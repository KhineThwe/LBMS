package com.jp.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jp.library.dto.UserDto;
import com.jp.library.entity.User;
import com.jp.library.service.UserService;
import com.jp.library.validator.EmailValidatorCustom;

import jakarta.validation.Valid;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
//	@PostMapping("/login")
//	public String login(@RequestParam String username, @RequestParam String password, Model model) {
//	    User user = userService.findUserByEmail(username);
//
//	    if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//	        // Authentication succeeded, redirect to a success page
//	        return "redirect:/login";
//	    } else {
//	        // Authentication failed, display an error message
//	        model.addAttribute("error", "Invalid username or password");
//	        return "login"; // Return to the login page with an error message
//	    }
//	}


	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new UserDto());
		return "register";
	}

	@PostMapping("/register")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
		User existingUser = userService.findUserByEmail(userDto.getEmail());
		if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty())
			result.rejectValue("email", null, "User already registered !!!");
		if (!EmailValidatorCustom.validate(userDto.getEmail())) {
			model.addAttribute("email_custom", "Invalid email format");
		}
		if (result.hasErrors()) {
			model.addAttribute("user", userDto);
			return "/register";
		}
//		List<User> users = userService.getAllUsers();
//		if (users.size() == 0) {
//			userDto.setRole("ROLE_ADMIN");
//		} else {
//			userDto.setRole("ROLE_USER");
//		}
		userDto.setRole("ROLE_USER");
		userService.saveUser(userDto);
		return "redirect:/register?success";
	}
}
//ui responsive only on index.html,add update pages
//register.html mhar error message pay yin ui
//when user 1 and 2 is deleted,user 3 is saved as admin
//default admin problem and name in nav bar