package com.ecom.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="VendorDataList")
public class VendorDataList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vendorDataId;
	private int vendorId;
	
	private String file;
	public int getVendorDataId() {
		return vendorDataId;
	}
	public void setVendorDataId(int vendorDataId) {
		this.vendorDataId = vendorDataId;
	}
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}

}
