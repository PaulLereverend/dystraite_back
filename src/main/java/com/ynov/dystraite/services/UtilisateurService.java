package com.ynov.dystraite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ynov.dystraite.entities.Utilisateur;
import com.ynov.dystraite.exceptions.UtilisateurExistException;
import com.ynov.dystraite.exceptions.UtilisateurNotFoundException;
import com.ynov.dystraite.repositories.UtilisateurRepository;

@Service
public class UtilisateurService {
	@Autowired
	UtilisateurRepository utilisateurRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Utilisateur getById(@PathVariable String email) throws UtilisateurNotFoundException {
		Utilisateur utilisateur=utilisateurRepo.findByEmail(email);
		if(utilisateur == null) {
			throw new UtilisateurNotFoundException("L'utilisateur n'a pas été trouvé");
		}
		return utilisateur;
	}
	
	public List<Utilisateur> getAll() {
		return utilisateurRepo.findAll();
	}
	
	
	public Utilisateur create(Utilisateur utilisateur) throws UtilisateurExistException{	
		if (utilisateurRepo.findByEmail(utilisateur.getEmail()) != null) {
	        throw new UtilisateurExistException(
	          "Cette adresse email est déjà utilisée :" + utilisateur.getEmail());
	    }
		utilisateurRepo.save(utilisateur);
		return utilisateur;
	}
	public Utilisateur changePassword(String email, String oldPassword, String newPassword) {
		Utilisateur user = utilisateurRepo.findByEmail(email);
		if (user.getPassword() != this.encode(oldPassword)) {
			throw new UtilisateurExistException(
			          "Mot de passe incorrect");
		}
		user.setPassword(this.encode(newPassword));
		utilisateurRepo.save(user);
		return user;
	}
	public Utilisateur delete(@PathVariable int id) {
		Optional<Utilisateur> utilisateur=utilisateurRepo.findById(id);
		if(!utilisateur.isPresent()) {
			System.out.println("Utilisateur non trouvé");
		}
		utilisateurRepo.deleteById(id);
		return utilisateur.get();
	}
	public Utilisateur update(@PathVariable int id, Utilisateur cour) {
		Optional<Utilisateur> utilisateur=utilisateurRepo.findById(id);
		if(!utilisateur.isPresent()) {
			System.out.println("Utilisateur non trouvé");
		}
		Utilisateur c = utilisateur.get();
		
		utilisateurRepo.save(recopie(c, cour));
		return c;
	}
	
	private Utilisateur recopie (Utilisateur utilisateur1, Utilisateur utilisateur2) {
		if (utilisateur2.getEmail() != null){
			utilisateur1.setEmail(utilisateur2.getEmail());
		}
		if (utilisateur2.getNom() != null){
			utilisateur1.setNom(utilisateur2.getNom());
		}
		if (utilisateur2.getPrenom() != null){
			utilisateur1.setPrenom(utilisateur2.getPrenom());
		}
		if (utilisateur2.getDate_naissance() != null){
			utilisateur1.setDate_naissance(utilisateur2.getDate_naissance());
		}
		if (utilisateur2.getLatitude() != 0){
			utilisateur1.setLatitude(utilisateur2.getLatitude());
		}
		if (utilisateur2.getLongitude() != 0){
			utilisateur1.setLongitude(utilisateur2.getLongitude());
		}
		if (utilisateur2.getVille() != null){
			utilisateur1.setVille(utilisateur2.getVille());
		}
		if (utilisateur2.getRole() != null){
			utilisateur1.setRole(utilisateur2.getRole());
		}
		if (utilisateur2.getOrthophoniste() != null){
			utilisateur1.setOrthophoniste(utilisateur2.getOrthophoniste());
		}
		
		return utilisateur1;
	}
	public void setPassword(String email, String password) {

		Utilisateur utilisateur=utilisateurRepo.findByEmail(email);
		if(utilisateur == null) {
			throw new UtilisateurNotFoundException("L'utilisateur n'a pas été trouvé");
		}
		Utilisateur c = utilisateur;
		c.setPassword(this.encode(password));
		utilisateurRepo.save(c);
	}
	public String encode (String password) {
		return passwordEncoder.encode(password);
	}
	public boolean isPasswordMatching(String password, String password2) {
		return passwordEncoder.matches(password, password2);
	}
	public List<Utilisateur> getNearOrtho(Utilisateur util){
		return utilisateurRepo.findNearest(util.getLatitude(), util.getLongitude());
	}
}
