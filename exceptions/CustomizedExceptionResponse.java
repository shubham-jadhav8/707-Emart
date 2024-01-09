package com.example.demo.exceptions;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class CustomizedExceptionResponse {
	
	
	private String defaultMessage;
	private HttpStatus status;
	private LocalDateTime date;
	
	
	public CustomizedExceptionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getDefaultMessage() {
		return defaultMessage;
	}


	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus conflict) {
		this.status = conflict;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime localDateTime) {
		this.date = localDateTime;
	}
	
 
	
}
