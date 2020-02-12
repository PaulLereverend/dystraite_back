package com.ynov.dystraite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ynov.dystraite.entities.Cours;
import com.ynov.dystraite.repositories.CoursRepository;

import javassist.NotFoundException;

@Service
public class CoursService {
	
	@Autowired
	CoursRepository coursRepo;
	
	public Cours getById(@PathVariable int id) {
		Optional<Cours> cours=coursRepo.findById(id);
		if(!cours.isPresent()) {
			System.out.println("Cours non trouvé");
		}
		return cours.get();
	}
	
	public List<Cours> getAll() {
		return coursRepo.findAll();
	}
	public Cours create(Cours cours) {
		coursRepo.save(cours);
		return cours;
	}
	public Cours delete(@PathVariable int id) {
		Optional<Cours> cours=coursRepo.findById(id);
		if(!cours.isPresent()) {
			System.out.println("Cours non trouvé");
		}
		coursRepo.deleteById(id);
		return cours.get();
	}
	public Cours update(@PathVariable int id, Cours cour) {
		Optional<Cours> cours=coursRepo.findById(id);
		if(!cours.isPresent()) {
			System.out.println("Cours non trouvé");
		}
		Cours c = cours.get();
		
		coursRepo.save(recopie(c, cour));
		return c;
	}
	
	private Cours recopie (Cours cours1, Cours cours2) {
		if (cours2.getTitre() != null){
			cours1.setTitre(cours2.getTitre());
		}
		if (cours2.getDescription() != null){
			cours1.setDescription(cours2.getDescription());
		}
		if (cours2.getContenu() != null){
			cours1.setContenu(cours2.getContenu());
		}
		if (cours2.getVignette() != null){
			cours1.setVignette(cours2.getVignette());
		}
		if (cours2.getTags() != null){
			cours1.setTags(cours2.getTags());
		}
		return cours1;
	}
	public List<Cours> findLastCreated(int limit){
		return coursRepo.findLastCreated(limit);
	}


}
