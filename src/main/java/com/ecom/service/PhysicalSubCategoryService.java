package com.ecom.service;

import java.util.List;

import com.ecom.beans.PhysicalSubCategory;

public interface PhysicalSubCategoryService {
	PhysicalSubCategory addPhysicalSubCategory(PhysicalSubCategory subcatagory);

	PhysicalSubCategory getDataById(int id);

	List<PhysicalSubCategory> list();

	PhysicalSubCategory getPhysicalSubCategoryById(int id);

	void deletePhysicalSubCategoryById(int id);

	public List<PhysicalSubCategory> findByCategory(int id);

	List<PhysicalSubCategory> findPhysicalSubCategoryByCategory(String CategoryName);

}
