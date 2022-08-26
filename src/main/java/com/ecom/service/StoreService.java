package com.ecom.service;

import java.util.List;

import com.ecom.beans.Store;
 

public interface StoreService {
	
	
	List<Store> getAllStores();
	List<Store> getAllByStoreID(int storeId);


	Store saveStore(Store store);

	 

	void deleteStoreById(int storeId);
	Store addBilingName(Store store);
	 


	 

	Store getStoreById(int storeid);

	 
	
	 List<Store> getBranchesByStore(Integer id);
	//Branch getServiceById(int careid);
	 

}
