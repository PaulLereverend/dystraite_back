package com.ynov.dystraite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Password reset token not found")
public class PasswordResetTokenNotFoundException extends RuntimeException {
	public PasswordResetTokenNotFoundException(String message) {
		super(message);
	}
}