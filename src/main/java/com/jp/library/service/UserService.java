package com.jp.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jp.library.dto.UserDto;
import com.jp.library.entity.User;
import com.jp.library.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void saveUser(UserDto userDto) {
		User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
				userDto.getPhoneNo(), userDto.getRole());
		userRepository.insertUser(user);
	}

	public User findUserByEmail(String email) {
		return userRepository.getUserByEmail(email);
	}

	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

}
