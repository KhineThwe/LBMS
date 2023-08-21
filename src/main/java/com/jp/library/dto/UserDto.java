package com.jp.library.dto;

import java.util.Set;

import com.jp.library.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
	private Long id;
	@NotEmpty(message = "Please enter valid name.")
	private String name;

	@NotEmpty(message = "Please enter valid email.")
	@Email
	private String email;

	@NotEmpty(message = "Please enter valid password.")
	private String password;

	private String phoneNo;
	private Set<Role> roles;

	public UserDto() {
		super();
	}

	public UserDto(String name, String email, String password, String phoneNo, Set<Role> roles) {
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

	public Set<Role> getRole() {
		return roles;
	}

	public void setRole(Set<Role> roles) {
		this.roles = roles;
	}

}
