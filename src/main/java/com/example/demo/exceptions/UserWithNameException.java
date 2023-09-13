package com.example.demo.exceptions;

public class UserWithNameException extends Exception{
	
private String message;
	
	public UserWithNameException(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
