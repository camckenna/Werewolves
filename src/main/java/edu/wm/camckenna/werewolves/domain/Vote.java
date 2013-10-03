package edu.wm.camckenna.werewolves.domain;

import java.util.Date;

public class Vote {
	private String voterID;
	private String votedAgainstID;
	private Date timestampDate;
	
	public Vote(){
		super();
	}

	public Vote(String voterID, String votedAgainstID, Date timestampDate) {
		super();
		this.voterID = voterID;
		this.votedAgainstID = votedAgainstID;
		this.timestampDate = timestampDate;
	}

	public String getVoterID() {
		return voterID;
	}

	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}

	public String getVotedAgainstID() {
		return votedAgainstID;
	}

	public void setVotedAgainstID(String votedAgainstID) {
		this.votedAgainstID = votedAgainstID;
	}

	public Date getTimestampDate() {
		return timestampDate;
	}

	public void setTimestampDate(Date timestampDate) {
		this.timestampDate = timestampDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((timestampDate == null) ? 0 : timestampDate.hashCode());
		result = prime * result
				+ ((votedAgainstID == null) ? 0 : votedAgainstID.hashCode());
		result = prime * result + ((voterID == null) ? 0 : voterID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vote other = (Vote) obj;
		if (timestampDate == null) {
			if (other.timestampDate != null)
				return false;
		} else if (!timestampDate.equals(other.timestampDate))
			return false;
		if (votedAgainstID == null) {
			if (other.votedAgainstID != null)
				return false;
		} else if (!votedAgainstID.equals(other.votedAgainstID))
			return false;
		if (voterID == null) {
			if (other.voterID != null)
				return false;
		} else if (!voterID.equals(other.voterID))
			return false;
		return true;
	}
	
}
