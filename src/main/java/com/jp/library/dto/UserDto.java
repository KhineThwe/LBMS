package com.jp.library.dto;

import com.jp.library.validator.ValidPhone;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {
	private Long id;
	@NotBlank(message = "Name cannot be blank!.")
	private String name;

	@NotBlank(message = "Mail cannot be blank!")
//	@Email(message = "Invalid email address format!")
	private String email;

	@NotBlank(message = "Password  cannot be blank!")
	private String password;

	@ValidPhone
	private String phoneNo;
	private String roles;

	public UserDto() {
		super();
	}

	public UserDto(String name, String email, String password, String phoneNo, String roles) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getRole() {
		return roles;
	}

	public void setRole(String roles) {
		this.roles = roles;
	}

}
