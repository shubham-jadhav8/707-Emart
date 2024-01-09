package com.example.demo.dao.impl;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.CategoryDao;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.exceptions.ResourceNotExistException;
import com.example.demo.exceptions.SomethingWentWrongException;

public class CategoryDaoImpl implements CategoryDao {

	//create SessionFactory instance  
	@Autowired       //we dont need to give @component annotation as application.properties will be read every time program runs and its data will be present in container 
	private SessionFactory sessionFactory;   //SessionFactory is combination of Datasource and HybernateProperties 
	
	
	@Override
	public int addCategory(CategoryEntity categoryEntity) {
		//now we have to add data to database and we will do that by using SessionFactory with following steps
		//create SessionFactory instance  
		//open session from SessionFactory
		//use methods of session to perform operation
		
		
		//open session from SessionFactory
	int status=0;	
		try {
			
			Session session = sessionFactory.openSession();
			
			session.save(categoryEntity);
			
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
	public CategoryEntity getCategorybyId(long categoryId) {
		CategoryEntity categoryEntity=null;
		//int a=0; to check exception
try {
	Session session = sessionFactory.openSession();
	
 categoryEntity = session.get(CategoryEntity.class, categoryId);   // where clause of query will alwys be on primary key (i.e. id in this case)
	//a=10/0;
	
} catch (Exception e) {
	e.printStackTrace();
	
	throw new SomethingWentWrongException("something went wrong");
}
		
		
return categoryEntity;
		
	}

	@Override
	public void updateCategory(CategoryEntity categoryEntity) {
		
		try {
			Session session = sessionFactory.openSession();
			session.update(categoryEntity);
			session.beginTransaction().commit();
			
		} catch (Exception e) {
            e.printStackTrace();
            throw new SomethingWentWrongException("something went wrong while updating");

		}
		
		
		
		
		
	}

	@Override
	public void deleteCategory(long categoryId) {
		
	try {
		
		Session session = sessionFactory.openSession();
	
	CategoryEntity dbcategoryEntity = session.get(CategoryEntity.class, categoryId);
	
	if(dbcategoryEntity!=null) { //why we checking it here? because we want object of id from same session 
	session.delete(dbcategoryEntity);        //why we converted categoryId to object(i.e.dbcategoryEntity) -because session.delete method only takes object as i/p and it should be from same session hence we cant use it from other methods 
	session.beginTransaction().commit(); 
	
	}
	else {
		throw new ResourceNotExistException("category not found with id -"+categoryId);
	}
		
	} catch (RollbackException e) {  //why> because exception from else was being catched here hence we checked what exception could occur if commit fails and used it here , so it will only catch that exception and not others 

		 throw new SomethingWentWrongException("something went wrong while deleting");
		
		
	}	
		
		
		
	}

	@Override
	public List<CategoryEntity> getAllCategories() {
		
		List<CategoryEntity> list=null;
		
		try {
			Session session=sessionFactory.openSession();
			
			Criteria criteria=session.createCriteria(CategoryEntity.class); //why we used criteria?-because session do not have method which directly returns list hence some of the methods are exclusively present in criteria which are not present in session 
			
			//some examples of methods in Criteria -The Criteria API provides a programmatic and type-safe way to build queries, which can be advantageous in scenarios where you need to construct queries dynamically or when you want to create queries without relying on static strings, as is the case with HQL or native SQL queries.
			//criteria.setFirstResult(2);
			//criteria.setMaxResults(3);
			//criteria.add(Restrictions.eq("categoryName", "cc Distributor"));
			list=criteria.list();
			
			//similarly  Restrictions in the Criteria API are used to add conditions or criteria to your query, enabling you to retrieve specific data from the database based on certain conditions. 
			//Filter results: You can apply various conditions to filter the results returned from the database. For instance, using Restrictions.eq(property, value) to filter by equality, Restrictions.between(property, min, max) to filter by a range, Restrictions.like(property, pattern) to perform pattern matching, etc.

//Build complex queries: Restrictions allow you to create more complex queries by combining multiple conditions using logical operators (AND, OR, etc.) with Restrictions.and(), Restrictions.or(), etc. This enables the creation of sophisticated queries based on multiple criteria.

//Dynamic queries: Criteria queries can be built dynamically at runtime. You can add restrictions conditionally, based on user input or changing criteria, to construct queries dynamically rather than having static queries.
//e.g. //criteria.add(Restrictions.eq("categoryName", "cc Distributor"));
		
		} catch (Exception e) {
	 
			e.printStackTrace();
			throw new SomethingWentWrongException("someting went wrong");
		}
	
		
		
		
		
		
		
		
		
		
		
		return list;
	}
}
