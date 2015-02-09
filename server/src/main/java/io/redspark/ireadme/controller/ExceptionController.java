package io.redspark.ireadme.controller;

import io.redspark.ireadme.dto.ExceptionDTO;
import io.redspark.ireadme.exceptions.IReadmeException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {

	@ResponseBody
	@ExceptionHandler(IReadmeException.class)
	public ExceptionDTO exceptionHandler(IReadmeException exception, HttpServletResponse response){
		response.setStatus(exception.getStatusCode());
		return new ExceptionDTO(exception.getMessage(), exception.getStackTrace().toString());
	}
}
