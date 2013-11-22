package edu.wm.camckenna.werewolves.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import edu.wm.camckenna.werewolves.domain.Game;
import edu.wm.camckenna.werewolves.domain.Player;
import edu.wm.camckenna.werewolves.domain.User;

public class GameServiceUtil {
	
	public static double convertKilometersToRadians(double km){
		return km/111.12; // 1 degree = 111.12 kilometers ish - CONFIRM 
	}

	public static boolean isInRange(Player center, Player point, double radius){
		double radiusInRadians = convertKilometersToRadians(radius);
		double x = center.getLat() - point.getLat();
		double y = center.getLng() - point.getLng();
		//System.out.println("Range: " + (Math.pow(x, 2) + Math.pow(y, 2)) +", "+ Math.pow(radiusInRadians, 2));
		//System.out.print((Math.pow(x, 2) + Math.pow(y, 2)) <= Math.pow(radiusInRadians, 2));
		return (Math.pow(x, 2) + Math.pow(y, 2)) <= Math.pow(radiusInRadians, 2);
	}
	
	public static boolean canKill(Player killer, Player victim, double radius){
	
		return killer.getCanKill() 
				&& !killer.isDead() 
				&& !victim.isDead() 
				&& isInRange(killer, victim, radius);			
				
	}
	public static boolean canVote(Player voter, Player votedAgainst){	
		return    !voter.isDead() 
				&& !votedAgainst.isDead() 
				&& voter.getVotedAgainst() != null;			
				
	}
	public static boolean isValidFreq(String freq){
		try{
			int fre = Integer.parseInt(freq);
			return (fre > 0 && fre <= 720); //Max time: 12 hours
		}
		catch(Exception e){
			return false;
		}
	}
	public static boolean isValidRange(String scent, String kill){
		try{
			double s = Double.parseDouble(scent);
			double k = Double.parseDouble(kill);
			return (s > 0 && k > 0 && s > k); 
		}
		catch(Exception e){
			return false;
		}
	}
	
	public static boolean doesHunterKill(List<Player> hunters, List<Player> townspeople){
		
		if(Math.random() <= .5*(1 - ((double)hunters.size())/townspeople.size()))
			return true;
		else
			return false;
		
	}
	public static boolean doesCounterAttackOccur(List<Player> werewolves, List<Player> townspeople){
		
		if(Math.random() <= .3*((double)werewolves.size())/townspeople.size())
			return true;
		else
			return false;
		
	}


	public static Map<String, String> getListofPlayersWithStatus(Game game,
			Player player, List<Player> players) {

		if(player.isDead()){
			return getDeadList(player, players);
		}
		else if(game.isDay()){
			return getDayList(player, players);
		}
		else if(!game.isDay() && player.isWerewolf())
			return getWerewolfList(game, player, players);
		else if(!game.isDay() && player.isHunter())
			return getHunterList(game, player, players);
		else
			return getDayList(player, players);
	}

	private static Map<String, String> getHunterList(Game game, Player player,
			List<Player> players) {
		Map<String, String> coll = new HashMap<String, String>();
		for(Player p : players){
			if(player.getUsername().equals(p.getUsername()))
				continue;
			else if(p.isDead())
				coll.put(p.getUsername(), "DEAD");
			else if(isInRange(player, p, game.getKillRadius())){
				coll.put(p.getUsername(), "KILL");
			}
			else{
				coll.put(p.getUsername(), "ALIVE");
			}
		}
		return coll;
	}

	private static Map<String, String> getWerewolfList(Game game,
			Player player, List<Player> players) {
		Map<String, String> coll = new HashMap<String, String>();
		for(Player p : players){
			if(player.getUsername().equals(p.getUsername()))
				continue;
			else if(p.isDead())
				coll.put(p.getUsername(), "DEAD");
			else if(p.isWerewolf())
				coll.put(p.getUsername(), "WEREWOLF");
			else if(isInRange(player, p, game.getKillRadius())){
				coll.put(p.getUsername(), "KILL");
			}
			else if(isInRange(player, p, game.getScentRadius())){
				coll.put(p.getUsername(), "SCENT");
			}
			else{
				coll.put(p.getUsername(), "ALIVE");
			}
		}
		return coll;
	}

	private static Map<String, String> getDeadList(Player player, List<Player> players) {
		Map<String, String> coll = new HashMap<String, String>();
		for(Player p : players){
			if(player.getUsername().equals(p.getUsername()))
				continue;
			coll.put(p.getUsername(), "DEAD");			
		}
		return coll;
	}

	private static Map<String, String> getDayList(Player player, List<Player> players) {
		Map<String, String> coll = new HashMap<String, String>();
		for(Player p : players){
			if(player.getUsername().equals(p.getUsername()))
				continue;
			else if(p.isDead())
				coll.put(p.getUsername(), "DEAD");
			else if(player.isWerewolf()){
				if(p.isWerewolf()){
					coll.put(p.getUsername(), "WEREWOLF_VOTE");
				}
				else{
					coll.put(p.getUsername(), "ALIVE_VOTE");
				}
			}
			else{
				coll.put(p.getUsername(), "ALIVE_VOTE");
			}
		}
		return coll;
	}
	


}
