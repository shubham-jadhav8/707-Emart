package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.ProductEntity;
import com.example.demo.exceptions.ResourceNotExistException;
import com.example.demo.model.ProductModel;
import com.example.demo.service.ProductService;

public class ProductServiceImpl implements ProductService {
@Autowired   //after adding dependency mwthods of model mapper will be present in ioc container but to access that method we will need object and for that we will use autowired here 
	
	private ModelMapper modelMapper;   //it will be available only after adding dependency , but we cant write @component in this class as it is not ours and container dont know about it so we will write custom method in BeanConfig class and it will return modelmapper to container 
	
	
	@Autowired  // to make object of ProductDao , it will throw exception if you dont write @component in ProductDaoImpl class to receive this request ,why ProductDaoImpl ?- because it is class level annotation and will not work on interface
	
	private ProductDao productDao;  //why? - to call methods of ProductDao
	
	@Override
	public int addProduct(ProductModel productModel) {
		// convert model to entity using modelmapper
		//first we have to add depedency of modelmapper 
		
		

		ProductEntity supplerEntity = modelMapper.map(productModel, ProductEntity.class);   //we need map method from modelmapper which will convert source to destination
		
		
		return productDao.addProduct(supplerEntity);
		
		
		
		
		
	}

	@Override
	public ProductModel getProductbyId(long supplerId) {
		ProductModel productModel=null;
		ProductEntity productEntity =productDao.getProductbyId(supplerId);
		
		if(productEntity!=null) {
			productModel = modelMapper.map(productEntity, ProductModel.class);
		}
		else {
			throw new ResourceNotExistException("product not found with id -"+supplerId);
			
		}
		
		
		return productModel;
	}

	

	@Override
	public void updateProduct(ProductModel productModel) {
		
		ProductModel dbproductModel = getProductbyId(productModel.getProductId()); //using getProductbyId method to check if data for that id exist in database or not
		
		if(dbproductModel!=null) {             
			
	ProductEntity productEntity = modelMapper.map(productModel, ProductEntity.class); //model to entity conversion
	
		productDao.updateProduct(productEntity)	;      //giving call to method in dao and passing it data from input
		
	}
	else {
		throw  new ResourceNotExistException("Product does not exist with id- "+productModel.getProductId());
	}
		
		
	}

	@Override
	public void deleteProduct(long productId) {

		
		

			productDao.deleteProduct(productId);
			
		
		
	}
	
	@Override
	public List<ProductModel> getAllProducts() {
	
		List<ProductModel> list=null;
		
		           List<ProductEntity> allProductsEntity =productDao.getAllProducts();
		
		if(!allProductsEntity.isEmpty()) {
			
		list =allProductsEntity.stream().map(entity->modelMapper.map(entity, ProductModel.class)).collect(Collectors.toList());
			
		}
		           
		else  {throw new ResourceNotExistException("products do not exist") ;         
		           
		}
		return list;
	}
	



}
