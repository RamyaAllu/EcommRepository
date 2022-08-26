package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecom.beans.PhysicalCategory;
import com.ecom.repository.PhysicalCategoryRepository;

@Repository
public class PhysicalCategoryServiceImpl implements PhysicalCategoryService {
	@Autowired
	PhysicalCategoryRepository PhysicalCategoryRepository;

	@Override
	public PhysicalCategory addCategory(PhysicalCategory name) {
		// TODO Auto-generated method stub
		return PhysicalCategoryRepository.save(name);
	}

	@Override
	public List<PhysicalCategory> getAllCategory() {
		// TODO Auto-generated method stub
		return PhysicalCategoryRepository.findAll();
	}

	@Override
	public List<PhysicalCategory> getAllCategoryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PhysicalCategory> getAllCategoryById(Iterable<Integer> id) {
		// TODO Auto-generated method stub
		return PhysicalCategoryRepository.findAllById(id);
	}

	@Override
	public PhysicalCategory getCategoryById(int id) {
		// TODO Auto-generated method stub
		return PhysicalCategoryRepository.getById(id);
	}

	@Override
	public void deleteCategoryById(int id) {
		// TODO Auto-generated method stub
		PhysicalCategoryRepository.deleteById(id);
	}

	@Override
	public Iterable<PhysicalCategory> findAll() {
		// TODO Auto-generated method stub
		return PhysicalCategoryRepository.findAll();
	}

	@Override
	public PhysicalCategory find(int id) {
		// TODO Auto-generated method stub
		return PhysicalCategoryRepository.findById(id).get();
	}

}
