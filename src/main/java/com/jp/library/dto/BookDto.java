package com.jp.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class BookDto {
	private String bookId;
	@NotBlank(message = "Book Name cannot be blank!")
	private String bookName;
	private CategoryDto bookCategoryId;
	@NotBlank(message = "Book Author cannot be blank!")
	private String bookAuthor;
	@NotBlank(message = "Produce Year cannot be blank!")
	private String produceYear;
	private String bookType;
	private String fileUpload;
	private String imageUpload;
	private byte[] content;

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

	public CategoryDto getBookCategoryId() {
		return bookCategoryId;
	}

	public void setBookCategoryId(CategoryDto bookCategoryId) {
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

}
