package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.beans.PhysicalSubCategory;
import com.ecom.repository.PhysicalSubCategoryRepository;

@Service
public class PhysicalSubCategoryServicesImpl implements PhysicalSubCategoryService {

	@Autowired
	PhysicalSubCategoryRepository PhysicalSubCategoryRepository;

	@Override
	public PhysicalSubCategory getDataById(int id) {
		// TODO Auto-generated method stub
		return PhysicalSubCategoryRepository.getById(id);
	}

	@Override
	public List<PhysicalSubCategory> list() {
		// TODO Auto-generated method stub
		return PhysicalSubCategoryRepository.findAll();
	}

	@Override
	public PhysicalSubCategory getPhysicalSubCategoryById(int id) {
		// TODO Auto-generated method stub
		return PhysicalSubCategoryRepository.getById(id);
	}

	@Override
	public void deletePhysicalSubCategoryById(int id) {
		// TODO Auto-generated method stub
		PhysicalSubCategoryRepository.deleteById(id);

	}

	@Override
	public List<PhysicalSubCategory> findByCategory(int id) {
		// TODO Auto-generated method stub
		return PhysicalSubCategoryRepository.findAll();
	}

	@Override
	public List<PhysicalSubCategory> findPhysicalSubCategoryByCategory(String categoryName) {
		// TODO Auto-generated method stub
		return PhysicalSubCategoryRepository.findPhysicalSubCategoryByCategory(categoryName);
	}

	@Override
	public PhysicalSubCategory addPhysicalSubCategory(PhysicalSubCategory subcatagory) {
		// TODO Auto-generated method stub
		return PhysicalSubCategoryRepository.save(subcatagory);
	}

}
