package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;








@Entity
@Table(name="supplier")
public class SupplierEntity {

	
	
	@Id  // to designate the primary key
	@Column(name="supplier_Id" ) //if we are declaring it as primary key then it will be automatically unique = true & nullable = false
	private long supplierId;
	
	@Column(name="supplier_name" ,unique=true, nullable=false)
	private String supplierName;
	
	@Column(name="supplier_city" , nullable=false)
	private String city;
	
	@Column(name="supplier_postalCode" , nullable=false)
	private int postalCode;
	
	@Column(name="supplier_country" , nullable=false)
	private String country;
	    
	@Column(name="supplier_mobileNo" ,unique=true, nullable=false)
	private String mobileNo;
	
	
	
	

	public SupplierEntity() {
		// TODO Auto-generated constructor stub
	}


	public SupplierEntity(long supplierId, String supplierName, String city, int postalCode, String country,
			String mobileNo) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
		this.mobileNo = mobileNo;
	}


	public long getSupplierId() {
		return supplierId;
	}


	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}


	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public int getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	@Override
	public String toString() {
		return "Supplier [supplierId=" + supplierId + ", supplierName=" + supplierName + ", city=" + city
				+ ", postalCode=" + postalCode + ", country=" + country + ", mobileNo=" + mobileNo + "]";
	}
	
	
}
