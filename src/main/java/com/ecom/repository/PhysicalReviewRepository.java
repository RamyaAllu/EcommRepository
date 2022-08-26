package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.beans.PhysicalReview;

public interface PhysicalReviewRepository extends JpaRepository<PhysicalReview, Integer>{
	 @Query("select a1 from PhysicalReview a1 where a1.productId=?1 order by created desc")
     List<PhysicalReview> getPhysicalReviewByProductId(int id);
}
