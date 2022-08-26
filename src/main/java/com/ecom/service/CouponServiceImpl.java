package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.beans.Coupon;
import com.ecom.repository.CouponRepository;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	CouponRepository couponRepository;

	@Override
	public Coupon getCouponById(int couponid) {
		// TODO Auto-generated method stub
		return couponRepository.findById(couponid).get();
	}

	@Override
	public List<Coupon> getAllCoupon() {
		// TODO Auto-generated method stub
		return couponRepository.findAll();
	}

	@Override
	public void deleteCouponById(int couponid) {
		// TODO Auto-generated method stub
		couponRepository.deleteById(couponid);
	}

	@Override
	public List<Coupon> getIsActiveCoupons() {
		// TODO Auto-generated method stub
		return couponRepository.findIsActiveCoupons();
	}

	@Override
	public void updateCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		couponRepository.save(coupon);
	}

	@Override
	public Coupon addCoupon(Coupon couponobject) {
		// TODO Auto-generated method stub
		return couponRepository.save(couponobject);
	}

}
