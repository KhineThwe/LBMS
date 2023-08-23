package com.jp.library.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jp.library.entity.MyBookList;

@Mapper
public interface MyBookRepository {
	void addToMyBookList(String bookId, Long userId);
	
	void deleteBook(String bookId);

    List<MyBookList> getBooksForUser(Long userId);
}
