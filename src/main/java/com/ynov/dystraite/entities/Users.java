package com.ynov.dystraite.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import com.ynov.dystraite.models.EnumRoles;
import com.ynov.dystraite.repositories.RolesRepository;

@Entity
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email") 
    }
)
public class Users {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank
    @Email
	@Column(name = "email")
	private String email;

	@NotBlank
    @Size(min = 3, max = 30)
	@Column(name = "lastname")
	private String lastname;

	@NotBlank
    @Size(min = 3, max = 30)
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
	
	@NotBlank
	@Size(max = 240)
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@Column(name= "photo")
	private Byte photo;
	
	@OneToOne
	@JoinColumn(name = "speech_therapist", referencedColumnName = "email")
	private Users speechTherapist;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "users_roles", 
				joinColumns = @JoinColumn(name = "users_id"), 
				inverseJoinColumns = @JoinColumn(name = "roles_id"))
	private Set<Roles> roles = new HashSet<>();
	
	public Users() {}
    public Users(String firstname, String lastname, String email, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
	
	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> strRoles, RolesRepository roleRepository, Authentication auth) {
		Set<Roles> roles = new HashSet<>();
		if (strRoles == null) {
			Roles userRole = roleRepository.findByName(EnumRoles.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
            if(auth.isAuthenticated() && AuthorityUtils.authorityListToSet(auth.getAuthorities()).contains("ROLE_ADMIN")) {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Roles adminRole = roleRepository.findByName(EnumRoles.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;

                        case "mod":
                            Roles modRole = roleRepository.findByName(EnumRoles.ROLE_SPEECH_THERAPIST)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);
                            break;

                        default:
                            Roles userRole = roleRepository.findByName(EnumRoles.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            } else {
                Roles userRole = roleRepository.findByName(EnumRoles.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            }
        }
        
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Users [email=" + email + ", lastname=" + lastname + ", firstname=" + firstname + ", birthdate="
				+ birthdate + ", latitude=" + latitude + ", longitude=" + longitude + ", city=" + city
				+ ", zip_code=" + zipCode + ", password=" + password + ", role=" + role + ", photo=" + photo
				+ ", speech_therapist=" + speechTherapist + "]";
	}
	
}
