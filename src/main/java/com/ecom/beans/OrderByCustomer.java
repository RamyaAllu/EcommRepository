package com.ecom.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="OrderByCustomer")
public class OrderByCustomer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OBCId;
	private int orderId;
	private int customerId;
	private int productId;// Primary key
	private String productName;
	private String productType;
	private String productCompany;
	private double productPrice;
	private double totalprice;
	private String productCode;
	private String productModelNumber;
	private char isActive;
	@Lob
	private String image;
	private int quantity;
	private String store;
	public int getOBCId() {
		return OBCId;
	}
	public void setOBCId(int oBCId) {
		OBCId = oBCId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductCompany() {
		return productCompany;
	}
	public void setProductCompany(String productCompany) {
		this.productCompany = productCompany;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductModelNumber() {
		return productModelNumber;
	}
	public void setProductModelNumber(String productModelNumber) {
		this.productModelNumber = productModelNumber;
	}
	public char getIsActive() {
		return isActive;
	}
	public void setIsActive(char isActive) {
		this.isActive = isActive;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}

	

}
