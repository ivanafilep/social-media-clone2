package com.example.demo.exceptions;

public class PostDoesntExistsException extends Exception {

	private String message;

	public PostDoesntExistsException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
