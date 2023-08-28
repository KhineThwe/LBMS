package com.jp.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jp.library.entity.BookEntity;
import com.jp.library.entity.MyBookList;
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
		model.addAttribute("filter", new BookEntity());
		model.addAttribute("categories", categoryService.findAll());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
		List<BookEntity> b = new ArrayList<BookEntity>();
		Long userId = Long.parseLong(authentication.getName());
		for(MyBookList m : myBookService.getBooksForUser(userId)) {
			b.add(bookService.findById(m.getBookId()).get());
//			b.add(bookService.findById(m.getBookId()).get());
		}
		model.addAttribute("bookList", b);
		return "index";
	}
}
