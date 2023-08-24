package com.jp.library.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jp.library.dto.BookDto;
import com.jp.library.entity.BookEntity;
import com.jp.library.service.BookService;
import com.jp.library.service.CategoryService;
import com.jp.library.service.MyBookService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class BookController {
	@Value("${spring.servlet.multipart.max-file-size}")
	private String maxFileSize;

	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	MyBookService myBookService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("bookList", bookService.findAll());
		model.addAttribute("filter", new BookEntity());
		model.addAttribute("categories", categoryService.findAll());
		return "index";
	}

	@GetMapping("/books")
	public String books(Model model) {
		model.addAttribute("bookList", bookService.findBooks());
		model.addAttribute("filter", new BookEntity());
		return "index";
	}

	@GetMapping("/ebooks")
	public String ebooks(Model model) {
		model.addAttribute("bookList", bookService.findEbooks());
		model.addAttribute("filter", new BookEntity());
		return "index";
	}

	@GetMapping("/category")
	public String category(Model model, @RequestParam("name") String name) {
		BookEntity e = new BookEntity();
		e.setBookCategoryId(name);
		model.addAttribute("bookList", bookService.findByCategory(e));
		model.addAttribute("filter", new BookEntity());
		model.addAttribute("categories", categoryService.findAll());
		return "index";
	}

	@PostMapping("/filter")
	public String filter(@ModelAttribute("filter") BookEntity e, Model model) {
		model.addAttribute("bookList", bookService.filter(e));
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("filter", new BookEntity());
		return "index";
	}

	@GetMapping("/addBook")
	public String addBook(Model model) {
		List<BookEntity> length = bookService.findAll();
		BookEntity b = length.get(length.size()-1);
		String id = b.getBookId().substring(1);
		int real_id = Integer.parseInt(id) + 1;
		String bookId = String.format("C%06d", real_id);
		BookDto dto = new BookDto();
		dto.setBookId(bookId);
		model.addAttribute("book", dto);
		model.addAttribute("categories", categoryService.findAll());
		return "addBook";
	}

	@PostMapping("/addBook")
	public String addBookToDb(@Valid @ModelAttribute("book") BookDto book, BindingResult result,
			@RequestParam("document") MultipartFile mulitpartFile, @RequestParam("pdf") MultipartFile pdf,
			RedirectAttributes ra, Model model) throws IOException {
		if (result.hasErrors()) {
			model.addAttribute("book", book);
			return "addBook";
		}

		String fileName = StringUtils.cleanPath(mulitpartFile.getOriginalFilename());
		String pdfName = StringUtils.cleanPath(pdf.getOriginalFilename());

		// configuration for file upload
		book.setContent(mulitpartFile.getBytes());
		book.setImageUpload(fileName);
		book.setFileUpload(pdfName);
		BookEntity b = bookService.save(book);
		String uploadDir = "./book-storage/" + b.getBookId();
		String pdfDir = "./book-pdf/" + b.getBookId();
		Path uploadPath = Paths.get(uploadDir);
		Path updfPath = Paths.get(pdfDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		if (!Files.exists(updfPath)) {
			try {
				Files.createDirectories(updfPath);
				Path pdfPath = updfPath.resolve(pdfName);
				Files.copy(pdf.getInputStream(), pdfPath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		try (InputStream inputStream = mulitpartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("Could not save uploaded File: " + fileName + "" + pdfName);
		}
//		ra.addFlashAttribute("message", "The file has been uploaded successfully");
		return "redirect:/";
	}

	@GetMapping("/download")
	public void downloadFile(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		Optional<BookEntity> result = bookService.findById(id);

		if (!result.isPresent()) {
			throw new Exception("Could not find document with ID: " + id);
		}
		BookEntity b = result.get();
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename = " + b.getPDFPath();

		response.setHeader(headerKey, headerValue);

		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.write(b.getContent());
		outputStream.close();
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleFileUploadError(RedirectAttributes ra) {
		ra.addFlashAttribute("error", "You could not upload file bigger than " + maxFileSize);
		return "redirect:/";
	}

	@GetMapping("/update")
	public String updateBook(Model model, @RequestParam("id") String id) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("bookInfo", bookService.getBookInfo(id));
		return "updateBook";
	}

	@PostMapping("/update")
	public String updateConfirm(Model model, @ModelAttribute("bookInfo") BookDto h,
			@RequestParam("document") MultipartFile mulitpartFile, @RequestParam("pdf") MultipartFile pdf)
			throws IOException {
		if (mulitpartFile != null && !mulitpartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(mulitpartFile.getOriginalFilename());
			h.setContent(mulitpartFile.getBytes());
			h.setImageUpload(fileName);
		}

		if (pdf != null && !pdf.isEmpty()) {
			String pdfName = StringUtils.cleanPath(pdf.getOriginalFilename());
			h.setFileUpload(pdfName);
		}

		model.addAttribute("categories", categoryService.findAll());
		BookEntity updateBook = bookService.updateBook(h);

		if (mulitpartFile != null && !mulitpartFile.isEmpty() && pdf != null && !pdf.isEmpty()) {
			String uploadDir = "./book-storage/" + updateBook.getBookId();
			String pdfDir = "./book-pdf/" + updateBook.getBookId();
			Path uploadPath = Paths.get(uploadDir);
			Path pdfPath = Paths.get(pdfDir);

			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			if (!Files.exists(pdfPath)) {
				Files.createDirectories(pdfPath);
			}

			try (InputStream imageInputStream = mulitpartFile.getInputStream();
					InputStream pdfInputStream = pdf.getInputStream()) {
				Path imageFilePath = uploadPath.resolve(updateBook.getImageUpload());
				Path pdfFilePath = pdfPath.resolve(updateBook.getFileUpload());
				Files.copy(imageInputStream, imageFilePath, StandardCopyOption.REPLACE_EXISTING);
				Files.copy(pdfInputStream, pdfFilePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new IOException("Could not save uploaded files for book ID: " + updateBook.getBookId());
			}
		}

		return "redirect:/";
	}

	@GetMapping("/available/{id}")
	public String available(@PathVariable("id") String id) {
		Optional<BookEntity> result = bookService.findById(id);
		BookEntity b = result.get();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		Long userId = Long.parseLong(authentication.getName());
		myBookService.addToMyBookList(id, userId);
		b.setIs_available(false);
		bookService.updateAvailable(b);
		return "redirect:/";
	}

	@GetMapping("/lent/{id}")
	public String lent(@PathVariable("id") String id) {
		Optional<BookEntity> result = bookService.findById(id);
		BookEntity b = result.get();
		myBookService.deleteBook(id);
		b.setIs_available(true);
		bookService.updateAvailable(b);
		return "redirect:/";
	}
}
