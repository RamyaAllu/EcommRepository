package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.beans.BilingDetails;
import com.ecom.beans.CustomerCart;
import com.ecom.beans.CustomerOrder;
import com.ecom.repository.CustomerCartRepository;
import com.ecom.repository.CustomerOrderRepository;
import com.ecom.service.BillingService;
import com.ecom.service.CustomerCartService;
import com.ecom.service.SalesOrderService;

@Controller
@RequestMapping("/Sales")
public class SalesOrderController {
	
	@Autowired
	SalesOrderService salesOrderService;
	@Autowired
	CustomerCartService customerCartService;
	@Autowired 
	CustomerOrderRepository customerOrderRepository;
	@Autowired
	CustomerCartRepository customerCartRepository;
	@Autowired
	BillingService billingService;
	
	@RequestMapping("/Order")
	public String addSales(Model model)
	{
	 
		 
		//List<CustomerCart> cartlist =customerCartService.getAllCart();
		//List<CustomerCart> cartlist = customerCartRepository.getCartOrderList(id);
		List<CustomerCart> cartlist = customerCartRepository.findAll();
		model.addAttribute("cartlist",  cartlist);
		
		List<CustomerOrder> customerOrder = customerOrderRepository.findAll();
		model.addAttribute("order", customerOrder);
		
		List<BilingDetails> listCust = billingService.getAllBillDetails();
		model.addAttribute("biling", listCust);
		return "order";
	}
	
   @RequestMapping("/Homepage")
   public String homePage(Model model)
   {
	   return "front-end-index";
   }
}
