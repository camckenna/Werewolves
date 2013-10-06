package edu.wm.camckenna.werewolves.dao;

import java.util.List;

import edu.wm.camckenna.werewolves.domain.GPSLocation;
import edu.wm.camckenna.werewolves.domain.Player;
import edu.wm.camckenna.werewolves.exceptions.MultiplePlayersWithSameIDException;
import edu.wm.camckenna.werewolves.exceptions.NoPlayerFoundException;

public interface IPlayerDAO {
	
	/*
	 * Precondition: A non-null Player object with non-null fields
	 */
	void createPlayer(Player player);
	void updatePlayer(Player player);
	void deletePlayer(Player player);
	void discardTable();
	
	List<Player> getAllPlayers();
	List<Player> getAllAlive();
	List<Player> getAllDead();	
	List<Player> getAllTownspeople();
	List<Player> getAllWerewolves();
	List<Player> getAllAliveWerewolves();
	
	boolean equals(Player p1, Player p2);
	
	Player getPlayerByID(String id) throws NoPlayerFoundException, MultiplePlayersWithSameIDException;
	Player getPlayerbyUsername(String username) throws NoPlayerFoundException, MultiplePlayersWithSameIDException;
	
}
