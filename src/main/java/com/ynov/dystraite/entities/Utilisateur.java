package com.ynov.dystraite.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Utilisateur {
	
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "date_naissance")
	private Date date_naissance;
	
	@Column(name = "adresse")
	private String adresse;

	@Column(name = "ville")
	private String ville;
	
	@Column(name = "code_postal")
	private int code_postal;
	
	@Column(name = "mdp")
	private String mdp;
	
	@Column(name = "role")
	private String role;
		
	@OneToOne
	@JoinColumn(name = "orthophoniste", referencedColumnName = "email")
	private Utilisateur orthophoniste;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(int code_postal) {
		this.code_postal = code_postal;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Utilisateur getOrthophoniste() {
		return orthophoniste;
	}

	public void setOrthophoniste(Utilisateur orthophoniste) {
		this.orthophoniste = orthophoniste;
	}
}
