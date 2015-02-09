package io.redspark.ireadme.exceptions;

import org.springframework.http.HttpStatus;

public class IReadmeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int statusCode;
	private String message;

	public IReadmeException() {
		super();
	}

	public IReadmeException(HttpStatus status, String message) {
		super();
		this.statusCode = status.value();
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getMessage() {
		return message;
	}
	
	
}
