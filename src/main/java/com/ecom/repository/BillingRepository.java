package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.beans.BilingDetails;

public interface BillingRepository extends JpaRepository<BilingDetails, Integer>{
	@Query("select al from BilingDetails al where al.customerId=?1")
	List<BilingDetails> getData(int id);
}
 