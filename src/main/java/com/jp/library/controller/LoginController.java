package com.jp.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jp.library.dto.UserDto;
import com.jp.library.entity.User;
import com.jp.library.service.UserService;

import jakarta.validation.Valid;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
		User existingUser = userService.findUserByEmail(userDto.getEmail());

		if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty())
			result.rejectValue("email", null, "User already registered !!!");

		if (result.hasErrors()) {
			model.addAttribute("user", userDto);
			return "/register";
		}
		List<User> users = userService.getAllUsers();
		if(users.size() == 0) {
			userDto.setRole("ROLE_ADMIN");
		}else {
			userDto.setRole("ROLE_USER");
		}
		userService.saveUser(userDto);
		return "redirect:/register?success";
		// tochuuu
		// 3download fix
		//*** public user error when no data
		//when book saved successfully,book not found error message still show and empty div still show
		//validation in login page 
		//role classification to public and when login,if user is already lent which user lends
		//show login user in nav bar
		//when update database saving format is wrong,need to sort
		//when user login,my books category is wrong.
		
		
		// 8.ui responsive *******
		// 9.ui fix before submit
		
		//register.html and login.html ka Go Home button ui
		//addBook.html ka error message location
		//index.html ka nav location arrangement
	}
}
