package com.ecom.service;

import java.util.List;

import com.ecom.beans.PhysicalCategory;

public interface PhysicalCategoryService {
	PhysicalCategory addCategory(PhysicalCategory name);

	List<PhysicalCategory> getAllCategory();

	List<PhysicalCategory> getAllCategoryById(int id);

	List<PhysicalCategory> getAllCategoryById(Iterable<Integer> id);

	PhysicalCategory getCategoryById(int id);

	void deleteCategoryById(int id);

	public Iterable<PhysicalCategory> findAll();

	public PhysicalCategory find(int id);
}
