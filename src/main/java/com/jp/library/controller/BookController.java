package com.jp.library.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BookController {
	@Value("${spring.servlet.multipart.max-file-size}")
	private String maxFileSize;

	@Autowired
	BookService bookService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("bookList", bookService.findAll());
		model.addAttribute("filter", new BookEntity());
		return "index";
	}

	@GetMapping("/books")
	public String books(Model model) {
		System.out.println(bookService.findBooks());
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
//		e.setBookCategoryId(name);
		model.addAttribute("bookList", bookService.findByCategory(e));
		model.addAttribute("filter", new BookEntity());
		return "index";
	}

	@PostMapping("/filter")
	public String filter(@ModelAttribute("filter") BookEntity e, Model model) {
		model.addAttribute("bookList", bookService.filter(e));
		return "index";
	}

	@GetMapping("/addBook")
	public String addBook(Model model) {
		int length = bookService.findAll().size();
		String bookId = String.format("C%06d", length + 1);
		BookDto dto = new BookDto();
		dto.setBookId(bookId);
		model.addAttribute("book", dto);
		return "addBook";
	}

	@PostMapping("/addBook")
	public String addBookToDb(@ModelAttribute("book") BookDto dto,
			@RequestParam("document") MultipartFile mulitpartFile, RedirectAttributes ra) throws IOException {
		String fileName = StringUtils.cleanPath(mulitpartFile.getOriginalFilename());

		// configuration for file upload
		dto.setContent(mulitpartFile.getBytes());
		BookEntity b = bookService.save(dto);
		String uploadDir = "./book-storage/" + b.getBookId();
		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = mulitpartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			System.out.println(filePath.toFile().getAbsolutePath());
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("Could not save uploaded File: " + fileName);
		}
		ra.addFlashAttribute("message", "The file has been uploaded successfully");
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
		String headerValue = "attachment; filename = " + b.getBookName();

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

	@GetMapping("/update/{id}")
	public String updateBook(Model model, @PathVariable("id") String id) {
		model.addAttribute("bookInfo", bookService.getBookInfo(id));
		return "updateBook";
	}

	@PostMapping("/update")
	public String updateConfirm(Model model, @ModelAttribute("bookInfo") BookDto h) {
		bookService.updateBook(h);
		return "updateBook";
	}
}
