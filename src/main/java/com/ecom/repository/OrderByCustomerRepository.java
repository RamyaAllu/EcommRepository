package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.beans.OrderByCustomer;

public interface OrderByCustomerRepository extends JpaRepository<OrderByCustomer, Integer>{

	@Query("select al from OrderByCustomer al where al.orderId=?1 and al.customerId=?2 and al.isActive='Y' ")
	List<OrderByCustomer> orderedProduct(int oid,int cid);
	@Query("select al from OrderByCustomer al where al.customerId=?1 and al.productModelNumber=?2 and orderId=?3 and al.isActive='Y' ")
	List<OrderByCustomer> ordered(int cid,String modelNumber,int orderId);
	@Query("select al from OrderByCustomer al where al.customerId=?1 and al.isActive='Y'")
	List<OrderByCustomer> placedOrder(int cid);
}
