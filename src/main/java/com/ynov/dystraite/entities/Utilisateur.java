package com.ynov.dystraite.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@Column(name = "orthophoniste")
	private Utilisateur orthophoniste;
	
}
