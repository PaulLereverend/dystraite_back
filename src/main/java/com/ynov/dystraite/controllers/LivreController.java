package com.ynov.dystraite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.dystraite.entities.Cours;
import com.ynov.dystraite.entities.Livre;
import com.ynov.dystraite.services.LivreService;

@RestController
public class LivreController {
	
	@Autowired
	LivreService service;
	
	@RequestMapping(value = "/livre/get/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Livre getById(@PathVariable int id) {
		return service.getById(id);
	}
	@RequestMapping(value = "/livre/get", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Livre> getAll() {
		System.out.println("ss");
		return service.getAll();
	}
	@RequestMapping(value = "/livre/create", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Livre create(Livre livre) {
		return service.create(livre);
	}
	@RequestMapping(value = "/livre/delete/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Livre delete(@PathVariable int id) {
		return service.delete(id);
	}
	@RequestMapping(value = "/livre/update/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Livre update(@PathVariable int id, Livre livre) {	
		return service.update(id, livre);
	}
	@RequestMapping(value = "/livre/getLast/{limit}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Livre> getLast(@PathVariable int limit) {
		return service.findLastCreated(limit);
	}
}
