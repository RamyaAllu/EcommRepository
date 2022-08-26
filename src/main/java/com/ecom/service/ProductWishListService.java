package com.ecom.service;

import java.util.List;

import com.ecom.beans.ProductWishlist;

public interface ProductWishListService {
	
	List<ProductWishlist> getAllProductWishlists();
	void deleteByProductId(int id);
	ProductWishlist getProductWishlistById(int id);
	ProductWishlist addProductWishList(ProductWishlist productWishlist);

}
