package com.ynov.dystraite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.dystraite.entities.Cours;
import com.ynov.dystraite.services.CoursService;

@RestController
public class CoursController {	
		
		@Autowired
		CoursService service;
		
		
		@RequestMapping(value = "/cours/get/{id}", method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		public Cours getById(@PathVariable int id) {
			return service.getById(id);
		}
		@RequestMapping(value = "/cours/get", method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Cours> getAll() {
			System.out.println("ss");
			return service.getAll();
		}
		@RequestMapping(value = "/cours/create", method = RequestMethod.POST,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		public Cours create(Cours cours) {
			return service.create(cours);
		}
		@RequestMapping(value = "/cours/delete/{id}", method = RequestMethod.DELETE,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		public Cours delete(@PathVariable int id) {
			return service.delete(id);
		}
		@RequestMapping(value = "/cours/update/{id}", method = RequestMethod.PUT,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		public Cours update(@PathVariable int id, Cours cours) {	
			return service.update(id, cours);
		}
}
