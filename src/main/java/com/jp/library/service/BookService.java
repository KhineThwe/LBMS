package com.jp.library.service;

import java.util.List;
import java.util.Optional;

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
	
	public Optional<BookEntity> findById(String id){
		return bookRepository.findById(id);
	}
	
	public List<BookEntity> filter(BookEntity e){
		return bookRepository.filter(e);
	}
	
	public BookEntity save(BookDto dto) {
		BookEntity entity = new BookEntity();
		entity.setBookId(dto.getBookId());
		entity.setBookName(dto.getBookName());
		entity.setBookAuthor(dto.getBookAuthor());
		entity.setBookCategoryId(dto.getBookCategoryId());
		entity.setProduceYear(dto.getProduceYear());
		entity.setBookType(dto.getBookType());
		entity.setContent(dto.getContent());
		entity.setFileUpload(dto.getFileUpload());
		entity.setImageUpload(dto.getImageUpload());
		entity.setIs_available(true);
		bookRepository.bookAdd(entity);
		return entity;
	}
	
	public BookDto getBookInfo(String id) {
		Optional<BookEntity> p = bookRepository.findById(id);
		BookEntity e = p.get();
		BookDto dto = new BookDto();
		dto.setBookId(e.getBookId());
		dto.setBookName(e.getBookName());
		dto.setBookAuthor(e.getBookAuthor());
		dto.setBookCategoryId(e.getBookCategoryId());
		dto.setBookType(e.getBookType());
		dto.setProduceYear(e.getProduceYear());
		return dto;
	}
	
	public void updateBook(BookDto dto) {
		BookEntity entity = new BookEntity();
		entity.setBookId(dto.getBookId());
		entity.setBookAuthor(dto.getBookAuthor());
		entity.setBookCategoryId(dto.getBookCategoryId());
		entity.setBookName(dto.getBookName());
		entity.setProduceYear(dto.getProduceYear());
		entity.setBookType(dto.getBookType());
		bookRepository.update(entity);
	}

}
