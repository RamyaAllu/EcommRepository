package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("select al from Customer al where lower(al.email)=lower(?1) and al.password=?2 ")
	Customer findByEmailAndPassword(String Email, String password);
	@Query("select al from Customer al where lower(al.email)=lower(?1)")
	Customer findByEmail(String email);

}
