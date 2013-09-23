package edu.wm.camckenna.werewolves.dao;

import java.util.List;

import edu.wm.camckenna.werewolves.domain.Player;
import edu.wm.camckenna.werewolves.exceptions.NoPlayerFoundException;

public interface IPlayerDAO {
	
	void createPlayer(Player player);
	List<Player> getAllAlive();
	void setDead(Player p);
	
	Player getPlayerByID(String id) throws NoPlayerFoundException;
	
}
