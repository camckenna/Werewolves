package edu.wm.camckenna.werewolves.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.wm.camckenna.werewolves.dao.IPlayerDAO;
import edu.wm.camckenna.werewolves.dao.IUserDAO;
import edu.wm.camckenna.werewolves.domain.GPSLocation;
import edu.wm.camckenna.werewolves.domain.Player;
import edu.wm.camckenna.werewolves.exceptions.MultiplePlayersWithSameIDException;
import edu.wm.camckenna.werewolves.exceptions.NoPlayerFoundException;

public class GameService {
	@Autowired private IPlayerDAO playerDAO;
	@Autowired private IUserDAO userDAO;
	
	public List<Player>getAllAlive()
	{
		return playerDAO.getAllAlive();
	}
	public void createPlayer(Player player)
	{
		playerDAO.createPlayer(player);
		return;
	}
	
	public void setDead(Player p)
	{
		playerDAO.setDead(p);
		return;
	}
	
	public Player getPlayerByID(String id) throws NoPlayerFoundException, MultiplePlayersWithSameIDException
	{
		return playerDAO.getPlayerByID(id);
	}	

	public void updatePosition(String name, GPSLocation location) {
		// TODO Auto-generated method stub
		
	}
	public boolean canKill(Player killer, Player victim){
		
		return true;
	}
}
