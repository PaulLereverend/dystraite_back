package com.ynov.dystraite.services;

import java.util.List;
import java.util.Optional;

import com.ynov.dystraite.entities.Lessons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ynov.dystraite.repositories.LessonsRepository;

@Service
public class LessonsService {
	
	@Autowired
	LessonsRepository lessonsRepo;
	
	public Lessons getById(@PathVariable int id) {
		Optional<Lessons> lesson= lessonsRepo.findById(id);
		if(!lesson.isPresent()) {
			System.out.println("Lesson not found");
		}
		return lesson.get();
	}
	
	public List<Lessons> getAll() {
		return lessonsRepo.findAll();
	}
	public Lessons create(Lessons lesson) {
		lessonsRepo.save(lesson);
		return lesson;
	}
	public Lessons delete(@PathVariable int id) {
		Optional<Lessons> lesson= lessonsRepo.findById(id);
		if(!lesson.isPresent()) {
			System.out.println("Lesson not found");
		}
		lessonsRepo.deleteById(id);
		return lesson.get();
	}
	public Lessons update(@PathVariable int id, Lessons newLesson) {
		Optional<Lessons> lesson= lessonsRepo.findById(id);
		if(!lesson.isPresent()) {
			System.out.println("Lesson not found");
		}
		Lessons l = lesson.get();
		
		lessonsRepo.save(copy(l, newLesson));
		return l;
	}
	
	private Lessons copy(Lessons lesson1, Lessons lesson2) {
		if (lesson2.getTitle() != null){
			lesson1.setTitle(lesson2.getTitle());
		}
		if (lesson2.getDescription() != null){
			lesson1.setDescription(lesson2.getDescription());
		}
		if (lesson2.getContent() != null){
			lesson1.setContent(lesson2.getContent());
		}
		if (lesson2.getThumbnail() != null){
			lesson1.setThumbnail(lesson2.getThumbnail());
		}
		if (lesson2.getTags() != null){
			lesson1.setTags(lesson2.getTags());
		}
		return lesson1;
	}
	public List<Lessons> findLastCreated(int limit){
		return lessonsRepo.findLastCreated(limit);
	}


}
