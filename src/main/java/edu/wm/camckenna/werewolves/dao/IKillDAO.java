package edu.wm.camckenna.werewolves.dao;

import java.util.List;

import edu.wm.camckenna.werewolves.domain.Kill;

public interface IKillDAO {
	
	void addKill(Kill kill);
	List<Kill> getAllKills();
	List<Kill> getKillsByKillerID(String killerID);
	List<Kill> getKillsByVictimID(String victimID);
	List<Kill> getKillsInTimeRange(long start, long end);
	void discardTable();
	

}
