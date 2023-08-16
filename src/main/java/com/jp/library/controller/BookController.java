package com.jp.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jp.library.dto.BookDto;
import com.jp.library.entity.BookEntity;
import com.jp.library.service.BookService;

@Controller
public class BookController {
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
		e.setBookCategory(name);
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
	public String addBookToDb(@ModelAttribute("book") BookDto dto,@RequestParam("document") MultipartFile mulitpartFile, RedirectAttributes ra) {
		String fileName = StringUtils.cleanPath(mulitpartFile.getOriginalFilename());
		System.out.println(fileName);
		bookService.save(dto);
		return "redirect:/";
	}

	@GetMapping("/update/{id}")
	public String updateBook(Model model, @PathVariable("id") int id) {
		model.addAttribute("bookInfo", bookService.getBookInfo(id));
		return "updateBook";
	}

	@PostMapping("/update")
	public String updateConfirm(Model model, @ModelAttribute("bookInfo") BookDto h) {
		bookService.updateBook(h);
		return "updateBook";
	}
}
