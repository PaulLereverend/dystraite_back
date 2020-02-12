package com.ynov.dystraite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.dystraite.entities.Utilisateur;
import com.ynov.dystraite.exceptions.UtilisateurNotFoundException;
import com.ynov.dystraite.services.UtilisateurService;

@RestController
public class UtilisateurController {
	@Autowired
	UtilisateurService service;
	
	@RequestMapping(value = "/utilisateur/{email}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Utilisateur getById(@PathVariable String email) {
		return service.getById(email);
	}
	@RequestMapping(value = "/utilisateur", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Utilisateur> getAll() {
		return service.getAll();
	}
	@RequestMapping(value = "/utilisateur", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Utilisateur create(@RequestBody Utilisateur utilisateur) {
		utilisateur.setPassword(service.encode(utilisateur.getPassword()));
		return service.create(utilisateur);
	}
	@RequestMapping(value = "/utilisateur/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Utilisateur delete(@PathVariable int id) {
		return service.delete(id);
	}
	@RequestMapping(value = "/utilisateur/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Utilisateur update(@PathVariable int id, Utilisateur utilisateur) {	
		return service.update(id, utilisateur);
	}
	@RequestMapping(value = "/orthophonistes/near/{email}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Utilisateur> findNearOrtho(@PathVariable String email) {
		return service.getNearOrtho(service.getById(email));
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Utilisateur login(String email, String password) {
		Utilisateur user = service.getById(email);
		if (service.isPasswordMatching(password, user.getPassword())) {
			return user;
		}else {
			throw new UtilisateurNotFoundException("Mauvais mot de passe");
		}
	}
}
