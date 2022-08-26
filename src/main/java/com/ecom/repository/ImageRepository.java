package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.beans.ProductImage;

public interface ImageRepository extends JpaRepository<ProductImage, Integer> {

	@Query("select al from ProductImage al where al.productCode=?1")
	List<ProductImage> getphysicalproductimagesbyId(String productCode);
}
