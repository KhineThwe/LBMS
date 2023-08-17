package com.jp.library.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Role {
	private Long roleId;
	private String name;
	private List<User> users = new ArrayList<>();

	public Role(String name) {
		this.name = name;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
