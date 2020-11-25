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
			System.out.println("Tag not found");
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
			System.out.println("Tag not found");
		}
		tagsRepo.deleteById(id);
		return tags.get();
	}
	public Tags update(@PathVariable int id, Tags newTag) {
		Optional<Tags> tags=tagsRepo.findById(id);
		if(!tags.isPresent()) {
			System.out.println("Tag not found");
		}
		Tags t = tags.get();
		
		tagsRepo.save(copy(t, newTag));
		return t;
	}
	
	private Tags copy(Tags tags1, Tags tags2) {
		if (tags2.getTitle() != null){
			tags1.setTitle(tags2.getTitle());
		}
		if (tags2.getDescription() != null){
			tags1.setDescription(tags2.getDescription());
		}
		if (tags2.getColor() != null){
			tags1.setColor(tags2.getColor());
		}
		if (tags2.getLessons() != null){
			tags1.setLessons(tags2.getLessons());
		}
		if (tags2.getBooks() != null){
			tags1.setBooks(tags2.getBooks());
		}
		return tags1;
	}

}
