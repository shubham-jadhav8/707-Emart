package com.example.demo.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SupplierModel {

	
	
	@Min(1)
	private long supplierId;
	
	@NotBlank
  @Pattern(regexp = "^[a-zA-Z ]{3,50}$",message="invalid supplierName " )
	private String supplierName;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z ]{3,50}$",message="invalid city  " )
	private String city;
	
    @Min(100000)
    @Max(999999)
	private int postalCode;
    
    
    @Pattern(regexp = "^[a-zA-Z ]{4,50}$",message="invalid country name " )
	private String country;
	
    @Pattern(regexp = "^[1-9][0-9 ]{9}$",message="invalid mobile no , it should contain numbers only   " )
	private String mobileNo;
	
	
	public SupplierModel() {
		// TODO Auto-generated constructor stub
	}


	public SupplierModel(long supplierId, String supplierName, String city, int postalCode, String country,
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
