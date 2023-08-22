package com.jp.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jp.library.dto.BookDto;
import com.jp.library.service.MyBookService;

@Controller
public class MyBookListController {

//    @Autowired
//    MyBookService myBookService;
//
//    @PostMapping("/add")
//    public void addToMyBookList(@RequestParam String bookId, @RequestParam Long userId) {
//    	myBookService.addToMyBookList(bookId, userId);
//    }
//
//    @GetMapping("/getbooks")
//    public List<BookDto> getBooksForUser(@RequestParam Long userId) {
//        return myBookService.getBooksForUser(userId);
//    }

}
