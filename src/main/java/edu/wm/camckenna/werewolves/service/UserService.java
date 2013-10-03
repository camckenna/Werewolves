package edu.wm.camckenna.werewolves.service;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class UserService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public UserDetails loadUserByUsername(String username){
		return null;
	}
	
	public void createUser(String name, String pass, String first, String last){
		Collection<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		//User from User Detials
		//MyUser user = new MyUser();
		//userDAO.createUser(u);
	}
}
