package edu.wm.camckenna.werewolves.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.wm.camckenna.validators.TempUserValidator;
import edu.wm.camckenna.werewolves.dao.IUserDAO;
import edu.wm.camckenna.werewolves.domain.ChangePasswordUser;
import edu.wm.camckenna.werewolves.domain.TempUser;
import edu.wm.camckenna.werewolves.domain.User;
import edu.wm.camckenna.werewolves.exceptions.MultipleUsersWithSameIDException;
import edu.wm.camckenna.werewolves.exceptions.NoUserFoundException;

public class UserService  {
	
	@Autowired private IUserDAO userDAO;
	@Autowired private BCryptPasswordEncoder passwordEncoder;
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	public void createAccount(TempUser user){
		logger.info("In User Service");
		String hashedPW = passwordEncoder.encode(user.getPassword());
		User newUser = new User(UUID.randomUUID().toString(), null, null, user.getUsername(), user.getEmail(), hashedPW, null);
		userDAO.createUser(newUser);
		logger.info("User with username " + user.getUsername() + "created");
	}
	public boolean deleteAccount(String name, String password){
		
		User user = convertFromPrincipalNameToUser(name);
		
		logger.info("Entered password: " + passwordEncoder.encode(password));
		logger.info("User password: " + user.getHashedPassword());
		
		if(passwordEncoder.matches(password, user.getHashedPassword())){
			userDAO.remove(user);
			logger.info("User with username " + user.getUsername() + "deleted");
			return true;
		}
		else{
			logger.info("Passwords did not match");
			return false;
		}
	}
	public void updateAccount(ChangePasswordUser changedUser, String name){
		User user = convertFromPrincipalNameToUser(name);
		
		if(changedUser.getNewPassword() != null)
			user.setHashedPassword(passwordEncoder.encode(changedUser.getNewPassword()));
		
		userDAO.updateUser(user);
		logger.info("User with username " + user.getUsername() + "updated");
	}
	
	private User convertFromPrincipalNameToUser(String name){
		try{
			return userDAO.getUserByUsername(name);
		}
		catch(MultipleUsersWithSameIDException e){
			logger.error("Too many users found for username: " + name);
			return null;
		} catch (NoUserFoundException e) {
			logger.error("No users found for username: " + name);
			return null;
		}
	}
	
	public List<String> getAllNames(){
		
		List<String> names = new ArrayList<String>();
		List<User> users = userDAO.getAllUsers();
		for(User user : users){
			names.add(user.getUsername());
			logger.info(user.getUsername());
		}
		return names;
	}

}
