package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CategoryModel;

public interface CategoryService {

	
	public int addCategory(CategoryModel categoryModel);
	
	public CategoryModel getCategorybyId(long categoryId);
	
	public List<CategoryModel> getAllCategories();
	
	public void updateCategory(CategoryModel categoryModel);
	
	public void deleteCategory(long categoryId);
	
}
