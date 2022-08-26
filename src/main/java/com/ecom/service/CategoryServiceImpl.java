package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.beans.Category;
import com.ecom.beans.SubCategory;
import com.ecom.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryrepository;

	@Override
	public Category addCategory(Category name) {
		// TODO Auto-generated method stub
		return categoryrepository.save(name);
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryrepository.findAll();
	}

	@Override
	public List<Category> getAllCategoryById(Iterable<Integer> id) {
		// TODO Auto-generated method stub
		return categoryrepository.findAllById(id);
	}

	@Override
	public List<Category> getAllCategoryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getCategoryById(int id) {
		// TODO Auto-generated method stub
		return categoryrepository.getById(id);
	}

	@Override
	public void deleteCategoryById(int id) {
		// TODO Auto-generated method stub
		categoryrepository.deleteById(id);
	}

	@Override
	public Iterable<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryrepository.findAll();
	}

	@Override
	public Category find(int id) {
		// TODO Auto-generated method stub
		return categoryrepository.findById(id).get();
	}

}
