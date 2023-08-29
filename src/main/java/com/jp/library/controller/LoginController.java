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
		//show login user in nav bar
		//when update database saving format is wrong,need to sort
		//when login,can we show register nav to that user? and add book and add category option
		//when pdf filed is null,data saves auto but no pdf,need to fix
		//need to fix select option for book type 
		//still download error when pdf data is saved null and update later
		//when deleting image and pdf in image and pdf file directory,auto generating id number is completely ruin and also download
		//normal user can't click back to already lent to avaiable
		//addBook.html --> error validation message can't show
		
		// 8.ui responsive ******* wa
		// 9.ui fix before submit wa
		
		//index.html ka nav location arrangement wa
	}
}
