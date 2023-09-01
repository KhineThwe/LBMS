package com.jp.library.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jp.library.entity.Category;

@Mapper
public interface CategoryRepository {
	List<Category> findAll();

	public void categoryAdd(Category category);
}
