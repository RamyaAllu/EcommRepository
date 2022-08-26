package com.ecom.service;

import java.util.List;

import com.ecom.beans.Coupon;

public interface CouponService {

	Coupon addCoupon(Coupon couponobject);

	Coupon getCouponById(int couponid);

	List<Coupon> getAllCoupon();

	void deleteCouponById(int couponid);

	List<Coupon> getIsActiveCoupons();

	void updateCoupon(Coupon coupon);

}
