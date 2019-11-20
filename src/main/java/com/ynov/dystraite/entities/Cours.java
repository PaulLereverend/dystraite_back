package com.ynov.dystraite.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

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
	
	@Column(name = "contenu")
	private String contenu;	
	
	@Column(name = "vignette", columnDefinition="BLOB")
	private byte[] vignette;
	
	@ManyToMany
    @JoinColumn
    private List<Tags> tags;
	
}
