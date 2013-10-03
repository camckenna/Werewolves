package edu.wm.camckenna.werewolves.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.mongodb.MongoClient;

import edu.wm.camckenna.werewolves.HomeController;
import edu.wm.camckenna.werewolves.dao.IKillDAO;
import edu.wm.camckenna.werewolves.dao.IPlayerDAO;
import edu.wm.camckenna.werewolves.dao.IUserDAO;
import edu.wm.camckenna.werewolves.dao.IVoteDAO;
import edu.wm.camckenna.werewolves.domain.GPSLocation;
import edu.wm.camckenna.werewolves.domain.Game;
import edu.wm.camckenna.werewolves.domain.Player;
import edu.wm.camckenna.werewolves.domain.User;
import edu.wm.camckenna.werewolves.domain.Vote;
import edu.wm.camckenna.werewolves.exceptions.MultiplePlayersWithSameIDException;
import edu.wm.camckenna.werewolves.exceptions.MultipleUsersWithSameIDException;
import edu.wm.camckenna.werewolves.exceptions.NoPlayerFoundException;
import edu.wm.camckenna.werewolves.exceptions.NoUserFoundException;

public class GameService {
	
	private static final Logger logger = LoggerFactory.getLogger(GameService.class);
	
	@Autowired private IPlayerDAO playerDAO;
	@Autowired private IUserDAO userDAO;
	@Autowired private IVoteDAO voteDAO;
	@Autowired private IKillDAO killDAO;
	
	private Game game;
	
	
	public GameService(){
		
	}
	
	public List<Player> getAllAlive()
	{
		return playerDAO.getAllAlive();
	}
	public List<Player> getAllWerewolves()
	{
		return playerDAO.getAllWerewolves();
	}


	public void switchFromDayToNight(){
		hangPlayer();
		List<Player> werewolves = getAllWerewolves();
		for(Player w : werewolves){
			w.setCanKill(true);
			updatePlayer(w);
		}		
	}
	public void switchFromNightToDay(){
		List<Player> werewolves = getAllWerewolves();
		for(Player w : werewolves){
			w.setCanKill(false);
			updatePlayer(w);
		}		
		List<Player> allPlayers = getAllPlayers();
		for(Player p : allPlayers){
			p.setVotedAgainst("");
		}
		
		List<Player> allAlive = getAllAlive();
		updateScoreBecauseOfPlayersAction(allAlive, 1);

		
	}
	private List<Player> getAllPlayers() {
		return playerDAO.getAllPlayers();
	}

	@Scheduled(fixedDelay=5000)
	public void checkGameOperation(){
		//something
		if(game == null){
			startGame();
		}
		//Check if people are reporting
		//Check if Game is running
		//Check day and night
		long timeElapsed = (new Date().getTime() - game.getCreatedDate().getTime())/1000;
		
		long dayNightInSec = game.getDayNightFreq()*60;
		
		if(timeElapsed % dayNightInSec == 0) //TIme to switch periods
		{
			if((timeElapsed/dayNightInSec) % 2 == 1) //Becoming Night
			{
				switchFromDayToNight();
				logger.info("Switching to Night");
			}
			else{
				switchFromNightToDay();
				logger.info("Switching to Day");
			}
				
		}
		
		
		logger.info("Time since game began in seconds: " + timeElapsed);
	}
	public void startGame(){
		//Drop PLayer,Vote,Kill collections
		//Create new Player for each user and assign status
		//Start game
		logger.info("Starting game!");
		playerDAO.discardTable();
		killDAO.discardTable();
		voteDAO.discardTable();
	
		List<User> users = getAllUsers();
		List<Player> players = new ArrayList<Player>();
		
		for(User u: users){
			Player p = new Player();
			p.setId(UUID.randomUUID().toString());
			p.setCanKill(false);
			p.setLat(0.0);
			p.setLng(0.0);
			p.setUserId(u.getUsername());
			p.setDead(false);
			p.setVotedAgainst("");
			
			players.add(p);			
		}
		long seed = System.nanoTime();
		Collections.shuffle(players, new Random(seed));
		
		int numOfWolves = (int)(0.3 * players.size());
		for(int x = 0; x < numOfWolves; x++){
			players.get(x).setWerewolf(true);
			createPlayer(players.get(x));
		}
		for(int x = numOfWolves; x < players.size(); x++){
			players.get(x).setWerewolf(false);
			createPlayer(players.get(x));
		}
		
		//DayNightFreq in minutes
		game = new Game(1, new Date());
		game.setRunning(true);
		
		
	}
	public void createPlayer(Player player)
	{
		playerDAO.createPlayer(player);
		return;
	}
	
	public void updatePlayer(Player p)
	{
		playerDAO.updatePlayer(p);
		return;
	}
	
	public Player getPlayerByID(String id) throws NoPlayerFoundException, MultiplePlayersWithSameIDException
	{
		return playerDAO.getPlayerByID(id);
	}	

	
	public boolean canKill(Player killer, Player victim){	
		return killer.getCanKill() && !killer.isDead() && !victim.isDead() && !victim.isWerewolf();
				
				
	}
	public List<String> getScores(){	
		List<User> allUsers = userDAO.getUsersInOrderofScore();
		List<String> namesAndScore = new ArrayList<String>();
		for(User u : allUsers){
			namesAndScore.add(u.getUsername() + ",  + " + u.getScore());
		}
		return namesAndScore;
	}
	public void voteForPlayer(String voterString, String votedAgainstString){
		logger.info(voterString + " voted for " + votedAgainstString);
		Player voter = convertFromPrincipalNameToPlayer(voterString);
		Player votedAgainst = convertFromPrincipalNameToPlayer(votedAgainstString);
		
		if(voter.isDead() || votedAgainst.isDead()){
			logger.info("Cannot vote if either is dead");
			return;
		}
		
		voter.setVotedAgainst(votedAgainst.getUserId());
		Vote vote = new Vote(voterString, votedAgainstString, new Date());
		voteDAO.addVote(vote);

		playerDAO.updatePlayer(voter);
	}
	public void hangPlayer() {
		Player p = findPlayerToHang();
		if(p == null) //if nobody voted...
			return;
		killVotedAgainstPlayer(p);	
		checkToRewardPointsforVoting(p);
	}
	
	private void killVotedAgainstPlayer(Player p){

		p.setDead(true);
		updatePlayer(p);		
		logger.info(p.getUserId() + ", isDead:" + p.isDead());
	}
	
	private Player findPlayerToHang(){
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<Player> players = getAllAlive();
		for(Player p : players){
			if(!p.isDead() && p.getVotedAgainst() != null && !p.getVotedAgainst().equals("")){
				String votedAgainst = p.getVotedAgainst();
				logger.info("Putting " + votedAgainst + " into the map");
				if(map.containsKey(votedAgainst)){
					int num = map.get(votedAgainst).intValue();
					num++;
					map.put(votedAgainst, Integer.valueOf(num));
				}
				else
					map.put(votedAgainst, Integer.valueOf(1));
			}
		}	
		
		int maxVotes = 1;
		for(Integer i : map.values())
		{
			if(Integer.valueOf(i) >= maxVotes){
				maxVotes = Integer.valueOf(i);
			}
		}
		//List of Player Ids with maxVotes
		List<String> ids = new ArrayList<String>();
		for(String s : map.keySet()){
			if(Integer.valueOf(map.get(s)) == maxVotes){
				logger.info("Updating maxvotes with: " + s);
				ids.add(s);
			}
		}
		logger.info("Number of players to choose from: " + ids.size());
		try{
			if(ids.size() == 1){
				String playerName = ids.get(0);
				logger.info("The player to die is: "+ playerName);
				return playerDAO.getPlayerbyUsername(playerName);
			}
			else if(ids.size() == 0){
				logger.info("Nobody was voted for, so nobody dies");
				return null;
			}
			else{
				int choice = new Random().nextInt(ids.size());
				String playerName = ids.get(choice);
				return playerDAO.getPlayerbyUsername(playerName);
			}
		}
		catch(Exception e){
			logger.info("Exception while finding player to hang: " + e.toString());
			return null;
		}
		
	}
	public void checkToRewardPointsforVoting(Player p){
		//Do not reward people for killing townspeople
		if(!p.isWerewolf())
			return;
	
		List<Player> players = getAllAlive();
		
		List<Player> playersToReward = new ArrayList<Player>();
		
		//If the player helped vote for the werewolf
		for(Player player : players){
			if(player.getVotedAgainst().equals(p.getUserId())){
				playersToReward.add(player); //players who voted to kill the werewolf
			}
		}
		updateScoreBecauseOfPlayersAction(playersToReward, 10);
	}

	private List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
	private void updateScoreBecauseOfPlayersAction(List<Player> players, int points){
		List<User> users = getAllUsers();
		for(User u : users){
			//if an user is in the list of players to reward, increase score by points
			if(players.contains(u.getUsername())){
				u.increaseScore(points);
			}
		}
	}

	public void updatePosition(String name, GPSLocation location) {
		
	}
	private User convertFromPrincipalNameToUser(String name){
		try{
			return userDAO.getUserById(name);
		}
		catch(MultipleUsersWithSameIDException e){
			logger.error("Too many users found for username: " + name);
			return null;
		} catch (NoUserFoundException e) {
			logger.error("No users found for username: " + name);
			return null;
		}
	}
	private Player convertFromPrincipalNameToPlayer(String name){
		try{
			return playerDAO.getPlayerbyUsername(name);
		}
		catch(MultiplePlayersWithSameIDException e){
			logger.error("Too many players found for username: " + name);
			return null;
		} catch (NoPlayerFoundException e) {
			logger.error("No players found for username: " + name);
			return null;
		}
	}
	
	public void kill(String victimName, String killerName){
		
		//Check for distance
		try {
			Player victim = playerDAO.getPlayerbyUsername(victimName);
			Player killer = playerDAO.getPlayerbyUsername(killerName);
			
			if(!canKill(killer, victim)){
				logger.info("Cannot kill at this time");
				return;
			}
				
			
			killer.setCanKill(false);
			victim.setDead(true);
			
			playerDAO.updatePlayer(killer);
			playerDAO.updatePlayer(victim);
			
		} catch (NoPlayerFoundException e){
			logger.error("No users found for usernames: " + victimName + ", or" + killerName);
		}
		catch(MultiplePlayersWithSameIDException e) {
			logger.error("No users found for usernames: " + victimName + ", or" + killerName);
		}
	}
	public List<Player> getAllNearby(String name){
		Player p = convertFromPrincipalNameToPlayer(name);
		return playerDAO.findNearbyPlayers(p);
	}
}
