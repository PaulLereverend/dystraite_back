package com.ynov.dystraite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ynov.dystraite.entities.Livre;
import com.ynov.dystraite.repositories.LivreRepository;

@Service
public class LivreService {
	@Autowired
	LivreRepository livreRepo;
	
	public Livre getById(@PathVariable int id) {
		Optional<Livre> livre=livreRepo.findById(id);
		if(!livre.isPresent()) {
			System.out.println("Livre non trouvé");
		}
		return livre.get();
	}
	
	public List<Livre> getAll() {
		return livreRepo.findAll();
	}
	public Livre create(Livre livre) {
		livreRepo.save(livre);
		return livre;
	}
	public Livre delete(@PathVariable int id) {
		Optional<Livre> livre=livreRepo.findById(id);
		if(!livre.isPresent()) {
			System.out.println("Livre non trouvé");
		}
		livreRepo.deleteById(id);
		return livre.get();
	}
	public Livre update(@PathVariable int id, Livre cour) {
		Optional<Livre> livre=livreRepo.findById(id);
		if(!livre.isPresent()) {
			System.out.println("Livre non trouvé");
		}
		Livre c = livre.get();
		
		livreRepo.save(recopie(c, cour));
		return c;
	}
	
	private Livre recopie (Livre livre1, Livre livre2) {
		if (livre2.getTitre() != null){
			livre1.setTitre(livre2.getTitre());
		}
		if (livre2.getDescription() != null){
			livre1.setDescription(livre2.getDescription());
		}
		if (livre2.getLien() != null){
			livre1.setLien(livre2.getLien());
		}
		if (livre2.getVignette() != null){
			livre1.setVignette(livre2.getVignette());
		}
		if (livre2.getTags() != null){
			livre1.setTags(livre2.getTags());
		}
		return livre1;
	}

}
