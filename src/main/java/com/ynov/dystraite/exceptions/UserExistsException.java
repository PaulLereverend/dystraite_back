package com.ynov.dystraite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User already exists")
public class UserExistsException extends RuntimeException {
	public UserExistsException(String message) {
		super(message);
	}
}