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
	
	@Column(name = "latitude")
	private long latitude;
	
	@Column(name = "longitude")
	private long longitude;

	@Column(name = "ville")
	private String ville;
	
	@Column(name = "code_postal")
	private int code_postal;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@Column(name= "photo")
	private Byte photo;
	
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
	

	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public Byte getPhoto() {
		return photo;
	}

	public void setPhoto(Byte photo) {
		this.photo = photo;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "Utilisateur [email=" + email + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance="
				+ date_naissance + ", latitude=" + latitude + ", longitude=" + longitude + ", ville=" + ville
				+ ", code_postal=" + code_postal + ", password=" + password + ", role=" + role + ", photo=" + photo
				+ ", orthophoniste=" + orthophoniste + "]";
	}
	
}
