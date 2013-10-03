package edu.wm.camckenna.werewolves.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.wm.camckenna.werewolves.domain.GPSLocation;
import edu.wm.camckenna.werewolves.domain.Player;
import edu.wm.camckenna.werewolves.exceptions.MultiplePlayersWithSameIDException;
import edu.wm.camckenna.werewolves.exceptions.NoPlayerFoundException;

public class MongoPlayerDAOTest {	
	
	public DB db;
	private static final String testCollectionName = "testPLAYERS";
	
	@Autowired private MongoPlayerDAO playerDAO;
	@Autowired private MongoClient mongo;
	
	@Before
	public void setUp() throws Exception {
		mongo = new MongoClient("localhost", 27017);
		db = mongo.getDB(MongoPlayerDAO.DATABASE_NAME);
		playerDAO = new MongoPlayerDAO();
		playerDAO.setCollectionName(testCollectionName);
	
	}

	@After
	public void tearDown() throws Exception {
		db.getCollection(testCollectionName).drop();
	}
	
	@Test
	public void testCreatePlayer() {

		Player p = new Player("1", false, 0.0, 0.0, "12", false, false, "him");
			
		playerDAO.createPlayer(p);	
		
		DBCollection coll = db.getCollection(testCollectionName);
		DBObject obj = coll.findOne();
		
		assertNotNull(obj);
		assertEquals(p.getId(), (String)(obj.get("id")));
		assertEquals(p.isDead(), (boolean)(obj.get("isDead")));
		assertEquals(p.getLat(), (double)(obj.get("lat")), 0.00001);
		assertEquals(p.getLng(), (double)(obj.get("lng")), 0.00001);
		assertEquals(p.getUserId(), (String)(obj.get("userId")));
		assertEquals(p.isWerewolf(), (boolean)(obj.get("isWerewolf")));
		assertEquals(p.getCanKill(), (boolean)(obj.get("canKill")));
		assertEquals(p.getVotedAgainst(), (String)(obj.get("votedAgainst")));		
	}
	@Test
	public void testEquals(){
		Player p1 = new Player("1", false, 0.0, 0.0, "12", false, false, "him");
		Player p2 = new Player("1", false, 0.0, 0.0, "12", false, false, "him");		
		
		assertTrue(playerDAO.equals(p1, p2));
		assertTrue(playerDAO.equals(p2, p1));
	}
	@Test
	public void testConvertFromObject(){
		Player p = new Player("1", false, 0.0, 0.0, "12", false, false, "him");
		playerDAO.createPlayer(p);
		
		DBCollection coll = db.getCollection(testCollectionName);
		DBObject obj = coll.findOne();		
		Player p2 = playerDAO.convertFromObject(obj);
		
		assertTrue(playerDAO.equals(p, p2));	
	}
	@Test	
	public void testUpdatePlayer(){
		Player p = new Player("1", false, 0.0, 0.0, "12", false, false, "him");
		
		playerDAO.createPlayer(p);	
		
		p.setWerewolf(true);
		p.setCanKill(true);
		p.setLat(10.0);
		p.setLng(10.0);
		
		playerDAO.updatePlayer(p);
		
		DBCollection coll = db.getCollection(testCollectionName);
		DBObject obj = coll.findOne();
		Player p1 = playerDAO.convertFromObject(obj);
		
		assertTrue(playerDAO.equals(p, p1));
	}
	@Test
	public void testDeletePlayer(){
		Player p = new Player("1", false, 0.0, 0.0, "12", false, false, "him");		
		playerDAO.createPlayer(p);	
		try{
		Player p1 = playerDAO.getPlayerByID(p.getId());
		assertTrue(true);
			try{
				playerDAO.deletePlayer(p);
				p1 = playerDAO.getPlayerByID(p1.getId());
			}
			catch(NoPlayerFoundException e){
				assertTrue(true);
			}
		}
		catch(Exception e){
			assertTrue(false);
		}
		
	}

	
	@Test
	public void testSetDead() {
		Player p = new Player("1", false, 0.0, 0.0, "12", false, false, "him");
		
		playerDAO.createPlayer(p);
		playerDAO.setDead(p);
		
		p.setDead(true);
		
		DBCollection coll = db.getCollection(testCollectionName);
		DBObject obj = coll.findOne();		
		Player p2 = playerDAO.convertFromObject(obj);
		
		assertTrue(playerDAO.equals(p, p2));			
	}
	
	@Test
	public void testGetAllAlive() {
		Player p = new Player("1", false, 0.0, 0.0, "12", false, false, "him");
		Player p2 = new Player("2", false, 0.0, 0.0, "12", false, false, "him");
		Player p3 = new Player("3", true, 0.0, 0.0, "12", false, false, "him");
		playerDAO.createPlayer(p);
		playerDAO.createPlayer(p2);
		playerDAO.createPlayer(p3);
		
		List<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p2);
			
		List<Player> dbPlayers = playerDAO.getAllAlive();
		
		for(int x = 0; x < dbPlayers.size(); x++){
			if(		!playerDAO.equals(	players.get(x), dbPlayers.get(x)	)		)
				assertTrue(false);			
		}		
		assertTrue(true);
	}
	@Test
	public void testGetAllTownspeople() {
		Player p = new Player("1", false, 0.0, 0.0, "12", false, false, "him");
		Player p2 = new Player("2", false, 0.0, 0.0, "12", false, false, "him");
		Player p3 = new Player("3", true, 0.0, 0.0, "12", true, true, "him");
		playerDAO.createPlayer(p);
		playerDAO.createPlayer(p2);
		playerDAO.createPlayer(p3);
		
		List<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p2);
			
		List<Player> dbPlayers = playerDAO.getAllTownspeople();
		for(int x = 0; x < dbPlayers.size(); x++){
			if(		!playerDAO.equals(	players.get(x), dbPlayers.get(x)	)		)
				assertTrue(false);			
		}		
		assertTrue(true);
	}
	@Test
	public void testGetAllWerewolves() {
		Player p = new Player("1", false, 0.0, 0.0, "12", false, false,"him");
		Player p2 = new Player("2", false, 0.0, 0.0, "12", false,false, "him");
		Player p3 = new Player("3", true, 0.0, 0.0, "12", true, true,"him");
		playerDAO.createPlayer(p);
		playerDAO.createPlayer(p2);
		playerDAO.createPlayer(p3);
		
		List<Player> players = new ArrayList<Player>();
		players.add(p3);
			
		List<Player> dbPlayers = playerDAO.getAllWerewolves();
		
		for(int x = 0; x < dbPlayers.size(); x++){
			if(		!playerDAO.equals(	players.get(x), dbPlayers.get(x)	)		)
				assertTrue(false);			
		}		
		assertTrue(true);
	}
	@Test
	public void testGetAllDead() {
		Player p = new Player("1", false, 0.0, 0.0, "12", false, false,"him");
		Player p2 = new Player("2", false, 0.0, 0.0, "12", false,false, "him");
		Player p3 = new Player("3", true, 0.0, 0.0, "12", false, false,"him");
		playerDAO.createPlayer(p);
		playerDAO.createPlayer(p2);
		playerDAO.createPlayer(p3);
		
		List<Player> players = new ArrayList<Player>();
		players.add(p3);
			
		List<Player> dbPlayers = playerDAO.getAllDead();
		
		for(int x = 0; x < dbPlayers.size(); x++){
			if(		!playerDAO.equals(	players.get(x), dbPlayers.get(x)	)		)
				assertTrue(false);			
		}		
		assertTrue(true);
	}
		@Test
	public void testGetAllPlayers() {
		Player p = new Player("1", false, 0.0, 0.0, "12", false, false, "him");
		Player p2 = new Player("2", false, 0.0, 0.0, "12", false, false, "him");
		Player p3 = new Player("3", true, 0.0, 0.0, "12", false, false, "him");
		playerDAO.createPlayer(p);
		playerDAO.createPlayer(p2);
		playerDAO.createPlayer(p3);
		
		List<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p2);
		players.add(p3);
			
		List<Player> dbPlayers = playerDAO.getAllAlive();
		for(int x = 0; x < dbPlayers.size(); x++){
			if(		!playerDAO.equals(	players.get(x), dbPlayers.get(x)	)		)
				assertTrue(false);			
		}		
		assertTrue(true);
	}
	
	@Test
	public void testSetPlayerLocation(){
		Player p = new Player("1", false, 0.0, 0.0, "12", false, false,"him");
		playerDAO.createPlayer(p);
		
		GPSLocation gps = new GPSLocation(10.0, 10.0);
		playerDAO.setPlayerLocation(p, gps);
		
		try{
			
			Player p2 = playerDAO.getPlayerByID(p.getId());
			assertFalse(playerDAO.equals(p2, p));
			p.setLat(10.0);
			p.setLng(10.0);
			assertTrue(playerDAO.equals(p2, p));			
		}
		catch(Exception e){
			System.out.println(e.toString());
			assertTrue(false);
		}			
	}
	@Test
	public void testGetPlayerByID_OneID() {
		Player p = new Player("1", false, 0.0, 0.0, "12", false, false,"him");
		playerDAO.createPlayer(p);
		try{
		Player p2 = playerDAO.getPlayerByID(p.getId());		
		assertTrue(playerDAO.equals(p,p2));
		}
		catch(Exception e){
			assertFalse(false);
		}
	}
	@Test
	public void testGetPlayerByID_NoID() {
		Player p = new Player("1", false, 0.0, 0.0, "12", false, false,"him");
		playerDAO.createPlayer(p);
		try{
			Player p2 = playerDAO.getPlayerByID("0");		
			assertTrue(playerDAO.equals(p,p2));
		}
		catch(NoPlayerFoundException e){
			assertTrue(true);
		}
		catch(Exception e){
			assertFalse(false);
		}		
	}
	@Test
	public void testGetPlayerByID_MultipleIDs() {
		Player p = new Player("1", false, 0.0, 0.0, "12", false,false, "him");
		Player p1 = new Player("1", false, 0.0, 0.0, "10", false, false,"him");
		playerDAO.createPlayer(p);
		playerDAO.createPlayer(p1);
		try{
			Player p2 = playerDAO.getPlayerByID("1");		
			assertTrue(playerDAO.equals(p,p2));
		}
		catch(NoPlayerFoundException e){
			assertFalse(false);
		}
		catch(MultiplePlayersWithSameIDException e){
			assertTrue(true);
		}		
	}

}
