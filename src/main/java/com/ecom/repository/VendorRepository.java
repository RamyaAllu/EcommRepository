package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.beans.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

	@Query("select al from Vendor al where al.vendorType='PhysicalProductsVendor'")
	List<Vendor> findPhysicalproductNames();

	@Query("select al from Vendor al where al.vendorType='DigitalProductsVendor' ")
	List<Vendor> finddigitalproductNames();

	@Query("select a1 from Vendor a1 where a1.email=?1")
	public Vendor findByEmailId(String email);

	@Query("select al from Vendor al where al.email=?1 and al.password=?2")
	Vendor findByUsernameIgnoreCaseAndPassword(String email, String password);

}
