package com.jp.library.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jp.library.entity.Role;

@Mapper
public interface RoleRepository {
	// Insert a new role
	void insertRole(Role role);

	// Retrieve a role by its ID
	Role getRoleById(Long id);

	// Retrieve all roles
	List<Role> getAllRoles();

	// Update an existing role
	void updateRole(Role role);

	// Delete a role by its ID
	void deleteRole(Long id);
}
