package edu.wm.camckenna.werewolves.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import edu.wm.camckenna.werewolves.domain.Player;
import edu.wm.camckenna.werewolves.exceptions.NoPlayerFoundException;

public class MongoPlayerDAO implements IPlayerDAO {
	
	public MongoPlayerDAO() throws UnknownHostException {
		// TODO Auto-generated constructor stub
		//	MongoClient mongoClient = new MongoClient("localhost", 27017);
	}
	
	@Override
	public List<Player> getAllAlive() {
		// TODO Auto-generated method stub
		List<Player> players = new ArrayList<>();
		Player bob = new Player();
		bob.setUserId("1");
		bob.setWerewolf(true);
		bob.setLat(40.3f);
		bob.setLng(32.5f);
		
		players.add(bob);
		
		return players;
	}

	@Override
	public void setDead(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getPlayerByID(String id) throws NoPlayerFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
