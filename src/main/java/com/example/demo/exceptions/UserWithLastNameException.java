package com.example.demo.exceptions;

public class UserWithLastNameException extends Exception{
	
private String message;
	
	public UserWithLastNameException(String message) {
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
