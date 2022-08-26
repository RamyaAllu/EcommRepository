package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom.beans.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	@Query(value = "select a1 from Coupon a1 where a1.isActive='Y'")
	List<Coupon> findIsActiveCoupons();

}
