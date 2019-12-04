package com.ynov.dystraite.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Cours {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
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
	@JoinTable(
			  name = "tags", 
			  joinColumns = @JoinColumn(name = "cours_id"), 
			  inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private List<Tags> tags;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public byte[] getVignette() {
		return vignette;
	}

	public void setVignette(byte[] vignette) {
		this.vignette = vignette;
	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}
	
}
