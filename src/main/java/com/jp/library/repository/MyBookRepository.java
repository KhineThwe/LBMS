package com.jp.library.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jp.library.dto.BookDto;

@Mapper
public interface MyBookRepository {
	void addToMyBookList(String bookId, Long userId);

    List<BookDto> getBooksForUser(Long userId);
}
