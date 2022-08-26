package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.beans.CustomerAddress;
@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Integer>{

	@Query("select al from CustomerAddress al where al.customerId=?1")
	List<CustomerAddress> getCustomerAddress(int cid);
}
