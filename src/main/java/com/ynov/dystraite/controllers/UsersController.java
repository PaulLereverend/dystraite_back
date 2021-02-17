package com.ynov.dystraite.controllers;

import com.ynov.dystraite.entities.Users;
import com.ynov.dystraite.models.UserAuth;
import com.ynov.dystraite.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UsersController {
	@Autowired
	UsersService service;
	
	@RequestMapping(value = "/users/{email}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Users getById(@PathVariable String email) {
		return service.getById(email);
	}
	@RequestMapping(value = "/users", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Users> getAll() {
		return service.getAll();
	}
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Users delete(@PathVariable int id) {
		return service.delete(id);
	}
	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Users update(@PathVariable int id, Users user) {
		return service.update(id, user);
	}
	@RequestMapping(value = "/speechtherapists/near/{email}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Users> findNearSpeechTherapist(@PathVariable String email) {
		return service.getNearSpeechTherapist(service.getById(email));
	}
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserAuth signUp(HttpServletResponse response, @RequestBody Users user) {
		return service.create(response, user);
	}
}
