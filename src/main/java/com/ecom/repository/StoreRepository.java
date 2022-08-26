package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.beans.Store;
@Repository
public interface StoreRepository extends JpaRepository<Store,Integer>{

	@Query("select al from Store al where al.storeName=?1")
	Store findStoreName(String storeName);

 

}
