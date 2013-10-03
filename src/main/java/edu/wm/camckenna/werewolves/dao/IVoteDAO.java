package edu.wm.camckenna.werewolves.dao;

import java.util.Date;
import java.util.List;

import edu.wm.camckenna.werewolves.domain.Vote;

public interface IVoteDAO {
	
	void addVote(Vote vote);
	void discardTable();
	List<Vote> getAllVotes();
	List<Vote> getAllVotesinTimeRange(long start, long end);
	List<Vote> getVotesByVoterID(String voterID);
	List<Vote> getVotesByVotedAgainstID(String votedAgainstID);
	
	

}
