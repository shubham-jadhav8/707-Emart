package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="Category")
public class CategoryEntity {

	@Id
	@Column(name="category_Id"  )//if we are declaring it as primary key then it will be automatically unique = true & nullable = false
	private long categoryId;
	
	@Column(name="category_Name" , unique=true ,nullable=false )
	private String categoryName;
	
	@Column(name="description" , nullable=false )
	private String description;
	
	@Column(name="category_discount" ,nullable=false )
	private int discount;
	
	@Column(name="category_Gst"  ,nullable=false )
	private int gst;
	
	
	public CategoryEntity() {
		// TODO Auto-generated constructor stub
	}


	public CategoryEntity(long categoryId, String categoryName, String description, int discount, int gst) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.description = description;
		this.discount = discount;
		this.gst = gst;
	}


	public long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}


	public int getGst() {
		return gst;
	}


	public void setGst(int gst) {
		this.gst = gst;
	}


	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", description=" + description
				+ ", discount=" + discount + ", gst=" + gst + "]";
	}
	
	
	
}
