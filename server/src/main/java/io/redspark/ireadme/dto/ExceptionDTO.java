package io.redspark.ireadme.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExceptionDTO {

	private String message;
	private String stacktrace;
	
	public ExceptionDTO() {
		super();
	}

	public ExceptionDTO(String message, String stacktrace) {
		super();
		this.message = message;
		this.stacktrace = stacktrace;
	}
}
