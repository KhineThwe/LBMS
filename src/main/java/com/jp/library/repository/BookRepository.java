package com.jp.library.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jp.library.entity.BookEntity;

@Mapper
public interface BookRepository {
	List<BookEntity> findAll();
	
	List<BookEntity> findBooks();
	
	List<BookEntity> findEBooks();
	
	List<BookEntity> findByCategory(BookEntity e);
	
	List<BookEntity> filter(BookEntity e);
	
	public void bookAdd(BookEntity book);
}
