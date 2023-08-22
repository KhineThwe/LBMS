package com.jp.library.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyBookRepository {
//	void addToMyBookList(String bookId, Long userId);
	void addToMyBookList(String bookId);

//    List<BookDto> getBooksForUser(Long userId);
}
