package com.ecom.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.ecom.beans.DigitalProducts;

public interface DigitalProductsService {

	DigitalProducts saveDigitalProductData(DigitalProducts data);
	List<DigitalProducts> getAllDigitalProductsData();
	void deleteBydataId(int dataid);
	DigitalProducts getDetails(int id);
	List<DigitalProducts> getAllDigitalProductsData(String keyword);
	List<DigitalProducts> getDigitalByName(String keyword);
	DigitalProducts addProduct(DigitalProducts productobject);
	DigitalProducts getProductById(int productid);
	List<DigitalProducts> getAllProduct();
	Optional<DigitalProducts> findById(int id);
	//List<DigitalProducts> findProductsBySubcategories(int subcatagoryId);
	List<DigitalProducts> getLatestProducts(LocalDate now);
	 
	
 }
