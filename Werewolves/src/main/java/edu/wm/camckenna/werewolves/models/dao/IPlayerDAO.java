package edu.wm.camckenna.werewolves.models.dao;

import edu.wm.camckenna.werewolves.models.Player;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IPlayerDAO {
	
	public Player getPlayerByID(int ID);
	public boolean updatePlayer(Player player);
	public int addPlayer(Player player);
	public int killsByHour(Date start, Date end);
	
	public List<Player> getAllPlayers();
	public List<Player> getAllDeadPlayers();
	public List<Player> getAllAlivePlayers();
	public List<Player> getAllTownspeopleinArea(double lat, double lng, int radius);
	public List<Player> getAllWerewolves();
	public List<Player> getAllTownspeople();
	
	public List<Player> getPlayerByAttributes(Map<String, Object> map);
	

}
