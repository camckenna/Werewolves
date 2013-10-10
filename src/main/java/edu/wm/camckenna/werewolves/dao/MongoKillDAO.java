package edu.wm.camckenna.werewolves.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.wm.camckenna.werewolves.domain.Kill;
import edu.wm.camckenna.werewolves.domain.Player;


public class MongoKillDAO implements IKillDAO {
	
	//private DB db;
	public final static String COLLECTION_NAME = "KILLS";
	public final static String DATABASE_NAME = "WEREWOLVES";
	
	//@Autowired private MongoClient mongo;
	@Autowired private DB db;
	public MongoKillDAO() throws UnknownHostException{
		/*mongo = new MongoClient("localhost", 27017);
		db = mongo.getDB(DATABASE_NAME);
		*/
	}
	@Override
	public void addKill(Kill kill) {
		DBCollection coll = getCollection();
		BasicDBObject doc = new BasicDBObject();
		
		doc.put("killerID", kill.getKillerID());
		doc.put("victimID", kill.getVictimID());
		doc.put("date", kill.getTimestampDate().getTime());
		doc.put("lng", kill.getLng());
		doc.put("lat",kill.getLat());
		coll.insert(doc);

	}

	@Override
	public List<Kill> getAllKills() {
		List<Kill> kills = new ArrayList<>();
		DBCollection coll = getCollection();
		DBCursor cursor = coll.find();
		
		while(cursor.hasNext()){
			Kill kill = convertFromObject(cursor.next());
			kills.add(kill);
		}
		return kills;
	}
	@Override
	public List<Kill> getKillsByKillerID(String killerID) {
		List<Kill> kills = new ArrayList<>();
		DBCollection coll = getCollection();
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("killerID", false);
		DBCursor cursor = coll.find(searchQuery);
		
		while(cursor.hasNext()){
			Kill kill = convertFromObject(cursor.next());
			kills.add(kill);
		}
		return kills;
	}
	@Override
	public List<Kill> getKillsByVictimID(String victimID) {
		List<Kill> kills = new ArrayList<>();
		DBCollection coll = getCollection();
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("victimID", false);
		DBCursor cursor = coll.find(searchQuery);
		
		while(cursor.hasNext()){
			Kill kill = convertFromObject(cursor.next());
			kills.add(kill);
		}
		return kills;
	}
	@Override
	public List<Kill> getKillsInTimeRange(long start, long end) {
		List<Kill> kills = new ArrayList<>();
		DBCollection coll = getCollection();
		
		DBObject query = new BasicDBObject("date", new BasicDBObject("$ne", end)).
                append("date", new BasicDBObject("$gt", start));
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("date", new BasicDBObject("$ne", end));
		searchQuery.put("date", new BasicDBObject("$gt", start));		
		DBCursor cursor = coll.find(searchQuery);
		
		while(cursor.hasNext()){
			Kill kill = convertFromObject(cursor.next());
			kills.add(kill);
		}
		return kills;
	}
	private DBCollection getCollection() {
		return db.getCollection(COLLECTION_NAME);
	}
	
	@SuppressWarnings("deprecation")
	protected Kill convertFromObject(DBObject obj){
		Kill kill = new Kill();
		
		kill.setKillerID((String)obj.get("killerId"));
		kill.setVictimID((String)obj.get("victimID"));
		long time = Long.parseLong((String)obj.get("date"));
		kill.setTimestampDate(new Date(time));	
		kill.setLng((double)obj.get("lng"));
		kill.setLat((double)obj.get("lat"));
				
		return kill;
	}
	public void discardTable(){
		getCollection().drop();
	}
}