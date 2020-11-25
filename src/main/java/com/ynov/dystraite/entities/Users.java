package com.ynov.dystraite.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Users {
	
	@Id
	@Column(name = "email")
	private String email;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "birthdate")
	private Date birthdate;
	
	@Column(name = "latitude")
	private long latitude;
	
	@Column(name = "longitude")
	private long longitude;

	@Column(name = "city")
	private String city;
	
	@Column(name = "zip_code")
	private int zipCode;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@Column(name= "photo")
	private Byte photo;
	
	@OneToOne
	@JoinColumn(name = "speech_therapist", referencedColumnName = "email")
	private Users speechTherapist;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
	    this.zipCode = zipCode;
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

	public Users getSpeechTherapist() {
		return speechTherapist;
	}

	public void setSpeechTherapist(Users speechTherapist) {
		this.speechTherapist = speechTherapist;
	}

	@Override
	public String toString() {
		return "Users [email=" + email + ", lastname=" + lastname + ", firstname=" + firstname + ", birthdate="
				+ birthdate + ", latitude=" + latitude + ", longitude=" + longitude + ", city=" + city
				+ ", zip_code=" + zipCode + ", password=" + password + ", role=" + role + ", photo=" + photo
				+ ", speech_therapist=" + speechTherapist + "]";
	}
	
}
