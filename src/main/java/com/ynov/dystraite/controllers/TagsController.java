package com.ynov.dystraite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.dystraite.entities.Tags;
import com.ynov.dystraite.services.TagsService;

@RestController
@RequestMapping("tags")
public class TagsController {

	@Autowired
	TagsService service;
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Tags getById(@PathVariable int id) {
		return service.getById(id);
	}
	@RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tags> getAll() {
		return service.getAll();
	}
	/*@RequestMapping(value = "", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Tags create(Tags tags) {
		return service.create(tags);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Tags delete(@PathVariable int id) {
		return service.delete(id);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Tags update(@PathVariable int id, Tags tags) {	
		return service.update(id, tags);
	}*/
}
