package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.beans.VendorDataList;
@Repository
public interface VendorDataListRepository extends JpaRepository<VendorDataList, Integer>{
	
	@Query("select al from VendorDataList al where al.vendorId=?1 ")
	public List<VendorDataList> vendordatalist(int vid);
}
