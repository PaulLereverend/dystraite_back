package com.ynov.dystraite.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ynov.dystraite.entities.Books;
import com.ynov.dystraite.repositories.BooksRepository;

@Service
public class BooksService {
	@Autowired
	BooksRepository booksRepo;
	
	public Books getById(@PathVariable int id) {
		Optional<Books> book= booksRepo.findById(id);
		if(!book.isPresent()) {
			System.out.println("Book not found");
		}
		return book.get();
	}
	
	public List<Books> getAll() {
		return booksRepo.findAll();
	}
	public Books create(Books book) {
		booksRepo.save(book);
		return book;
	}
	public Books delete(@PathVariable int id) {
		Optional<Books> book= booksRepo.findById(id);
		if(!book.isPresent()) {
			System.out.println("Book not found");
		}
		booksRepo.deleteById(id);
		return book.get();
	}
	public Books update(@PathVariable int id, Books newBook) {
		Optional<Books> book= booksRepo.findById(id);
		if(!book.isPresent()) {
			System.out.println("Book not found");
		}
		Books b = book.get();
		
		booksRepo.save(copy(b, newBook));
		return b;
	}
	
	private Books copy(Books book1, Books book2) {
		if (book2.getTitle() != null){
			book1.setTitle(book2.getTitle());
		}
		if (book2.getDescription() != null){
			book1.setDescription(book2.getDescription());
		}
		if (book2.getLink() != null){
			book1.setLink(book2.getLink());
		}
		if (book2.getThumbnail() != null){
			book1.setThumbnail(book2.getThumbnail());
		}
		if (book2.getTags() != null){
			book1.setTags(book2.getTags());
		}
		return book1;
	}
	public List<Books> findLastCreated(int limit){
		return booksRepo.findLastCreated(limit);
	}

}
