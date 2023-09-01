package com.jp.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jp.library.dto.UserDto;
import com.jp.library.entity.User;
import com.jp.library.service.UserService;

@Component
public class AdminUserInitializer implements CommandLineRunner {
	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		// Check if an admin user already exists
		User adminUser = userService.findUserByEmail("admin@gmail.com");
		UserDto dto = new UserDto();
		if (adminUser == null) {
			dto.setEmail("admin@gmail.com");
			dto.setName("ADMIN");
			dto.setPhoneNo("09952588975");
			dto.setPassword("admin");
			dto.setRole("ROLE_ADMIN");
			userService.saveUser(dto);
		}
	}
}
