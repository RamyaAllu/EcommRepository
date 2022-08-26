package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 import com.ecom.beans.Review;
import com.ecom.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{
@Autowired
ReviewRepository Repository;
	@Override
	public Review addReviewDetails(Review details) {
		// TODO Auto-generated method stub
		return Repository.save(details);
	}

	@Override
	public void updateReviewDetails(Review details) {
		// TODO Auto-generated method stub
		Repository.save(details);

	}

	@Override
	public List<Review> getAllReviewDetails() {
		// TODO Auto-generated method stub
		return Repository.findAll();
	}

	@Override
	public void deleteReviewDetailsById(int id) {
		// TODO Auto-generated method stub
		Repository.deleteById(id);

	}

}
