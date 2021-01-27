package com.ynov.dystraite.entities;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
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
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@Column(name= "photo")
	private Byte photo;
	
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

	@Override
	public String toString() {
		return "Users [email=" + email + ", lastname=" + lastname + ", firstname=" + firstname + ", birthdate="
				+ birthdate + ", latitude=" + latitude + ", longitude=" + longitude + ", city=" + city
				+ ", zip_code=" + zipCode + ", password=" + password + ", role=" + role + ", photo=" + photo
				+ ", speech_therapist=" + speechTherapist + "]";
	}

	
}
