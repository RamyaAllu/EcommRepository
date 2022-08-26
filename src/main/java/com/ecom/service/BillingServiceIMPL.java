package com.ecom.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.beans.BilingDetails;
import com.ecom.repository.BillingRepository;

@Service
public class BillingServiceIMPL implements BillingService{

	@Autowired
	BillingRepository bilinBillingRepository;
	@Override
	public BilingDetails addBilingName(BilingDetails bilingDetails) {
		// TODO Auto-generated method stub
		return bilinBillingRepository.save(bilingDetails);
	}
	@Override
	public List<BilingDetails> getAllBillDetails() {
		// TODO Auto-generated method stub
		return  bilinBillingRepository.findAll();
	}
//	@Override
//	public String getRandomNumberString() {
//		// It will generate 6 digit random Number.
//	    // from 0 to 999999
//	    Random rnd = new Random();
//	    int number = rnd.nextInt(999999);
//
//	    // this will convert any number sequence into 6 character.
//	    return String.format("%06d", number);
//	}

}
