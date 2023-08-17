package com.jp.library.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jp.library.entity.Role;

@Mapper
public interface RoleRepository {
	Role getRoleById(Long id);

	Role getRoleByName(String name);

	List<Role> getAllRoles();

	Role insertRole(Role role);

	void updateRole(Role role);

	void deleteRole(Long id);
}
