package com.ecom.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Store")
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bID;
	private String storeName;
	
    private int storeID;
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String storeMo;
	private String  country;
	private String   Address;
	private String   town;
	 
	public int getBranchID() {
		return bID;
	}
	public void setBranchID(int bID) {
		this. bID = bID;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName1) {
		storeName = storeName1;
	}
	public int getStoreID() {
		return storeID;
	}
	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}
	public String getStoreMo() {
		return storeMo;
	}
	public void setStoreMo(String storeMo) {
		this.storeMo = storeMo;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPinCode() {
		return pinCode;
	}
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
	public Character getIsActive() {
		return isActive;
	}
	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	private String   state;
	private int   pinCode ;
	@Column(columnDefinition = "character(1) default 'Y'::bpchar")
	private Character isActive;
	@Column(columnDefinition = "varchar(45)")
	private String createdBy;
	@Column(columnDefinition = "varchar(45)")
	private String updatedBy;
	@Column(columnDefinition = "date default now()")
	private Date createdDate;
	@Column(name = "latitude")
	private double  latitude;



	@Column(name = "longitude")
	private double longitude;
	private Date updatedDate;
	
 

 }
