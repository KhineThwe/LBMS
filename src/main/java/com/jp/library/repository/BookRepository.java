package com.jp.library.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.jp.library.entity.BookEntity;

@Mapper
public interface BookRepository {
	List<BookEntity> findAll();

	List<BookEntity> findBooks();

	List<BookEntity> findEBooks();

	List<BookEntity> findByCategory(BookEntity e);

	public Optional<BookEntity> findById(String id);

	public BookEntity findForBookList(Long id);

	public Optional<BookEntity> findByIdForMyBookList(String id, Boolean status);

	List<BookEntity> filter(BookEntity e);

	public void bookAdd(BookEntity book);

	public void update(BookEntity exp);

	public void updateAvailable(BookEntity exp);

	public void updateLent(BookEntity exp);
}
