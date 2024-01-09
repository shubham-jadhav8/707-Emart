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
import com.example.demo.model.ProductModel;
import com.example.demo.service.ProductService;

public class ProductController {


	@Autowired          //this annotation will automatically create object of ProductService which we were created manually in first project by using ProductService service=new ProductServiceimpl(); 
	ProductService productService;   //we need to write @component annotation in ProductServiceImpl class for this to work as it will tell it is a component of this class9now ioc container knows about ProductService
	
	@PostMapping("addProduct")
public ResponseEntity<String> addProduct(@RequestBody @Valid  ProductModel productModel)
{
		int status=productService.addProduct(productModel);
		
		if(status==1) {
			return new ResponseEntity<String>("added successfully", HttpStatus.CREATED);
		}
		else if(status==2) {
			//return new ResponseEntity<String>("product already exist,check unique code" , HttpStatus.CONFLICT);
			throw new ResourceAlreadyExistsException("product already exists with id "+productModel.getProductId());
		}
		else {
			throw new SomethingWentWrongException("Something went wrong" );
			
			//ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		
	
		
		
}
	@GetMapping("get-Product-by-id/{product_id}") //if variable name and place holder name is not same then we need to write like below ("product_id")
	public 	ResponseEntity<ProductModel> getProduct(@PathVariable ("product_id") long productId )
	{
		   ProductModel productModel = productService.getProductbyId(productId);
		
		   if (productModel!=null)
		   {
			   
			return new	ResponseEntity<ProductModel>(productModel,HttpStatus.FOUND);
			   
		   }
		   else {
			   throw new ResourceNotExistException("Product does not exist with id"+productId);
		   }
		  
	
		}
	
	   
	@PutMapping("update_Product")	   
public ResponseEntity<String> updateProduct(@RequestBody ProductModel productModel )	
{
		
		productService.updateProduct(productModel);
		
		
		
		
	return new ResponseEntity<String>("updated successfully",HttpStatus.OK);
		



}
	
		@DeleteMapping("delete_Product")	   
		public ResponseEntity<String> deleteProduct(@RequestParam long productId )	
		{
				
				productService.deleteProduct(productId);
				
				
				
				
			return new ResponseEntity<String>("deleted successfully",HttpStatus.OK);
				
	
	
	
		}
			
	
		// ("productId")
		
		
		
		@GetMapping("getAllProducts")
		public ResponseEntity<java.util.List<ProductModel>> getAllProducts(){
			
			
			java.util.List<ProductModel> allProducts=productService.getAllProducts();    
			
			
			return new ResponseEntity<java.util.List<ProductModel>>(allProducts,HttpStatus.FOUND);
			
			
			
			
		}
	

}
