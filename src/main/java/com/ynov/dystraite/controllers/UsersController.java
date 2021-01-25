package com.ynov.dystraite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ynov.dystraite.entities.Tips;
import com.ynov.dystraite.entities.Users;
import com.ynov.dystraite.exceptions.UserNotFoundException;
import com.ynov.dystraite.services.UsersService;

@RestController
@RequestMapping("users")
public class UsersController {
	@Autowired
	UsersService service;
	
	@RequestMapping(value = "/{email}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Users getById(@PathVariable String email) {
		return service.getById(email);
	}
	@RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Users> getAll() {
		return service.getAll();
	}
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Users create(@RequestBody Users user) {
		user.setPassword(service.encode(user.getPassword()));
		return service.create(user);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Users delete(@PathVariable int id) {
		return service.delete(id);
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Users update(@PathVariable int id, Users user) {
		return service.update(id, user);
	}
	@RequestMapping(value = "/speechtherapists/near/{email}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Users> findNearSpeechTherapist(@PathVariable String email) {
		return service.getNearSpeechTherapist(service.getById(email));
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Users login(String email, String password) {
		Users user = service.getById(email);
		if (service.isPasswordMatching(password, user.getPassword())) {
			return user;
		}else {
			throw new UserNotFoundException("Incorrect login/password");
		}
	}
	@RequestMapping(value = "/like", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public Tips like(@RequestBody Tips tip) {
		return this.service.like(null, tip);
	}
	@RequestMapping(value = "/likedTips", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tips> getLikedTips() {
		Users user = new Users();
		return user.getLikedTips();
	}
	@RequestMapping(value = "/tips", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Tips> getTips() {
		Users user = new Users();
		return user.getTips();
	}
	
	
}
