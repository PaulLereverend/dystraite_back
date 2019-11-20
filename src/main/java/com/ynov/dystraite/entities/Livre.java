package com.ynov.dystraite.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

public class Livre {
	
	@Entity
	public class Cours {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID")
		private int id;
		
		@Column(name = "titre")
		private String titre;
		
		@Column(name = "description")
		private String description;
		
		@Column(name = "lien")
		private String lien;	
		
		@Column(name = "vignette", columnDefinition="BLOB")
		private byte[] vignette;
		
		@ManyToMany
	    private List<Tags> tags;
	}
}
