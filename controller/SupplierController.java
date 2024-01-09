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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.ResourceAlreadyExistsException;
import com.example.demo.exceptions.ResourceNotExistException;
import com.example.demo.exceptions.SomethingWentWrongException;
import com.example.demo.model.SupplierModel;
import com.example.demo.service.ProductService;
import com.example.demo.service.SupplierService;

import antlr.collections.List;


@RestController

@RequestMapping("/supplier")
public class SupplierController {

	@Autowired          //this annotation will automatically create object of SupplierService which we were created manually in first project by using ProductService service=new ProductServiceimpl(); 
	SupplierService supplierService;   //we need to write @component annotation in SupplierServiceImpl class for this to work as it will tell it is a component of this class9now ioc container knows about SupplierService
	
	@PostMapping("addSupplier")
public ResponseEntity<String> addSupplier(@RequestBody @Valid  SupplierModel supplierModel)
{
		int status=supplierService.addSupplier(supplierModel);
		
		if(status==1) {
			return new ResponseEntity<String>("added successfully", HttpStatus.CREATED);
		}
		else if(status==2) {
			//return new ResponseEntity<String>("supplier already exist,check unique code" , HttpStatus.CONFLICT);
			throw new ResourceAlreadyExistsException("supplier already exists with id "+supplierModel.getSupplierId());
		}
		else {
			throw new SomethingWentWrongException("Something went wrong" );
			
			//ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		
	
		
		
}
	@GetMapping("get-Supplier-by-id/{supplier_id}") //if variable name and place holder name is not same then we need to write like below ("supplier_id")
	public 	ResponseEntity<SupplierModel> getSupplier(@PathVariable ("supplier_id") long supplierId )
	{
		   SupplierModel supplierModel = supplierService.getSupplierbyId(supplierId);
		
		   if (supplierModel!=null)
		   {
			   
			return new	ResponseEntity<SupplierModel>(supplierModel,HttpStatus.FOUND);
			   
		   }
		   else {
			   throw new ResourceNotExistException("Supplier does not exist with id"+supplierId);
		   }
		  
	
		}
	
	   
	@PutMapping("update_Supplier")	   
public ResponseEntity<String> updateSupplier(@RequestBody SupplierModel supplierModel )	
{
		
		supplierService.updateSupplier(supplierModel);
		
		
		
		
	return new ResponseEntity<String>("updated successfully",HttpStatus.OK);
		



}
	
		@DeleteMapping("delete_Supplier")	   
		public ResponseEntity<String> deleteSupplier(@RequestParam long supplierId )	
		{
				
				supplierService.DeleteSupplier(supplierId);
				
				
				
				
			return new ResponseEntity<String>("deleted successfully",HttpStatus.OK);
				
	
	
	
		}
			
	
		// ("supplierId")
		
		
		
		@GetMapping("getAllSuppliers")
		public ResponseEntity<java.util.List<SupplierModel>> getAllSuppliers(){
			
			
			java.util.List<SupplierModel> allSuppliers=supplierService.getAllSuppliers();    
			
			
			return new ResponseEntity<java.util.List<SupplierModel>>(allSuppliers,HttpStatus.FOUND);
			
			
			
			
		}
		
	
}
