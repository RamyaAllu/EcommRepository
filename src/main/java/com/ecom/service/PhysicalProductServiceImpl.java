package com.ecom.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.beans.PhysicalProducts;
import com.ecom.repository.PhysicalProductRepository;
@Service
public class PhysicalProductServiceImpl implements PhysicalProductService{
@Autowired
PhysicalProductRepository physicalproductrepository;
	@Override
	public PhysicalProducts addProduct(PhysicalProducts productobject) {
		
		return physicalproductrepository.save(productobject);
	}

	@Override
	public PhysicalProducts getProductById(int productid) {
		// TODO Auto-generated method stub
		return physicalproductrepository.getById(productid);
	}

	@Override
	public List<PhysicalProducts> getAllProduct() {
		// TODO Auto-generated method stub
		return physicalproductrepository.findAll();
	}

	@Override
	public void deleteBydataId(int id) {
		
		physicalproductrepository.deleteById(id);
	}

//	@Override
//	public List<PhysicalProducts> getLatestProducts(LocalDate created) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<PhysicalProducts> findPhysicalProductsByModelNumber(String model) {
		// TODO Auto-generated method stub
		//return physicalproductrepository.getPhysicalProductsByModelNumber(model);
	return null;
	}

//	@Override
//	public List<PhysicalProducts> getsinglemodelproduct() {
//		// TODO Auto-generated method stub
//		return physicalproductrepository.getPhysicalProducts();
//	}

}
