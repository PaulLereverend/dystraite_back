package com.ynov.dystraite.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.ynov.dystraite.entities.Users;
import com.ynov.dystraite.repositories.UsersRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UsersRepository usersRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = usersRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
		// user.setAutoLogin(autoLogin); , Boolean autoLogin
		return UserDetailsImpl.build(user);
	}
}
