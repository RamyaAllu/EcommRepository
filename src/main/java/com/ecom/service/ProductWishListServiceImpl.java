package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.beans.ProductWishlist;
import com.ecom.repository.ProductWishListRepository;

@Service
public class ProductWishListServiceImpl implements ProductWishListService {
	@Autowired
	ProductWishListRepository productWishListRepository;

	@Override
	public List<ProductWishlist> getAllProductWishlists() {
		
		return productWishListRepository.findAll();
	}

	@Override
	public void deleteByProductId(int id) {
		productWishListRepository.deleteById(id);
		
	}

	@Override
	public ProductWishlist getProductWishlistById(int id) {
		
		return productWishListRepository.getById(id);
	}

	@Override
	public ProductWishlist addProductWishList(ProductWishlist productWishlist) {
		// TODO Auto-generated method stub
		return productWishListRepository.save(productWishlist);
	}

}
