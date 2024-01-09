package com.example.demo.exceptions;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice    // this annotation will bring all exception from controller to this class and they will be handled accordingly
public class GlobalExceptionHandler {

	Map<String,String>errorMap=new HashMap<>();
	
	//annotation to target particular exception(inbuilt/custom)
	@ExceptionHandler(MethodArgumentNotValidException.class)  
	public Map<String, String> methodArgumentNotValidExceptionMethod(MethodArgumentNotValidException ex ) //ex will hold all info about exception
	{
		
		
		
		List<FieldError> variablesWithError = ex.getFieldErrors();
		
		for (FieldError fieldError : variablesWithError) {
			
			String field = fieldError.getField();
						
			
			String defaultMessage = fieldError.getDefaultMessage();
			
			errorMap.put(field, defaultMessage);
			
			
		}
		return errorMap;
		
				
	}
	
	
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	@ResponseStatus(code=HttpStatus.CONFLICT)
	public CustomizedExceptionResponse resourceAlreadyExistsException(ResourceAlreadyExistsException ex)
	{
		
		
		CustomizedExceptionResponse response= new CustomizedExceptionResponse();
		
		response.setDefaultMessage(ex.getMessage());
		
		response.setStatus(HttpStatus.CONFLICT);
		
		response.setDate(LocalDateTime.now());
		
	//String message=ex.getMessage(); //we got this method because we extended our custom exception class with RuntimeException hence got all its methods 
		
		return response;
		
		
	}
	
	
	@ExceptionHandler(SomethingWentWrongException.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	public CustomizedExceptionResponse somethingWentWrongException(SomethingWentWrongException ex)
	{
		
	CustomizedExceptionResponse response= new CustomizedExceptionResponse();
		
		response.setDefaultMessage(ex.getMessage());
		
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		
		response.setDate(LocalDateTime.now());
		
		
		return response;
		
		
		
		
		
		
	}
	
	@ExceptionHandler(ResourceNotExistException.class)
	@ResponseStatus(code=HttpStatus.OK)
	public CustomizedExceptionResponse resourceNotExistException(ResourceNotExistException ex)
	{
		
		
		CustomizedExceptionResponse response= new CustomizedExceptionResponse();
		
		response.setDefaultMessage(ex.getMessage());
		
		response.setStatus(HttpStatus.OK);
		
		response.setDate(LocalDateTime.now());
		
	//String message=ex.getMessage(); //we got this method because we extended our custom exception class with RuntimeException hence got all its methods 
		
		return response;
		
		
	}
	
	
}
