package com.jp.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jp.library.dto.CategoryDto;
import com.jp.library.service.CategoryService;

@Controller
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/addCategory")
	public String addCategory(Model model) {
		int length = categoryService.findAll().size();
		String categoryId = String.format("CA%05d", length + 1);
		CategoryDto dto = new CategoryDto();
		dto.setId(categoryId);
		model.addAttribute("category",dto );
		return "addBookCategory";
	}
	
	@PostMapping("/addCategory")
	public String addCategoryConfirm(@ModelAttribute("category")CategoryDto dto, BindingResult result,Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("category", dto);
			model.addAttribute("error","validate!");
			return "addBookCategory";
		}
		try {
			categoryService.categoryAdd(dto);
			
		} catch (Exception e) {
			model.addAttribute("category", dto);
			model.addAttribute("error","Name is already Exited!");
			return "addBookCategory";
		}
		return "redirect:/addCategory";
	}

}
