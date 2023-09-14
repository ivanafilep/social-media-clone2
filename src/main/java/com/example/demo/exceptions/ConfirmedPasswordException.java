package com.example.demo.exceptions;

public class ConfirmedPasswordException extends Exception{
	
	private String message;
	
	public ConfirmedPasswordException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
