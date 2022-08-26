package com.ecom.service;

import java.util.ArrayList;
import java.util.List;

import com.ecom.beans.ProductImage;

public interface ImageService {

	ProductImage addImage(ProductImage image);

	List<ProductImage> getAllImages();

	ArrayList<ProductImage> getPhysicalProductImages(String productid);
}
