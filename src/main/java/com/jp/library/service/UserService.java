package com.jp.library.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jp.library.dto.UserDto;
import com.jp.library.entity.Role;
import com.jp.library.entity.User;
import com.jp.library.repository.RoleRepository;
import com.jp.library.repository.UserRepository;
import com.jp.library.util.TbConstants;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void saveUser(UserDto userDto) {
		Role role = roleRepository.getRoleByName(TbConstants.Roles.USER);
		System.out.println(role.getName());
		if (role == null)
			role = roleRepository.insertRole(new Role(TbConstants.Roles.USER));

		User user = new User(userDto.getName(), userDto.getEmail(), userDto.getPhoneNo(),
				passwordEncoder.encode(userDto.getPassword()), Arrays.asList(role));
		System.out.println(user);
		userRepository.insertUser(user);
	}
	
	public User findUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

}
