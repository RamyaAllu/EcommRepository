package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.beans.SubCategory;
import com.ecom.repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubcategoryService {

	@Autowired
	SubCategoryRepository subcategoryrepository;

	@Override
	public SubCategory addSubcategory(SubCategory details) {
		// TODO Auto-generated method stub
		return subcategoryrepository.save(details);
	}

	@Override
	public SubCategory getDataById(int id) {
		// TODO Auto-generated method stub
		return subcategoryrepository.getById(id);
	}

	@Override
	public List<SubCategory> list() {
		// TODO Auto-generated method stub
		return subcategoryrepository.findAll();
	}

	@Override
	public List<SubCategory> findByCategory(int id) {
		// TODO Auto-generated method stub
		return subcategoryrepository.findAll();
	}

	@Override
	public List<SubCategory> findSubCategoryByCategory(int id) {
		// TODO Auto-generated method stub
		return subcategoryrepository.findSubCategoryByCategory(id);
	}

	@Override
	public SubCategory getSubCategoryById(int id) {
		// TODO Auto-generated method stub
		return subcategoryrepository.getById(id);
	}

	@Override
	public void deleteSubCategoryById(int id) {
		// TODO Auto-generated method stub
		subcategoryrepository.deleteById(id);
	}

}
