package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.beans.CustomerAddress;
import com.ecom.repository.CustomerAddressRepository;
@Service
public class CustomerAddressServiceImpl implements CustomerAddressService{
@Autowired
CustomerAddressRepository customeraddressrepository; 
	@Override
	public CustomerAddress addcustomeraddress(CustomerAddress customeraddress) {
		// TODO Auto-generated method stub
		return customeraddressrepository.save(customeraddress);
	}

	@Override
	public List<CustomerAddress> getAllcustomeraddressAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBydataId(int customeraddressId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CustomerAddress getcustomeraddress(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBydataId(CustomerAddress customeraddress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CustomerAddress getcustomeraddressById(int customeraddressid) {
		// TODO Auto-generated method stub
		return null;
	}

}
