package com.ecom.service;

import java.util.List;

import com.ecom.beans.CustomerCart;

public interface CustomerCartService {

	CustomerCart addCart(CustomerCart customerCart);

	List<CustomerCart> getAllCart();

	void deleteBydataId(int id);

	CustomerCart getCartById(int cart);

}
