package edu.wm.camckenna.werewolves.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import edu.wm.camckenna.werewolves.domain.Player;
import edu.wm.camckenna.werewolves.domain.Vote;

public class ModelVoteDAOTest {

	public DB db;
	private final static String testCollectionName = "testVOTES";
	@Autowired private MongoVoteDAO voteDAO;
	@Autowired private MongoClient mongo;
	
	@Before
	public void setUp() throws Exception {
		mongo = new MongoClient("localhost", 27017);
		db = mongo.getDB(MongoVoteDAO.DATABASE_NAME);
		voteDAO = new MongoVoteDAO();
		voteDAO.setCollectionName(testCollectionName);
	}

	@After
	public void tearDown() throws Exception {
		db.getCollection(testCollectionName).drop();
	}


	@Test
	public void testAddVote() {
		Vote vote = new Vote("1", "12", new Date());
		
		voteDAO.addVote(vote);	
		
		DBCollection coll = db.getCollection(testCollectionName);
		DBObject obj = coll.findOne();
		
		assertNotNull(obj);
		assertEquals(vote.getVoterID(), (String)(obj.get("voterID")));
		assertEquals(vote.getVotedAgainstID(), (String)(obj.get("votedAgainstID")));
		assertEquals(vote.getTimestampDate().getTime(), (long)(obj.get("date")));
	}
	@Test
	public void testConvertFromObject() {
		Vote vote = new Vote("1", "12", new Date());
		
		voteDAO.addVote(vote);	
		
		DBCollection coll = db.getCollection(testCollectionName);
		DBObject obj = coll.findOne();
		
		Vote newVote = voteDAO.convertFromObject(obj);
		assertTrue(vote.equals(newVote));		
	}

	@Test
	public void testGetAllVotes() {
		Vote vote1 = new Vote("1", "12", new Date());
		Vote vote2 = new Vote("1", "1", new Date());
	
		voteDAO.addVote(vote1);
		voteDAO.addVote(vote2);
		
		List<Vote> votes = new ArrayList<Vote>();
		votes.add(vote1);
		votes.add(vote2);
		
		List<Vote> voteDB = voteDAO.getAllVotes();
		
		Set<Vote> s1 = new HashSet<Vote>(votes);
		Set<Vote> s2 = new HashSet<Vote>(voteDB);
		
		assertEquals(s1, s2);
	
	}

	@Test
	public void testGetAllVotesinTimeRange() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVotesByVoterID() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetVotesByVotedAgainstID() {
		fail("Not yet implemented");
	}



}
