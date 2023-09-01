package com.jp.library.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class BookDto {
	private String bookId;
	@NotNull(message = "Book Name can not be null!!")
	@NotBlank(message = "Book Name cannot be blank!")
	private String bookName;
	@NotNull(message = "Please select an option")
	@NotBlank(message = "Please select an option")
	private String bookCategoryId;
	@NotNull(message = "Book Author can not be null!!")
	@NotBlank(message = "Book Author cannot be blank!")
	private String bookAuthor;
	@NotNull(message = "Produce Year can not be null!!")
	@NotBlank(message = "Produce Year cannot be blank!")
	private String produceYear;
	@NotNull(message = "Please select an option")
	@NotBlank(message = "Please select an option")
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

}
