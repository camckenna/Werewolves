package edu.wm.camckenna.werewolves.dao;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.wm.camckenna.werewolves.domain.GPSLocation;
import edu.wm.camckenna.werewolves.domain.Player;
import edu.wm.camckenna.werewolves.exceptions.MultiplePlayersWithSameIDException;
import edu.wm.camckenna.werewolves.exceptions.NoPlayerFoundException;

public class MongoPlayerDAO implements IPlayerDAO {
	
	private MongoClient mongoClient;
	private DB db;
	
	public final static String COLLECTION_NAME = "PLAYERS";
	public final static String DATABASE_NAME = "WEREWOLVES";
	
	//@autowired private 
	
	public MongoPlayerDAO() throws UnknownHostException {
		mongoClient = new MongoClient("localhost", 27017);
		db = mongoClient.getDB(DATABASE_NAME);
	}
	
	@Override
	public void createPlayer(Player player) {		
		DBCollection playerColl = getCollection();
		BasicDBObject doc = new BasicDBObject();
		
		doc.put("id", player.getId());
		doc.put("isDead", player.isDead());
		doc.put("lat",player.getLat());
		doc.put("lng", player.getLng());
		doc.put("userId", player.getUserId());
		doc.put("isWerewolf", player.isWerewolf()); 
		doc.put("canKill", player.getCanKill());
		doc.put("votedAgainst", player.getVotedAgainst());
		playerColl.insert(doc);
	}
	@Override
	public void updatePlayer(Player player) {
		DBCollection coll = getCollection();
		
		BasicDBObject query = new BasicDBObject();
		query.put("id", player.getId());
		
		BasicDBObject doc = new BasicDBObject();
		doc.put("isDead", player.isDead());
		doc.put("lat",player.getLat());
		doc.put("lng", player.getLng());
		doc.put("userId", player.getUserId());
		doc.put("isWerewolf", player.isWerewolf()); 
		doc.put("canKill", player.getCanKill());
		doc.put("votedAgainst", player.getVotedAgainst());
	 
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", doc);
	 
		coll.update(query, updateObj);			
	}
	@Override
	public void deletePlayer(Player player) {
		DBCollection coll = getCollection();
		 
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", player.getId());
	 
		coll.remove(searchQuery);		
	}
		
	public boolean equals(Player p1, Player p2){

		return  p1.getId().equals(p2.getId()) 
				&& p1.isDead() == p2.isDead() 
				&& BigDecimal.valueOf(p1.getLat()).equals(BigDecimal.valueOf(p2.getLat()))
				&& BigDecimal.valueOf(p1.getLng()).equals(BigDecimal.valueOf(p2.getLng()))
				&& p1.getUserId().equals(p2.getUserId()) 
				&& p1.isWerewolf() == p2.isWerewolf()
				&& p1.getCanKill() == p2.getCanKill()
				&& p1.getVotedAgainst().equals(p2.getVotedAgainst());			
	}
	
	@Override
	public List<Player> getAllPlayers(){
		List<Player> players = new ArrayList<>();
		DBCollection playerColl = getCollection();
		DBCursor cursor = playerColl.find();
		
		while(cursor.hasNext()){
			Player p = convertFromObject(cursor.next());
			players.add(p);
		}
		return players;
	}
	@Override
	public List<Player> getAllAlive() {
		List<Player> players = new ArrayList<>();
		DBCollection playerColl = getCollection();	
		 
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("isDead", false);
		DBCursor cursor = playerColl.find(searchQuery);
		
		while(cursor.hasNext()){
			Player p = convertFromObject(cursor.next());
			players.add(p);
		}
		return players;
	}
	@Override
	public List<Player> getAllDead() {
		List<Player> players = new ArrayList<>();
		DBCollection playerColl = getCollection();	
		 
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("isDead", true);
		DBCursor cursor = playerColl.find(searchQuery);
		
		while(cursor.hasNext()){
			Player p = convertFromObject(cursor.next());
			players.add(p);
		}
		return players;
	}


	@Override
	public List<Player> getAllTownspeople() {
		List<Player> players = new ArrayList<>();
		DBCollection playerColl = getCollection();	
		 
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("isWerewolf", false);
		DBCursor cursor = playerColl.find(searchQuery);
		
		while(cursor.hasNext()){
			Player p = convertFromObject(cursor.next());
			players.add(p);
		}
		return players;
	}

	@Override
	public List<Player> getAllWerewolves() {
		List<Player> players = new ArrayList<>();
		DBCollection playerColl = getCollection();	
		 
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("isWerewolf", true);
		DBCursor cursor = playerColl.find(searchQuery);
		
		while(cursor.hasNext()){
			Player p = convertFromObject(cursor.next());
			players.add(p);
		}
		return players;
	}


	
	@Override
	public void setDead(Player p) {
		DBCollection coll = getCollection();
		
		BasicDBObject query = new BasicDBObject();
		query.put("id", p.getId());
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("isDead", true);
	 
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);
	 
		coll.update(query, updateObj);			
	}
	@Override
	public void setPlayerLocation(Player p, GPSLocation loc){
		DBCollection coll = getCollection();
		
		BasicDBObject query = new BasicDBObject();
		query.put("id", p.getId());
		
		BasicDBObject newDocument = new BasicDBObject();
		newDocument.put("lat", loc.getLat());
		newDocument.put("lng", loc.getLng());
	 
		BasicDBObject updateObj = new BasicDBObject();
		updateObj.put("$set", newDocument);
	 
		coll.update(query, updateObj);	
	}
	
	@Override
	public Player getPlayerByID(String id) throws NoPlayerFoundException, MultiplePlayersWithSameIDException {
		DBCollection playerColl = getCollection();	
		 
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("id", id);
		DBCursor cursor = playerColl.find(searchQuery);
		
		/*
		 Warning: Calling toArray or length on a DBCursor will irrevocably turn it into an array. 
		This means that, if the cursor was iterating over ten million results 
		(which it was lazily fetching from the database), suddenly there will 
		be a ten-million element array in memory. Before converting to an array, 
		make sure that there are a reasonable number of results using skip() and limit().
		*/
		if(cursor.count() < 1){
			throw new NoPlayerFoundException(id);
		}
		if(cursor.count() > 1){
			throw new MultiplePlayersWithSameIDException(id, cursor.count());
		}
		
		DBObject obj = cursor.next();
		return convertFromObject(obj);		
	}
	
	private DBCollection getCollection() {
		return db.getCollection(COLLECTION_NAME);
	}
	protected Player convertFromObject(DBObject obj){
		Player p = new Player();
		
		p.setId((String)obj.get("id"));
		p.setDead((boolean)obj.get("isDead"));
		p.setLat((double)obj.get("lat"));
		p.setLng((double)obj.get("lng"));
		p.setUserId((String)obj.get("userId"));
		p.setWerewolf((boolean)obj.get("isWerewolf"));
		p.setCanKill((boolean)obj.get("canKill"));
		p.setVotedAgainst((String)obj.get("votedAgainst"));
				
		return p;
	}
}
