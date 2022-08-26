package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.beans.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	@Query(value = "SELECT *\r\n"
			+ " FROM public.review\r\n"
			+ " order by created DESC", nativeQuery = true)
			List<Review> findAllRatingsInDescOrder();
	
     @Query("select a1 from Review a1 where a1.productId=?1 order by created desc")
     List<Review> getReviewByProductId(int id);
 }
