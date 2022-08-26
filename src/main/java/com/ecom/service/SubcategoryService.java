package com.ecom.service;

import java.util.List;

import com.ecom.beans.SubCategory;

public interface SubcategoryService {

	SubCategory addSubcategory(SubCategory details);

	SubCategory getDataById(int id);

	List<SubCategory> list();

	SubCategory getSubCategoryById(int id);

	void deleteSubCategoryById(int id);

	public List<SubCategory> findByCategory(int id);

	List<SubCategory> findSubCategoryByCategory(int id);

}
