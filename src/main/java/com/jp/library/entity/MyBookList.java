package com.jp.library.entity;

import lombok.Data;

@Data
public class MyBookList {
	private String bookId;
	private Long userId;
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
