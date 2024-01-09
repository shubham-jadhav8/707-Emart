package com.example.demo.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dao.SupplierDao;

import com.example.demo.entity.SupplierEntity;
import com.example.demo.exceptions.ResourceNotExistException;
import com.example.demo.exceptions.SomethingWentWrongException;


@Component
public class SupplierDaoImpl implements SupplierDao {

	//create SessionFactory instance  
	@Autowired       //we dont need to give @component annotation as application.properties will be read every time program runs and its data will be present in container 
	private SessionFactory sessionFactory;   //SessionFactory is combination of Datasource and HybernateProperties 
	
	
	@Override
	public int addSupplier(SupplierEntity supplierEntity) {
		//now we have to add data to database and we will do that by using SessionFactory with following steps
		//create SessionFactory instance  
		//open session from SessionFactory
		//use methods of session to perform operation
		
		
		//open session from SessionFactory
	int status=0;	
		try {
			
			Session session = sessionFactory.openSession();
			
			session.save(supplierEntity);
			
			session.beginTransaction().commit();
			
			status=1;
		} catch (PersistenceException e) {    //PersistenceException occurs when we add duplicate data for unique key hence we will use it for status 2
	         status=2;
		}
		
		
		
		catch (Exception e) {
			e.printStackTrace();
			status=3;
			
		}
		return status;
	}

	@Override
	public SupplierEntity getSupplierbyId(long supplerId) {
		SupplierEntity supplierEntity=null;
		//int a=0; to check exception
try {
	Session session = sessionFactory.openSession();
	
 supplierEntity = session.get(SupplierEntity.class, supplerId);   // where clause of query will alwys be on primary key (i.e. id in this case)
	//a=10/0;
	
} catch (Exception e) {
	e.printStackTrace();
	
	throw new SomethingWentWrongException("something went wrong");
}
		
		
return supplierEntity;
		
	}

	@Override
	public void updateSupplier(SupplierEntity supplierEntity) {
		
		try {
			Session session = sessionFactory.openSession();
			session.update(supplierEntity);
			session.beginTransaction().commit();
			
		} catch (Exception e) {
            e.printStackTrace();
            throw new SomethingWentWrongException("something went wrong while updating");

		}
		
		
		
		
		
	}

	@Override
	public void DeleteSupplier(long supplierId) {
		
	try {
		
		Session session = sessionFactory.openSession();
	
	SupplierEntity dbsupplierEntity = session.get(SupplierEntity.class, supplierId);
	
	if(dbsupplierEntity!=null) { //why we checking it here? because we want object of id from same session 
	session.delete(dbsupplierEntity);        //why we converted supplierId to object(i.e.dbsupplierEntity) -because session.delete method only takes object as i/p and it should be from same session hence we cant use it from other methods 
	session.beginTransaction().commit(); 
	
	}
	else {
		throw new ResourceNotExistException("supplier not found with id -"+supplierId);
	}
		
	} catch (RollbackException e) {  //why> because exception from else was being catched here hence we checked what exception could occur if commit fails and used it here , so it will only catch that exception and not others 

		 throw new SomethingWentWrongException("something went wrong while deleting");
		
		
	}	
		
		
		
	}

	@Override
	public List<SupplierEntity> getAllSuppliers() {
		
		List<SupplierEntity> list=null;
		
		try {
			Session session=sessionFactory.openSession();
			
			Criteria criteria=session.createCriteria(SupplierEntity.class); //why we used criteria?-because session do not have method which directly returns list hence some of the methods are exclusively present in criteria which are not present in session 
			
			//some examples of methods in Criteria -The Criteria API provides a programmatic and type-safe way to build queries, which can be advantageous in scenarios where you need to construct queries dynamically or when you want to create queries without relying on static strings, as is the case with HQL or native SQL queries.
			//criteria.setFirstResult(2);
			//criteria.setMaxResults(3);
			//criteria.add(Restrictions.eq("supplierName", "cc Distributor"));
			list=criteria.list();
			
			//similarly  Restrictions in the Criteria API are used to add conditions or criteria to your query, enabling you to retrieve specific data from the database based on certain conditions. 
			//Filter results: You can apply various conditions to filter the results returned from the database. For instance, using Restrictions.eq(property, value) to filter by equality, Restrictions.between(property, min, max) to filter by a range, Restrictions.like(property, pattern) to perform pattern matching, etc.

//Build complex queries: Restrictions allow you to create more complex queries by combining multiple conditions using logical operators (AND, OR, etc.) with Restrictions.and(), Restrictions.or(), etc. This enables the creation of sophisticated queries based on multiple criteria.

//Dynamic queries: Criteria queries can be built dynamically at runtime. You can add restrictions conditionally, based on user input or changing criteria, to construct queries dynamically rather than having static queries.
//e.g. //criteria.add(Restrictions.eq("supplierName", "cc Distributor"));
		
		} catch (Exception e) {
	 
			e.printStackTrace();
			throw new SomethingWentWrongException("someting went wrong");
		}
	
		
		
		
		
		
		
		
		
		
		
		return list;
	}

}
