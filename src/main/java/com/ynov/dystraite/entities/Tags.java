package com.ynov.dystraite.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tags {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	@Lob
	private String description;
	
	@Column(name = "color")
	private String color;
	
	@ManyToMany(mappedBy = "tags")
    private List<Lessons> lessons;
	
	@ManyToMany(mappedBy = "tags")
    private List<Books> books;

	public Tags(String title, String description, String color, List<Lessons> lessons, List<Books> books) {
		super();
		this.title = title;
		this.description = description;
		this.color = color;
		this.lessons = lessons;
		this.books = books;
	}

}

