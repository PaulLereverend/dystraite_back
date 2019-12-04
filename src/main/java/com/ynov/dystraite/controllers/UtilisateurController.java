package com.ynov.dystraite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynov.dystraite.entities.Utilisateur;
import com.ynov.dystraite.services.UtilisateurService;

public class UtilisateurController {
	@Autowired
	UtilisateurService service;
	
	
	@RequestMapping(value = "/utilisateur/get/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Utilisateur getById(@PathVariable int id) {
		return service.getById(id);
	}
	@RequestMapping(value = "/utilisateur/get", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Utilisateur> getAll() {
		return service.getAll();
	}
	@RequestMapping(value = "/utilisateur/create", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Utilisateur create(Utilisateur utilisateur) {
		return service.create(utilisateur);
	}
	@RequestMapping(value = "/utilisateur/delete/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Utilisateur delete(@PathVariable int id) {
		return service.delete(id);
	}
	@RequestMapping(value = "/utilisateur/update/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Utilisateur update(@PathVariable int id, Utilisateur utilisateur) {	
		return service.update(id, utilisateur);
	}
}
