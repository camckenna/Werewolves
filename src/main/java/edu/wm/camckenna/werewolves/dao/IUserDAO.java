package edu.wm.camckenna.werewolves.dao;

import edu.wm.camckenna.werewolves.domain.User;
import edu.wm.camckenna.werewolves.exceptions.MultipleUsersWithSameIDException;
import edu.wm.camckenna.werewolves.exceptions.NoUserFoundException;

import java.util.List;

public interface IUserDAO {
	
	void createUser(User user);
	void updateUser(User user);
	void remove(User user);
	
	List<User> getAllUsers();
	List<User> getAdmin();
	List<User> getUsersInOrderofScore();
	
	User getUserById(String id) throws NoUserFoundException, MultipleUsersWithSameIDException;
	User getUserByUsername(String username) throws NoUserFoundException, MultipleUsersWithSameIDException;
	
	boolean equals(User u1, User u2);
	
}
