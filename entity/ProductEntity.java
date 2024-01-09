package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class ProductEntity {

	
	
	
	
	@Id
	@Column(name="product_Id")  //if we are declaring it as primary key then it will be automatically unique = true & nullable = false
	private long productId;
	
	@Column(name="product_Name" , unique = true , nullable = false)
	private String productName;
	
	
	@OneToOne //as it will be connected to supplier table
	private SupplierEntity supplier;
	
	@OneToOne
	private CategoryEntity category;
	
	@Column(name="product_Qty" , unique = false )
	private int productQty;
	
	@Column(name="product_Price" , unique = false )
	private double productPrice;
	
	@Column(name="deliveryCharges" , unique = false )
	private int deliveryCharges;
	
	public ProductEntity() {
		// TODO Auto-generated constructor stub
	}

	public ProductEntity(long productId, String productName, SupplierEntity supplier, CategoryEntity category,
			int productQty, double productPrice, int deliveryCharge) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.supplier = supplier;
		this.category = category;
		this.productQty = productQty;
		this.productPrice = productPrice;
		this.deliveryCharges = deliveryCharges;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public SupplierEntity getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierEntity supplier) {
		this.supplier = supplier;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(int deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}
	
	
	
}
