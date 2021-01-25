package com.ynov.dystraite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ynov.dystraite.entities.Tips;
import com.ynov.dystraite.entities.Users;
import com.ynov.dystraite.exceptions.UserExistsException;
import com.ynov.dystraite.exceptions.UserNotFoundException;
import com.ynov.dystraite.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	UsersRepository usersRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Users getById(@PathVariable String email) throws UserNotFoundException {
		Users user = usersRepo.findByEmail(email);
		if(user == null) {
			throw new UserNotFoundException("User not found");
		}
		return user;
	}
	
	public List<Users> getAll() {
		return usersRepo.findAll();
	}
	
	
	public Users create(Users user) throws UserExistsException {
		if (usersRepo.findByEmail(user.getEmail()) != null) {
	        throw new UserExistsException(
	          "This email address is already used: " + user.getEmail());
	    }
		usersRepo.save(user);
		return user;
	}
	public Users changePassword(String email, String oldPassword, String newPassword) {
		Users user = usersRepo.findByEmail(email);
		if (user.getPassword() != this.encode(oldPassword)) {
			throw new UserExistsException(
			          "Incorrect password");
		}
		user.setPassword(this.encode(newPassword));
		usersRepo.save(user);
		return user;
	}
	public Users delete(@PathVariable int id) {
		Optional<Users> user = usersRepo.findById(id);
		if(!user.isPresent()) {
			System.out.println("User not found");
		}
		usersRepo.deleteById(id);
		return user.get();
	}
	public Users update(@PathVariable int id, Users newUser) {
		Optional<Users> user= usersRepo.findById(id);
		if(!user.isPresent()) {
			System.out.println("User not found");
		}
		Users u = user.get();
		
		usersRepo.save(copy(u, newUser));
		return u;
	}
	public Tips like(Users user, Tips tip) {
		user.getLikedTips().add(tip);
		this.usersRepo.save(user);
		return tip;
	}
	
	private Users copy(Users user1, Users user2) {
		if (user2.getEmail() != null){
			user1.setEmail(user2.getEmail());
		}
		if (user2.getLastname() != null){
			user1.setLastname(user2.getLastname());
		}
		if (user2.getFirstname() != null){
			user1.setFirstname(user2.getFirstname());
		}
		if (user2.getBirthdate() != null){
			user1.setBirthdate(user2.getBirthdate());
		}
		if (user2.getLatitude() != 0){
			user1.setLatitude(user2.getLatitude());
		}
		if (user2.getLongitude() != 0){
			user1.setLongitude(user2.getLongitude());
		}
		if (user2.getCity() != null){
			user1.setCity(user2.getCity());
		}
		if (user2.getRole() != null){
			user1.setRole(user2.getRole());
		}
		if (user2.getSpeechTherapist() != null){
			user1.setSpeechTherapist(user2.getSpeechTherapist());
		}
		
		return user1;
	}
	public void setPassword(String email, String password) {

		Users user = usersRepo.findByEmail(email);
		if(user == null) {
			throw new UserNotFoundException("User not found");
		}
		Users u = user;
		u.setPassword(this.encode(password));
		usersRepo.save(u);
	}
	public String encode (String password) {
		return passwordEncoder.encode(password);
	}
	public boolean isPasswordMatching(String password, String password2) {
		return passwordEncoder.matches(password, password2);
	}
	public List<Users> getNearSpeechTherapist(Users util){
		return usersRepo.findNearest(util.getLatitude(), util.getLongitude());
	}
}
