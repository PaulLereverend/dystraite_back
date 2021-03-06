package com.ynov.dystraite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.dystraite.entities.Books;
import com.ynov.dystraite.services.BooksService;

@RestController
@RequestMapping("books")
public class BooksController {
	
	@Autowired
	BooksService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Books getById(@PathVariable int id) {
		return service.getById(id);
	}
	@RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Books> getAll() {
		return service.getAll();
	}
	/*@RequestMapping(value = "", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Books create(Books book) {
		return service.create(book);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Books delete(@PathVariable int id) {
		return service.delete(id);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Books update(@PathVariable int id, Books book) {
		return service.update(id, book);
	}*/
	@RequestMapping(value = "/getLast/{limit}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Books> getLast(@PathVariable int limit) {
		return service.findLastCreated(limit);
	}
}
