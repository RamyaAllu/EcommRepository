package com.ecom.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "PhysicalProducts")
public class PhysicalProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int productId;
	private String productName;
	private String productRating;
	private double productMRPPrice;
	private double productDiscountPrice;
	private String productSize;
	private String productModelNumber;
	private String productCompany;
	private String storeName;

	private String productCategory;
	private String ProductSubCategory;
	@Column(name = "productShippingInformation", nullable = true,  length = 1000)
	private String productShippingInformation;
	@Column(name = "productDetails", nullable = true,  length = 1000)
	private String productDetails;
	@Column(name = "productSpecification", nullable = true,  length = 1000)
	private String productSpecification;
	@Column(name = "productDescription", nullable = true,  length = 1000)
	private String productDescription;
	@Lob
	private String productVideo;
	private String productCode;
	@Lob
	private String productImage;
	@Lob
	private String QRcode;
	private char isactive;
	private int createdby;
	private LocalDate created;
	private int updatedby;
	private LocalDate updated;

	
	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductSubCategory() {
		return ProductSubCategory;
	}

	public void setProductSubCategory(String productSubCategory) {
		ProductSubCategory = productSubCategory;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	

	public String getProductCompany() {
		return productCompany;
	}

	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public char getIsactive() {
		return isactive;
	}

	public void setIsactive(char isactive) {
		this.isactive = isactive;
	}

	public int getCreatedby() {
		return createdby;
	}

	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public int getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}

	public LocalDate getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDate updated) {
		this.updated = updated;
	}

	public double getProductMRPPrice() {
		return productMRPPrice;
	}

	public void setProductMRPPrice(double productMRPPrice) {
		this.productMRPPrice = productMRPPrice;
	}

	public double getProductDiscountPrice() {
		return productDiscountPrice;
	}

	public void setProductDiscountPrice(double productDiscountPrice) {
		this.productDiscountPrice = productDiscountPrice;
	}

	public String getProductModelNumber() {
		return productModelNumber;
	}

	public void setProductModelNumber(String productModelNumber) {
		this.productModelNumber = productModelNumber;
	}

	public String getQRcode() {
		return QRcode;
	}

	public void setQRcode(String qRcode) {
		QRcode = qRcode;
	}

	public String getProductRating() {
		return productRating;
	}

	public void setProductRating(String productRating) {
		this.productRating = productRating;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public String getProductShippingInformation() {
		return productShippingInformation;
	}

	public void setProductShippingInformation(String productShippingInformation) {
		this.productShippingInformation = productShippingInformation;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public String getProductSpecification() {
		return productSpecification;
	}

	public void setProductSpecification(String productSpecification) {
		this.productSpecification = productSpecification;
	}

	public String getProductVideo() {
		return productVideo;
	}

	public void setProductVideo(String productVideo) {
		this.productVideo = productVideo;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
