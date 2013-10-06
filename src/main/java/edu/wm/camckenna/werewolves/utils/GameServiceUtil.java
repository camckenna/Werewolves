package edu.wm.camckenna.werewolves.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
				&& !victim.isWerewolf()
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
	


}
