package com.jp.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.library.dto.CategoryDto;
import com.jp.library.entity.Category;
import com.jp.library.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public Category categoryAdd(CategoryDto dto) {
		Category c = new Category();
		c.setId(dto.getId());
		c.setCategoryName(dto.getCategoryName());
		categoryRepository.categoryAdd(c);
		return c;
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

}
