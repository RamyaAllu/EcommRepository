package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.beans.Customer;
import com.ecom.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerrepository;

	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerrepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomerRegistration() {
		// TODO Auto-generated method stub
		return customerrepository.findAll();
	}

	@Override
	public Customer getCustomer(String Email, String password) {

		return customerrepository.findByEmailAndPassword(Email, password);
	}

	@Override
	public void deleteBydataId(int customerId) {
		// TODO Auto-generated method stub
		customerrepository.deleteById(customerId);
	}

	@Override
	public void updateBydataId(Customer customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public Customer getCustomerById(int customerid) {
		// TODO Auto-generated method stub
		return customerrepository.findById(customerid).get();
	}

}
