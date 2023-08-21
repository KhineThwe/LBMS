package com.jp.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.library.entity.Role;
import com.jp.library.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	

	

	public void insertRole(Role role) {
		roleRepository.insertRole(role);
	}

	public Role getRoleById(Long id) {
		return roleRepository.getRoleById(id);
	}

	public List<Role> getAllRoles() {
		return roleRepository.getAllRoles();
	}

	public void updateRole(Role role) {
		roleRepository.updateRole(role);
	}

	public void deleteRole(Long id) {
		roleRepository.deleteRole(id);
	}

}
