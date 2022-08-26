package com.ecom.service;

import java.util.List;

 import com.ecom.beans.Review;

public interface ReviewService {

	Review addReviewDetails(Review details);
	
	void updateReviewDetails(Review details);
	
	List<Review> getAllReviewDetails();
	
    void deleteReviewDetailsById(int id);
}
