package com.ecom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.beans.ProductImage;
import com.ecom.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageRepository imagerepository;

	public ProductImage addImage(ProductImage image) {

		return imagerepository.save(image);
	}

	@Override
	public List<ProductImage> getAllImages() {
		// TODO Auto-generated method stub
		return imagerepository.findAll();
	}

	@Override
	public ArrayList<ProductImage> getPhysicalProductImages(String productid) {
		// TODO Auto-generated method stub
		return (ArrayList<ProductImage>) imagerepository.getphysicalproductimagesbyId(productid);
	}

}
