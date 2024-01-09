package com.example.demo.service;

import java.util.List;



import com.example.demo.model.SupplierModel;


public interface SupplierService {

	public int addSupplier(SupplierModel supplierModel);
	
	public SupplierModel getSupplierbyId(long supplerId);
	
	public List<SupplierModel> getAllSuppliers();
	
	public void updateSupplier(SupplierModel supplierModel);
	
	public void DeleteSupplier(long supplierId);
}
