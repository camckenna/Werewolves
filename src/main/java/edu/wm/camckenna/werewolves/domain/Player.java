package edu.wm.camckenna.werewolves.domain;

public class Player {

	// Pojo! Plain old java object
	private String id;
	private boolean isDead;
	private double lat;
	private double lng;
	private String username;
	private boolean isWerewolf;
	private boolean isHunter;
	private boolean canKill;
	private boolean hasReported;
	private String votedAgainst;
	
	public Player(String id, boolean isDead, double lat, double lng,
			String username, boolean isWerewolf, boolean canKill, String votedAgainst) {
		super();
		this.id = id;
		this.isDead = isDead;
		this.lat = lat;
		this.lng = lng;
		this.username = username;
		this.isWerewolf = isWerewolf;
		this.isHunter = false;
		this.canKill = canKill;
		this.hasReported = true;
		this.votedAgainst = votedAgainst;

	}
	public Player() {
		super();
	}

	public boolean isWerewolf() {
		return isWerewolf;
	}

	public void setWerewolf(boolean isWerewolf) {
		this.isWerewolf = isWerewolf;
	}

	public boolean isHunter() {
		return isHunter;
	}
	public void setHunter(boolean isHunter) {
		this.isHunter = isHunter;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isDead() {
		return isDead;
	}
	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	public boolean hasReported() {
		return hasReported;
	}
	public void setHasReported(boolean hasReported) {
		this.hasReported = hasReported;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean getCanKill() {
		return canKill;
	}
	public void setCanKill(boolean canKill) {
		this.canKill = canKill;
	}

	public String getVotedAgainst() {
		return votedAgainst;
	}
	public void setVotedAgainst(String votedAgainst) {
		this.votedAgainst = votedAgainst;
	}
	
}
