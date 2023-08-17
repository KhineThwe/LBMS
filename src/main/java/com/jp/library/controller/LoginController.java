package com.jp.library.controller;

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

		if (existingUser != null)
			result.rejectValue("email", null, "User already registered !!!");

		if (result.hasErrors()) {
			model.addAttribute("user", userDto);
			return "/register";
		}

		userService.saveUser(userDto);
		return "redirect:/register?success";
		//tochuuu
	}
}
