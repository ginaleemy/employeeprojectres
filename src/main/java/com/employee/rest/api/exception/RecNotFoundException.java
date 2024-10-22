package com.employee.rest.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RecNotFoundException extends RuntimeException {
	
	public RecNotFoundException(String message) {
		super(message);
	}

}
