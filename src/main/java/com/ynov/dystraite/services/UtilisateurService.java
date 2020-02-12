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
import com.ynov.dystraite.repositories.UtilisateurRepository;

@Service
public class UtilisateurService {
	@Autowired
	UtilisateurRepository utilisateurRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	public Utilisateur getById(@PathVariable int id) {
		Optional<Utilisateur> utilisateur=utilisateurRepo.findById(id);
		if(!utilisateur.isPresent()) {
			System.out.println("Utilisateur non trouvé");
		}
		return utilisateur.get();
	}
	
	public List<Utilisateur> getAll() {
		return utilisateurRepo.findAll();
	}
	
	
	public Utilisateur create(Utilisateur utilisateur) throws UtilisateurExistException{	
		if (utilisateurRepo.findByEmail(utilisateur.getEmail()) != null) {
	        throw new UtilisateurExistException(
	          "Cette adresse email est déjà utilisée :" + utilisateur.getEmail());
	    }
		utilisateur.setMdp(passwordEncoder.encode(utilisateur.getMdp()));
		utilisateurRepo.save(utilisateur);
		return utilisateur;
	}
	public Utilisateur changePassword(String email, String oldPassword, String newPassword) {
		Utilisateur user = utilisateurRepo.findByEmail(email);
		if (user.getMdp() != passwordEncoder.encode(oldPassword)) {
			throw new UtilisateurExistException(
			          "Mot de passe incorrect");
		}
		user.setMdp(passwordEncoder.encode(newPassword));
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
		if (utilisateur2.getAdresse() != null){
			utilisateur1.setAdresse(utilisateur2.getAdresse());
		}
		if (utilisateur2.getVille() != null){
			utilisateur1.setVille(utilisateur2.getVille());
		}
		if (utilisateur2.getCode_postal() != 0){
			utilisateur1.setCode_postal(utilisateur2.getCode_postal());
		}
		if (utilisateur2.getRole() != null){
			utilisateur1.setRole(utilisateur2.getRole());
		}
		if (utilisateur2.getOrthophoniste() != null){
			utilisateur1.setOrthophoniste(utilisateur2.getOrthophoniste());
		}
		
		return utilisateur1;
	}
	public void setPassword(int id, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Strength set as 12

		Optional<Utilisateur> utilisateur=utilisateurRepo.findById(id);
		if(!utilisateur.isPresent()) {
			System.out.println("Utilisateur non trouvé");
		}
		Utilisateur c = utilisateur.get();
		c.setMdp(encoder.encode(password));
		
		utilisateurRepo.save(c);
	}

}
