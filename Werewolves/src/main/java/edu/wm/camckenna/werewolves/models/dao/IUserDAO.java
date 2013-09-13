package edu.wm.camckenna.werewolves.models.dao;

import edu.wm.camckenna.werewolves.models.User;

import java.util.List;

public interface IUserDAO {
	
	public User getUserByID(int id);
	public boolean updateUser(User user);
	public int addUser(User user);
	public List<User> getAllUsers();
	public User getLeader();
	

}
