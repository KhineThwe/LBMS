package com.jp.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jp.library.dto.BookDto;
import com.jp.library.repository.MyBookRepository;

public class MyBookService {
	@Autowired
	MyBookRepository myBookRepository;
	
    public void addToMyBookList(String bookId, Long userId) {
    	myBookRepository.addToMyBookList(bookId, userId);
    }

    public List<BookDto> getBooksForUser(Long userId) {
        return myBookRepository.getBooksForUser(userId);
    }

}
