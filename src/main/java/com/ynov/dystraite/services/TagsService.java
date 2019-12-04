package com.ynov.dystraite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ynov.dystraite.entities.Tags;
import com.ynov.dystraite.repositories.TagsRepository;

@Service
public class TagsService {
	
	@Autowired
	TagsRepository tagsRepo;
	
	public Tags getById(@PathVariable int id) {
		Optional<Tags> tags=tagsRepo.findById(id);
		if(!tags.isPresent()) {
			System.out.println("Tags non trouvé");
		}
		return tags.get();
	}
	
	public List<Tags> getAll() {
		return tagsRepo.findAll();
	}
	public Tags create(Tags tags) {
		tagsRepo.save(tags);
		return tags;
	}
	public Tags delete(@PathVariable int id) {
		Optional<Tags> tags=tagsRepo.findById(id);
		if(!tags.isPresent()) {
			System.out.println("Tags non trouvé");
		}
		tagsRepo.deleteById(id);
		return tags.get();
	}
	public Tags update(@PathVariable int id, Tags cour) {
		Optional<Tags> tags=tagsRepo.findById(id);
		if(!tags.isPresent()) {
			System.out.println("Tags non trouvé");
		}
		Tags c = tags.get();
		
		tagsRepo.save(recopie(c, cour));
		return c;
	}
	
	private Tags recopie (Tags tags1, Tags tags2) {
		if (tags2.getTitre() != null){
			tags1.setTitre(tags2.getTitre());
		}
		if (tags2.getDescription() != null){
			tags1.setDescription(tags2.getDescription());
		}
		if (tags2.getCouleur() != null){
			tags1.setCouleur(tags2.getCouleur());
		}
		if (tags2.getCours() != null){
			tags1.setCours(tags2.getCours());
		}
		if (tags2.getLivres() != null){
			tags1.setLivres(tags2.getLivres());
		}
		return tags1;
	}

}
