package com.example.demo.model;

public class ProductModel {

	private long productId;
	private String productName;
	
	
	
	private SupplierModel supplier;
	private CategoryModel category;
	private int productQty;
	private double productPrice;
	private int deliveryCharge;
	
	public ProductModel() {
		// TODO Auto-generated constructor stub
	}

	public ProductModel(long productId, String productName, SupplierModel supplier, CategoryModel category,
			int productQty, double productPrice, int deliveryCharge) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.supplier = supplier;
		this.category = category;
		this.productQty = productQty;
		this.productPrice = productPrice;
		this.deliveryCharge = deliveryCharge;
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

	public SupplierModel getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel category) {
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

	public int getDeliveryCharge() {
		return deliveryCharge;
	}

	public void setDeliveryCharge(int deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}
	
	
	
}
