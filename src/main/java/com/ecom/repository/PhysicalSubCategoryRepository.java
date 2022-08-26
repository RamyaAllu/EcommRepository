package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.beans.PhysicalSubCategory;

@Repository
public interface PhysicalSubCategoryRepository extends JpaRepository<PhysicalSubCategory, Integer> {

	@Query(value = "SELECT al FROM PhysicalSubCategory al WHERE al.categoryName =?1")
	List<PhysicalSubCategory> findPhysicalSubCategoryByCategory(String categoryName);

}
