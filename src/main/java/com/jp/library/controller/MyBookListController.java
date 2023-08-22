package com.jp.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jp.library.entity.BookEntity;
import com.jp.library.service.BookService;
import com.jp.library.service.CategoryService;
import com.jp.library.service.MyBookService;

@Controller
public class MyBookListController {
	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	MyBookService myBookService;

	@GetMapping("/myBookList")
	public String myBookList(Model model) {
		model.addAttribute("bookList", bookService.findAll());
		model.addAttribute("filter", new BookEntity());
		model.addAttribute("categories", categoryService.findAll());
		return "index";
	}

//	@PostMapping("/addToMyBookList")
//	public void addToMyBookList(@RequestParam String bookId, @RequestParam Long userId) {
//		
//		myBookService.addToMyBookList(bookId, userId);
//	}
	
	@PostMapping("/addToMyBookList")
	public void addToMyBookList(@RequestParam String bookId) {
		
		myBookService.addToMyBookList(bookId);
	}

//
//    @GetMapping("/getbooks")
//    public List<BookDto> getBooksForUser(@RequestParam Long userId) {
//        return myBookService.getBooksForUser(userId);
//    }

}
