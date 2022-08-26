package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.beans.Store;
import com.ecom.repository.StoreRepository;
 
@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreRepository storeRepository;
	@Override
	public List<Store> getAllStores() {
		// TODO Auto-generated method stub
		return storeRepository.findAll();
	}

	@Override
	public List<Store> getAllByStoreID(int storeId) {
		// TODO Auto-generated method stub
		return storeRepository.findAll();
	}

	@Override
	public Store saveStore(Store store) {
		 return storeRepository.save(store);
		
	}

	@Override
	public void deleteStoreById(int storeId) {
		storeRepository.deleteById(storeId);
		
	}

	@Override
	public Store  addBilingName(Store store) {
		return storeRepository.save(store);
		
	}

	@Override
	public Store getStoreById(int storeid) {
		// TODO Auto-generated method stub
		return storeRepository.getById(storeid);
	}

	@Override
	public List<Store> getBranchesByStore(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
