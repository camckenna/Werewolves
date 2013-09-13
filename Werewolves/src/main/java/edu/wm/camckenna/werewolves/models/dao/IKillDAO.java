package edu.wm.camckenna.werewolves.models.dao;

import edu.wm.camckenna.werewolves.models.Kill;

import java.util.List;

public interface IKillDAO {
	
	public boolean addKill(Kill kill);
	public List<Kill> getAllKills(Kill kill);

}
