package com.ecom.service;

import java.util.List;

import com.ecom.beans.Category;

public interface CategoryService {
	Category addCategory(Category name);

	List<Category> getAllCategory();

	List<Category> getAllCategoryById(int id);

	List<Category> getAllCategoryById(Iterable<Integer> id);

	Category getCategoryById(int id);

	void deleteCategoryById(int id);

	public Iterable<Category> findAll();

	public Category find(int id);
}
