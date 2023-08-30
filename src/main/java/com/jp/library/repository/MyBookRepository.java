package com.jp.library.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jp.library.entity.MyBookList;

@Mapper
public interface MyBookRepository {
	void addToMyBookList(Long bookId, Long userId);
	
	void deleteBook(Long bookId);

    List<MyBookList> getBooksForUser(Long userId);
}
