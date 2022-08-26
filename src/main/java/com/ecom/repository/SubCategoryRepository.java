package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.beans.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

	@Query(value = "SELECT al FROM SubCategory al WHERE al.categoryId =?1")
	List<SubCategory> findSubCategoryByCategory(Integer id);
	@Query(value = "SELECT al FROM SubCategory al WHERE al.categoryName =?1")
	List<SubCategory> findSubCategoryByCategory(String id);

}
