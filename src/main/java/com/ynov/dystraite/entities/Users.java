package com.ynov.dystraite.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
	private Long latitude;
	
	@Column(name = "longitude")
	private Long longitude;

	@Column(name = "city")
	private String city;
	
	@Column(name = "zip_code")
	private Integer zipCode;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@Column(name= "photo")
	private Byte photo;
	
	@Column(name = "liked")
	@ManyToMany
	@JoinTable(
			  name = "users_tips", 
			  joinColumns = @JoinColumn(name = "email"), 
			  inverseJoinColumns = @JoinColumn(name = "id"))
	private List<Tips> likedTips;
	
	@OneToMany(mappedBy="owner")
    private List<Tips> tips;
	
	@OneToOne
	@JoinColumn(name = "speech_therapist", referencedColumnName = "email")
	private Users speechTherapist;


	public Users(String email, String lastname, String firstname, Date birthdate, long latitude, long longitude,
			String city, int zipCode, String password, String role, Byte photo, Users speechTherapist) {
		super();
		this.email = email;
		this.lastname = lastname;
		this.firstname = firstname;
		this.birthdate = birthdate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.zipCode = zipCode;
		this.password = password;
		this.role = role;
		this.photo = photo;
		this.speechTherapist = speechTherapist;
	}

	public Users() {
	}

	@Override
	public String toString() {
		return "Users [email=" + email + ", lastname=" + lastname + ", firstname=" + firstname + ", birthdate="
				+ birthdate + ", latitude=" + latitude + ", longitude=" + longitude + ", city=" + city
				+ ", zip_code=" + zipCode + ", password=" + password + ", role=" + role + ", photo=" + photo
				+ ", speech_therapist=" + speechTherapist + "]";
	}

	
}
