package edu.wm.camckenna.werewolves.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.wm.camckenna.werewolves.dao.IUserDAO;
import edu.wm.camckenna.werewolves.domain.User;
import edu.wm.camckenna.werewolves.domain.UserCredentials;
import edu.wm.camckenna.werewolves.exceptions.MultipleUsersWithSameIDException;
import edu.wm.camckenna.werewolves.exceptions.NoUserFoundException;

public class UserCredentialsDetailsService implements UserDetailsService {

	@Autowired private IUserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
	    User user = null;
		try {
			user = userDAO.getUserByUsername(username);
		} catch (NoUserFoundException | MultipleUsersWithSameIDException e) {
			
			e.printStackTrace();
		} 
	    if (user == null) { 
	      throw new UsernameNotFoundException("Invalid username/password."); 
	    } 
	    Collection<? extends GrantedAuthority> authorities = 
	      createAuthorities(user); 
	    return new UserCredentials(user.getUsername(), user.getHashedPassword(), 
	      authorities); 
	}

	private Collection<? extends GrantedAuthority> createAuthorities(User user){
		Set<SimpleGrantedAuthority> roles = new HashSet<SimpleGrantedAuthority>();
		if(!user.isAdmin()){
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		else if(user.isAdmin()){
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		else{}
		return roles;
	}
}
