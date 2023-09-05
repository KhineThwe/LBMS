package com.jp.library.entity;

import java.util.Arrays;

import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class BookEntity {
	private String bookId;
	private String bookName;
	private String bookCategoryId;
	private String bookAuthor;
	private String produceYear;
	private String bookType;
	private String fileUpload;
	private String imageUpload;
	private byte[] content;
	private Boolean is_available;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookCategoryId() {
		return bookCategoryId;
	}

	public void setBookCategoryId(String bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getProduceYear() {
		return produceYear;
	}

	public void setProduceYear(String produceYear) {
		this.produceYear = produceYear;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getImageUpload() {
		return imageUpload;
	}

	public void setImageUpload(String imageUpload) {
		this.imageUpload = imageUpload;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public Boolean getIs_available() {
		return is_available;
	}

	public void setIs_available(Boolean is_available) {
		this.is_available = is_available;
	}

	@Transient
	public String getImagePath() {
		if (bookName == null || bookId == null)
			return null;
		return "/book-storage/" + bookId + "/" + imageUpload;

	}

	@Transient
	public String getPDFPath() {
		if (bookName == null || bookId == null)
			return null;
		return "/book-pdf/" + bookId + "/" + fileUpload;

	}

	@Override
	public String toString() {
		return "BookEntity [bookId=" + bookId + ", bookName=" + bookName + ", bookCategoryId=" + bookCategoryId
				+ ", bookAuthor=" + bookAuthor + ", produceYear=" + produceYear + ", bookType=" + bookType
				+ ", fileUpload=" + fileUpload + ", imageUpload=" + imageUpload + ", content="
				+ Arrays.toString(content) + ", is_available=" + is_available + "]";
	}
	

}
