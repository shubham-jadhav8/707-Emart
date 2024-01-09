package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.entity.SupplierEntity;
import com.example.demo.model.SupplierModel;


public interface SupplierDao {

	
	public int addSupplier(SupplierEntity supplierEntity  );
	
	public SupplierEntity getSupplierbyId(long supplerId);
	
	public List<SupplierEntity> getAllSuppliers();
	
	public void updateSupplier(SupplierEntity supplierEntity);
	
	public void DeleteSupplier(long supplierId);
	
	
}
