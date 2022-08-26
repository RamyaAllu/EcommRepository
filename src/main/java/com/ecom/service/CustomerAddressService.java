package com.ecom.service;

import java.util.List;

import com.ecom.beans.CustomerAddress;

public interface CustomerAddressService {
	CustomerAddress addcustomeraddress(CustomerAddress customeraddress);

	List<CustomerAddress> getAllcustomeraddressAddress();

	void deleteBydataId(int customeraddressId);

	CustomerAddress getcustomeraddress(String username, String password);

	void updateBydataId(CustomerAddress customeraddress);

	CustomerAddress getcustomeraddressById(int customeraddressid);
}
