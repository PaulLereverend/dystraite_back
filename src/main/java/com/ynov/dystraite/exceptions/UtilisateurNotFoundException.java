package com.ynov.dystraite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "L'utilisateur n'a pas été trouvé")
public class UtilisateurNotFoundException extends RuntimeException {
	public UtilisateurNotFoundException(String message) {
		super(message);
	}
}