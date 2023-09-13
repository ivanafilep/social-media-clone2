package com.example.demo.exceptions;

public class UserDoesntExist extends Exception {
	
private String message;
	
	public UserDoesntExist(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
