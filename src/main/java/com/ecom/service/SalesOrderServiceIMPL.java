package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.ecom.beans.SalesOrder;
import com.ecom.repository.SalesOrderRepository;

@Service
public class SalesOrderServiceIMPL implements SalesOrderService {

	
	@Autowired
	SalesOrderRepository salesOrderRepository;
	@Override
	public SalesOrder addSalesOrder(SalesOrder salesOrder) {
		// TODO Auto-generated method stub
		return  salesOrderRepository.save(salesOrder);
	}

	@Override
	public List<SalesOrder> SalesOrderList() {
		// TODO Auto-generated method stub
		return salesOrderRepository.findAll();
	}

}
