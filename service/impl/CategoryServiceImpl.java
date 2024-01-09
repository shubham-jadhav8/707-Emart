package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.CategoryDao;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.exceptions.ResourceNotExistException;
import com.example.demo.model.CategoryModel;
import com.example.demo.model.CategoryModel;
import com.example.demo.service.CategoryService;



public class CategoryServiceImpl implements CategoryService {

@Autowired   //after adding dependency mwthods of model mapper will be present in ioc container but to access that method we will need object and for that we will use autowired here 
	
	private ModelMapper modelMapper;   //it will be available only after adding dependency , but we cant write @component in this class as it is not ours and container dont know about it so we will write custom method in BeanConfig class and it will return modelmapper to container 
	
	
	@Autowired  // to make object of CategoryDao , it will throw exception if you dont write @component in CategoryDaoImpl class to receive this request ,why CategoryDaoImpl ?- because it is class level annotation and will not work on interface
	
	private CategoryDao categoryDao;  //why? - to call methods of CategoryDao
	
	@Override
	public int addCategory(CategoryModel categoryModel) {
		// convert model to entity using modelmapper
		//first we have to add depedency of modelmapper 
		
		

		CategoryEntity supplerEntity = modelMapper.map(categoryModel, CategoryEntity.class);   //we need map method from modelmapper which will convert source to destination
		
		
		return categoryDao.addCategory(supplerEntity);
		
		
		
		
		
	}

	@Override
	public CategoryModel getCategorybyId(long supplerId) {
		CategoryModel categoryModel=null;
		CategoryEntity categoryEntity = categoryDao.getCategorybyId(supplerId);
		
		if(categoryEntity!=null) {
			categoryModel = modelMapper.map(categoryEntity, CategoryModel.class);
		}
		else {
			throw new ResourceNotExistException("category not found with id -"+supplerId);
			
		}
		
		
		return categoryModel;
	}

	

	@Override
	public void updateCategory(CategoryModel categoryModel) {
		
		CategoryModel dbcategoryModel = getCategorybyId(categoryModel.getCategoryId()); //using getCategorybyId method to check if data for that id exist in database or not
		
		if(dbcategoryModel!=null) {             
			
	CategoryEntity categoryEntity = modelMapper.map(categoryModel, CategoryEntity.class); //model to entity conversion
	
		 categoryDao.updateCategory(categoryEntity)	;      //giving call to method in dao and passing it data from input
		
	}
	else {
		throw  new ResourceNotExistException("Category does not exist with id- "+categoryModel.getCategoryId());
	}
		
		
	}

	@Override
	public void deleteCategory(long categoryId) {

		
		

			categoryDao.deleteCategory(categoryId);
			
		
		
	}
	
	@Override
	public List<CategoryModel> getAllCategories() {
	
		List<CategoryModel> list=null;
		
		           List<CategoryEntity> allCategorysEntity = categoryDao.getAllCategories();
		
		if(!allCategorysEntity.isEmpty()) {
			
		list =allCategorysEntity.stream().map(entity->modelMapper.map(entity, CategoryModel.class)).collect(Collectors.toList());
			
		}
		           
		else  {throw new ResourceNotExistException("categorys do not exist") ;         
		           
		}
		return list;
	}
	


}
