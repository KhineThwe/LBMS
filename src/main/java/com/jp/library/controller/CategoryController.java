package com.jp.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jp.library.dto.CategoryDto;
import com.jp.library.entity.BookEntity;
import com.jp.library.entity.Category;
import com.jp.library.service.CategoryService;

import jakarta.validation.Valid;

@Controller
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping("/addCategory")
	public String addCategory(Model model) {
		List<Category> length = categoryService.findAll();

		Category c;
		if (length.isEmpty()) {
			c = new Category();
		} else {
			c = length.get(length.size() - 1);

		}
		String id = "0";
		if (c.getId() != null && c.getId().matches("CA\\d{5}")) {
			id = c.getId().substring(2);
		}
		int real_id = Integer.parseInt(id) + 1;
		String categoryId = String.format("CA%05d", real_id);
		CategoryDto dto = new CategoryDto();
		dto.setId(categoryId);
		model.addAttribute("category", dto);
		return "addBookCategory";
	}

	@PostMapping("/addCategory")
	public String addCategoryConfirm(@Valid @ModelAttribute("category") CategoryDto dto, BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			model.addAttribute("category", dto);
			return "addBookCategory";
		}
		try {
			categoryService.categoryAdd(dto);

		} catch (Exception e) {
			model.addAttribute("category", dto);
			model.addAttribute("error", "Name is already Exited!");
			return "addBookCategory";
		}
		model.addAttribute("message", "Category saved successfully!");
		return "addBookCategory";
	}

}
