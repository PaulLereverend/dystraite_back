package com.ynov.dystraite.controllers;

import java.util.List;

import com.ynov.dystraite.entities.Lessons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.dystraite.services.LessonsService;

@RestController
public class LessonsController {
		
		@Autowired
		LessonsService service;
		
		//CRUD 
		@RequestMapping(value = "/lessons/{id}", method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		public Lessons getById(@PathVariable int id) {
			return service.getById(id);
		}
		@RequestMapping(value = "/lessons", method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Lessons> getAll() {
			return service.getAll();
		}
		@RequestMapping(value = "/lessons", method = RequestMethod.POST,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		public Lessons create(Lessons lesson) {
			return service.create(lesson);
		}
		@RequestMapping(value = "/lessons/{id}", method = RequestMethod.DELETE,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		public Lessons delete(@PathVariable int id) {
			return service.delete(id);
		}
		@RequestMapping(value = "/lessons/{id}", method = RequestMethod.PUT,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		public Lessons update(@PathVariable int id, Lessons lesson) {
			return service.update(id, lesson);
		}
		
		@RequestMapping(value = "/lessons/getLast/{limit}", method = RequestMethod.GET,
	            produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Lessons> getLast(@PathVariable int limit) {
			return service.findLastCreated(limit);
		}
		
}
