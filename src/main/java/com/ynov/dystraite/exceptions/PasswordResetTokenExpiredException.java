package com.ynov.dystraite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Password reset token expired")
public class PasswordResetTokenExpiredException extends RuntimeException {
	public PasswordResetTokenExpiredException(String message) {
		super(message);
	}
}