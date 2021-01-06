package com.ynov.dystraite.security.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ynov.dystraite.entities.Users;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String email;
	
	private String lastname;
	
	private String firstname;
	
	private Date birthdate;
	
	private long latitude;
	
	private long longitude;
	
	private String city;
	
	private int zipCode;
	
	private Byte photo;

	@JsonIgnore
	private String password;
	
	// Info Remember User
	private boolean autoLogin;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(
		Long id, 
		String email,
		String lastname,
		String firstname,
		Date birthdate,
		long latitude,
		long longitude,
		String city,
		int zipCode,
		Byte photo,
		String password,
		Collection<? extends GrantedAuthority> authorities
        ) {
		this.id = id;
		this.email = email;
		this.lastname =lastname;
		this.firstname = firstname;
		this.birthdate = birthdate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
		this.zipCode = zipCode;
		this.photo = photo;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(Users user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
			user.getId(),
			user.getEmail(),
			user.getLastname(),
			user.getFirstname(),
			user.getBirthdate(),
			user.getLatitude(),
			user.getLongitude(),
			user.getCity(),
			user.getZipCode(),
			user.getPhoto(),
			user.getPassword(),
			authorities
		);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public Date getBirthdate() {
		return birthdate;
	}
	
	public long getLatitude() {
		return latitude;
	}
	
	public long getLongitude() {
		return longitude;
	}
	
	public String getCity() {
		return city;
	}
	
	public int getZipCode() {
		return zipCode;
	}
	
	public Byte getPhoto() {
		return photo;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public boolean getAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public String getUsername() {
		return null;
	}
}
