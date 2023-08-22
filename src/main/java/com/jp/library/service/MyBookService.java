package com.jp.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.library.repository.MyBookRepository;

@Service
public class MyBookService {
	@Autowired
	MyBookRepository myBookRepository;
	
    public void addToMyBookList(String bookId) {
    	myBookRepository.addToMyBookList(bookId);
    }
    
//    public void addToMyBookList(String bookId, Long userId) {
//    	myBookRepository.addToMyBookList(bookId, userId);
//    }
//
//    public List<BookDto> getBooksForUser(Long userId) {
//        return myBookRepository.getBooksForUser(userId);
//    }

}
