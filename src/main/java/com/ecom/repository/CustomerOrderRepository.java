package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.beans.CustomerOrder;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
	@Query("select al from CustomerOrder al where al.customerId=?1")
	List<CustomerOrder> getData(int id);

	@Query("select al from CustomerOrder al where al.orderDate=current_date ")
	List<CustomerOrder> findLatestOrders();

}
