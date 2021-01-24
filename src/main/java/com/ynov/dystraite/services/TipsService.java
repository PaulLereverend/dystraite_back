package com.ynov.dystraite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ynov.dystraite.entities.Tips;
import com.ynov.dystraite.repositories.TipsRepository;

@Service
public class TipsService {
	
	@Autowired
	TipsRepository tipsRepo;
	
	public Tips getById(@PathVariable int id) {
		Optional<Tips> tip= tipsRepo.findById(id);
		if(!tip.isPresent()) {
			System.out.println("Tip not found");
		}
		return tip.get();
	}
	
	public List<Tips> getAll() {
		return tipsRepo.findAll();
	}
	public Tips create(Tips tip) {
		tipsRepo.save(tip);
		return tip;
	}
	public Tips delete(@PathVariable int id) {
		Optional<Tips> tip= tipsRepo.findById(id);
		if(!tip.isPresent()) {
			System.out.println("Tip not found");
		}
		tipsRepo.deleteById(id);
		return tip.get();
	}
	public Tips update(Tips newTip) {
		return tipsRepo.save(newTip);
	}
	
	public List<Tips> findLastCreated(int limit){
		return tipsRepo.findLastCreated(limit);
	}

}
