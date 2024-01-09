package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ProductModel;

public interface ProductService {

	public int addProduct(ProductModel productModel);
	
	public ProductModel getProductbyId(long productId);
	
	public List<ProductModel> getAllProducts();
	
	public void updateProduct(ProductModel productModel);
	
	public void deleteProduct(long productId);
	
}
