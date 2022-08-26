package com.ecom.service;

import java.util.List;

import com.ecom.beans.BilingDetails;
 
 

public interface BillingService {
	
	BilingDetails addBilingName(BilingDetails bilingDetails);
	
	List<BilingDetails > getAllBillDetails();

//	public String getRandomNumberString();
	 
	 

}
