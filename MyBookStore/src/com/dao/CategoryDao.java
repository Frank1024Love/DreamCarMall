package com.dao;

import java.util.List;

import com.domain.Category;

public interface CategoryDao {

	void save(Category c);
	
	

	List<Category> findAll();

	Category findById(String categoryId);

}
