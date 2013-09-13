package edu.wm.camckenna.werewolves.models.dao;

import edu.wm.camckenna.werewolves.models.Game;

public interface IGameDAO {
	
	public Game getGame();
	public boolean updateGame();
	public boolean createGame();

}
