package com.ecom.service;

import java.util.List;

import com.ecom.beans.SalesOrder;

public interface SalesOrderService {
	
	
	SalesOrder addSalesOrder( SalesOrder salesOrder);
	
	List<SalesOrder> SalesOrderList();

}
