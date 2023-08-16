package com.jp.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.library.dto.BookDto;
import com.jp.library.entity.BookEntity;
import com.jp.library.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	
	public List<BookEntity> findAll(){
		return bookRepository.findAll();
	}
	
	public List<BookEntity> findBooks(){
		return bookRepository.findBooks();
	}
	
	public List<BookEntity> findEbooks(){
		return bookRepository.findEBooks();
	}
	
	public List<BookEntity> findByCategory(BookEntity e){
		return bookRepository.findByCategory(e);
	}
	
	public List<BookEntity> filter(BookEntity e){
		return bookRepository.filter(e);
	}
	
	public void save(BookDto dto) {
		BookEntity entity = new BookEntity();
		entity.setBookId(dto.getBookId());
		entity.setBookName(dto.getBookName());
		entity.setBookAuthor(dto.getBookAuthor());
		entity.setBookCategory(dto.getBookCategory());
		entity.setProduceYear(dto.getProduceYear());
		entity.setBookType(dto.getBookType());
		bookRepository.bookAdd(entity);
	}

}
