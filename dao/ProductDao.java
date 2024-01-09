package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.ProductEntity;

public interface ProductDao {

	public int addProduct(ProductEntity productEntity  );
	
	public ProductEntity getProductbyId(long supplerId);
	
	public List<ProductEntity> getAllProducts();
	
	public void updateProduct(ProductEntity productEntity);
	
	public void deleteProduct(long productId);
	
}
