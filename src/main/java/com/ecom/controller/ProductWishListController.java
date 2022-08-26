package com.ecom.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecom.beans.Customer;
import com.ecom.beans.PhysicalCategory;
import com.ecom.beans.PhysicalProducts;
import com.ecom.beans.PhysicalSubCategory;
import com.ecom.beans.ProductWishlist;
import com.ecom.repository.PhysicalProductRepository;
import com.ecom.repository.ProductWishListRepository;
import com.ecom.service.CustomerService;
import com.ecom.service.PhysicalCategoryService;
import com.ecom.service.PhysicalProductService;
import com.ecom.service.PhysicalSubCategoryService;
import com.ecom.service.ProductWishListService;

@Controller
@RequestMapping("/wishlist")
public class ProductWishListController {
	
	@Autowired
	CustomerService customerservice;
	@Autowired
	PhysicalProductRepository physicalProductRepository;
	@Autowired
	PhysicalCategoryService PhysicalCategoryServices;
	
	@Autowired
	PhysicalSubCategoryService PhysicalSubCategoryServices;

	@Autowired
	ProductWishListService productWishListService;

	@Autowired
	PhysicalProductService physicalProductService;
	
	@Autowired
	ProductWishListRepository productWishListRepository;
	

	@RequestMapping("/deleteProductWishList/{id}/{cid}")
	public String deleteProductWishListById(Model model, @PathVariable("id") int id,@PathVariable("cid") int cid) {

		productWishListService.deleteByProductId(id);

		return "redirect:/wishlist/product-wishlist/"+cid;

	}

	@RequestMapping("/addtowishlist/{id}/{cid}")
	public String addToWishList(Model model, @PathVariable("id") int id, ProductWishlist productWishListObject,@PathVariable("cid") int cid) {
		
		ProductWishlist wishlist = productWishListRepository.findByProductId(id,cid);
		
		if(wishlist!=null) {
			return "redirect:/wishlist/product-wishlist/"+cid;
		}
		else {

		PhysicalProducts products = physicalProductService.getProductById(id);

		productWishListObject.setProductCode(products.getProductCode());
		productWishListObject.setProductModelNumber(products.getProductModelNumber());
		productWishListObject.setCreated(LocalDate.now());
		productWishListObject.setUpdated(LocalDate.now());
		productWishListObject.setIsactive('Y');
		productWishListObject.setProductName(products.getProductName());
		productWishListObject.setProductDiscountPrice(products.getProductDiscountPrice());

		productWishListObject.setProductImage(products.getProductImage());
		productWishListObject.setProductId(id);
		productWishListObject.setCustomerId(cid);
		productWishListObject.setProductRating(products.getProductRating());
		productWishListObject.setProductMRPPrice(products.getProductMRPPrice());
		productWishListObject.setProductSize(products.getProductSize());
		productWishListObject.setProductCompany(products.getProductCompany());
		productWishListObject.setProductCategory(products.getProductCategory());
		productWishListObject.setProductSubCategory(products.getProductSubCategory());
		productWishListObject.setProductShippingInformation(products.getProductShippingInformation());
		productWishListObject.setProductDetails(products.getProductDetails());
		productWishListObject.setProductSpecification(products.getProductSpecification());
		productWishListObject.setProductVideo(products.getProductVideo());
		productWishListObject.setQRcode(products.getQRcode());
		
		

		productWishListService.addProductWishList(productWishListObject);
		return "redirect:/wishlist/product-wishlist/"+cid;
		}
		

		
	}

	@RequestMapping("/product-wishlist/{cid}")
	public String getWishList(Model model,@PathVariable("cid") int cid) {

		List<ProductWishlist> productWishlists = productWishListRepository.getWishlistByCustomerId(cid);
		
		Customer customer = customerservice.getCustomerById(cid);
		model.addAttribute("customer", customer);
		model.addAttribute("productWishlists", productWishlists);

		return "product-wishlist";
	}
	
	@RequestMapping("/frontendproducts/{id}")
	public String method(Model model, @PathVariable(value = "id") int id) {

		Customer object = customerservice.getCustomerById(id);
		model.addAttribute("customer", object);
		List<PhysicalProducts> products = physicalProductRepository.findLatestProducts();
		model.addAttribute("product", products);

		List<PhysicalCategory> catagorylist = PhysicalCategoryServices.getAllCategory();
		model.addAttribute("catagorylist", catagorylist);

		List<PhysicalSubCategory> subcatagorylist = PhysicalSubCategoryServices.list();
		model.addAttribute("subcatagorylist", subcatagorylist);
		List<PhysicalProducts> productobject = physicalProductService.getAllProduct();
		model.addAttribute("productlist", productobject);
		return "front-end-products";
	}

}
