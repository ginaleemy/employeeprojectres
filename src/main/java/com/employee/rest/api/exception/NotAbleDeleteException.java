package com.employee.rest.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class NotAbleDeleteException extends RuntimeException {
	
	public NotAbleDeleteException(String message) {
		super(message);
	}

}
