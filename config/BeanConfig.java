package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	
	

	@Bean  //it will ensure that return of method goes to IOC container , and it can be written in only configuration class and to specify that we will write @Configuration annotation  
	public ModelMapper mapper()
	{
		return new ModelMapper();
		
	}
	
	
}
