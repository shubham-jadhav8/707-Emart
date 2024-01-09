package com.example.demo.dao;

import java.util.List;

import com.example.demo.entity.CategoryEntity;

public interface CategoryDao {

	
	public int addCategory(CategoryEntity categoryEntity  );
	
	public CategoryEntity getCategorybyId(long supplerId);
	
	public List<CategoryEntity> getAllCategories();
	
	public void updateCategory(CategoryEntity categoryEntity);
	
	public void deleteCategory(long categoryId);
	
}
