package com.ynov.dystraite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "L'utilisateur existe déjà")
public class UtilisateurExistException extends RuntimeException {
	public UtilisateurExistException(String message) {
		super(message);
	}
}