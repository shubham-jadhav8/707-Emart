package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.exceptions.ResourceAlreadyExistsException;
import com.example.demo.exceptions.ResourceNotExistException;
import com.example.demo.exceptions.SomethingWentWrongException;
import com.example.demo.model.CategoryModel;
import com.example.demo.service.CategoryService;

public class CategoryController {


	@Autowired          //this annotation will automatically create object of CategoryService which we were created manually in first project by using ProductService service=new ProductServiceimpl(); 
	CategoryService categoryService;   //we need to write @component annotation in CategoryServiceImpl class for this to work as it will tell it is a component of this class9now ioc container knows about CategoryService
	
	@PostMapping("addCategory")
public ResponseEntity<String> addCategory(@RequestBody @Valid  CategoryModel categoryModel)
{
		int status=categoryService.addCategory(categoryModel);
		
		if(status==1) {
			return new ResponseEntity<String>("added successfully", HttpStatus.CREATED);
		}
		else if(status==2) {
			//return new ResponseEntity<String>("category already exist,check unique code" , HttpStatus.CONFLICT);
			throw new ResourceAlreadyExistsException("category already exists with id "+categoryModel.getCategoryId());
		}
		else {
			throw new SomethingWentWrongException("Something went wrong" );
			
			//ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		
	
		
		
}
	@GetMapping("get-Category-by-id/{category_id}") //if variable name and place holder name is not same then we need to write like below ("category_id")
	public 	ResponseEntity<CategoryModel> getCategory(@PathVariable ("category_id") long categoryId )
	{
		   CategoryModel categoryModel = categoryService.getCategorybyId(categoryId);
		
		   if (categoryModel!=null)
		   {
			   
			return new	ResponseEntity<CategoryModel>(categoryModel,HttpStatus.FOUND);
			   
		   }
		   else {
			   throw new ResourceNotExistException("Category does not exist with id"+categoryId);
		   }
		  
	
		}
	
	   
	@PutMapping("update_Category")	   
public ResponseEntity<String> updateCategory(@RequestBody CategoryModel categoryModel )	
{
		
		categoryService.updateCategory(categoryModel);
		
		
		
		
	return new ResponseEntity<String>("updated successfully",HttpStatus.OK);
		



}
	
		@DeleteMapping("delete_Category")	   
		public ResponseEntity<String> deleteCategory(@RequestParam long categoryId )	
		{
				
				categoryService.deleteCategory(categoryId);
				
				
				
				
			return new ResponseEntity<String>("deleted successfully",HttpStatus.OK);
				
	
	
	
		}
			
	
		// ("categoryId")
		
		
		
		@GetMapping("getAllCategorys")
		public ResponseEntity<java.util.List<CategoryModel>> getAllCategorys(){
			
			
			java.util.List<CategoryModel> allCategorys=categoryService.getAllCategories();    
			
			
			return new ResponseEntity<java.util.List<CategoryModel>>(allCategorys,HttpStatus.FOUND);
			
			
			
			
		}
	
	
	
}
