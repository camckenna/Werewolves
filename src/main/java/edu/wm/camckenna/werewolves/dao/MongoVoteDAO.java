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
import edu.wm.camckenna.werewolves.domain.Vote;
import edu.wm.camckenna.werewolves.domain.Player;

public class MongoVoteDAO implements IVoteDAO {

	private DB db;
	public String COLLECTION_NAME;
	public final static String DATABASE_NAME = "WEREWOLVES";
	
	@Autowired private MongoClient mongo;
	public MongoVoteDAO() throws UnknownHostException{
		mongo = new MongoClient("localhost", 27017);
		db = mongo.getDB(DATABASE_NAME);
		COLLECTION_NAME = "VOTES";
	}
	public void setCollectionName(String coll){
		COLLECTION_NAME = coll;
	}
	public String getCollectionName(){
		return COLLECTION_NAME;
	}
	@Override
	public void addVote(Vote vote) {
		DBCollection coll = getCollection();
		BasicDBObject doc = new BasicDBObject();
		
		doc.put("voterID", vote.getVoterID());
		doc.put("votedAgainstID", vote.getVotedAgainstID());
		doc.put("date", vote.getTimestampDate().getTime());
		coll.insert(doc);
			}

	@Override
	public List<Vote> getAllVotes() {
		List<Vote> votes = new ArrayList<>();
		DBCollection coll = getCollection();
		DBCursor cursor = coll.find();
		
		while(cursor.hasNext()){
			Vote vote = convertFromObject(cursor.next());
			votes.add(vote);
		}
		return votes;
	}

	@Override
	public List<Vote> getAllVotesinTimeRange(long start, long end) {
		List<Vote> votes = new ArrayList<>();
		DBCollection coll = getCollection();
		
		DBObject query = new BasicDBObject("date", new BasicDBObject("$ne", end)).
                append("date", new BasicDBObject("$gt", start));
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("date", new BasicDBObject("$ne", end));
		searchQuery.put("date", new BasicDBObject("$gt", start));		
		DBCursor cursor = coll.find(searchQuery);
		
		while(cursor.hasNext()){
			Vote vote = convertFromObject(cursor.next());
			votes.add(vote);
		}
		return votes;
	}

	@Override
	public List<Vote> getVotesByVoterID(String voterID) {
		List<Vote> votes = new ArrayList<>();
		DBCollection coll = getCollection();
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("voterID", voterID);
		DBCursor cursor = coll.find(searchQuery);
		
		while(cursor.hasNext()){
			Vote vote = convertFromObject(cursor.next());
			votes.add(vote);
		}
		return votes;
	}

	@Override
	public List<Vote> getVotesByVotedAgainstID(String votedAgainstID) {
		List<Vote> votes = new ArrayList<>();
		DBCollection coll = getCollection();
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("votedAgainstID", votedAgainstID);
		DBCursor cursor = coll.find(searchQuery);
		
		while(cursor.hasNext()){
			Vote vote = convertFromObject(cursor.next());
			votes.add(vote);
		}
		return votes;
	}

	private DBCollection getCollection() {
		return db.getCollection(COLLECTION_NAME);
	}
	
	@SuppressWarnings("deprecation")
	protected Vote convertFromObject(DBObject obj){
		Vote vote = new Vote();
		
		vote.setVoterID((String)obj.get("voterID"));
		vote.setVotedAgainstID((String)obj.get("votedAgainstID"));
		long time = (long)obj.get("date");
		vote.setTimestampDate(new Date(time));					
		return vote;
	}
	public void discardTable(){
		getCollection().drop();
	}

}
