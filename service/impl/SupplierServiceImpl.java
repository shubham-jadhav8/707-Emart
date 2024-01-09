package com.example.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.SupplierDao;
import com.example.demo.entity.SupplierEntity;
import com.example.demo.exceptions.ResourceNotExistException;
import com.example.demo.model.SupplierModel;
import com.example.demo.service.SupplierService;

@Component
public class SupplierServiceImpl implements SupplierService {

	@Autowired   //after adding dependency mwthods of model mapper will be present in ioc container but to access that method we will need object and for that we will use autowired here 
	
	private ModelMapper modelMapper;   //it will be available only after adding dependency , but we cant write @component in this class as it is not ours and container dont know about it so we will write custom method in BeanConfig class and it will return modelmapper to container 
	
	
	@Autowired  // to make object of SupplierDao , it will throw exception if you dont write @component in SupplierDaoImpl class to receive this request ,why SupplierDaoImpl ?- because it is class level annotation and will not work on interface
	
	private SupplierDao supplierDao;  //why? - to call methods of SupplierDao
	
	@Override
	public int addSupplier(SupplierModel supplierModel) {
		// convert model to entity using modelmapper
		//first we have to add depedency of modelmapper 
		
		

		SupplierEntity supplerEntity = modelMapper.map(supplierModel, SupplierEntity.class);   //we need map method from modelmapper which will convert source to destination
		
		
		return supplierDao.addSupplier(supplerEntity);
		
		
		
		
		
	}

	@Override
	public SupplierModel getSupplierbyId(long supplerId) {
		SupplierModel supplierModel=null;
		SupplierEntity supplierEntity = supplierDao.getSupplierbyId(supplerId);
		
		if(supplierEntity!=null) {
			supplierModel = modelMapper.map(supplierEntity, SupplierModel.class);
		}
		else {
			throw new ResourceNotExistException("supplier not found with id -"+supplerId);
			
		}
		
		
		return supplierModel;
	}

	

	@Override
	public void updateSupplier(SupplierModel supplierModel) {
		
		SupplierModel dbsupplierModel = getSupplierbyId(supplierModel.getSupplierId()); //using getSupplierbyId method to check if data for that id exist in database or not
		
		if(dbsupplierModel!=null) {             
			
	SupplierEntity supplierEntity = modelMapper.map(supplierModel, SupplierEntity.class); //model to entity conversion
	
		 supplierDao.updateSupplier(supplierEntity)	;      //giving call to method in dao and passing it data from input
		
	}
	else {
		throw  new ResourceNotExistException("Supplier does not exist with id- "+supplierModel.getSupplierId());
	}
		
		
	}

	@Override
	public void DeleteSupplier(long supplierId) {

		
		

			supplierDao.DeleteSupplier(supplierId);
			
		
		
	}
	
	@Override
	public List<SupplierModel> getAllSuppliers() {
	
		List<SupplierModel> list=null;
		
		           List<SupplierEntity> allSuppliersEntity = supplierDao.getAllSuppliers();
		
		if(!allSuppliersEntity.isEmpty()) {
			
		list =allSuppliersEntity.stream().map(entity->modelMapper.map(entity, SupplierModel.class)).collect(Collectors.toList());
			
		}
		           
		else  {throw new ResourceNotExistException("suppliers do not exist") ;         
		           
		}
		return list;
	}
	

}
