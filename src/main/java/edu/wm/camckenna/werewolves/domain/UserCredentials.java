package edu.wm.camckenna.werewolves.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserCredentials extends org.springframework.security.core.userdetails.User implements UserDetails{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserCredentials(String username, String password,
		Collection<? extends GrantedAuthority> authorities) {
	super(username, password, true, true, true, true, authorities);
}

}
