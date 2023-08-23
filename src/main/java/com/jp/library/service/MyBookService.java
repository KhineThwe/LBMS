package com.jp.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.library.entity.MyBookList;
import com.jp.library.repository.MyBookRepository;

@Service
public class MyBookService {
	@Autowired
	MyBookRepository myBookRepository;

	public void addToMyBookList(String bookId, Long userId) {
		myBookRepository.addToMyBookList(bookId, userId);
	}

	public void deleteBook(String bookId) {
		myBookRepository.deleteBook(bookId);
	}

	public List<MyBookList> getBooksForUser(Long userId) {
		return myBookRepository.getBooksForUser(userId);
	}

}
