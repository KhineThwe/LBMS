package com.jp.library.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jp.library.entity.BookEntity;

@Mapper
public interface BookRepository {
	List<BookEntity> findAll();
	
	List<BookEntity> findBooks();
	
	List<BookEntity> findEBooks();
	
	List<BookEntity> findByCategory(BookEntity e);
	
	public Optional<BookEntity> findById(String id);
	
	List<BookEntity> filter(BookEntity e);
	
	public void bookAdd(BookEntity book);
	
	public void update(BookEntity exp);
	
	public void updateAvailable(BookEntity exp);
	
	public void updateLent(BookEntity exp);
}
