package edu.wm.camckenna.werewolves.models;

public class Player {
	
	private String id;
	private boolean isDead;
	private boolean canKill;
	private double lat;
	private double lng;
	private String userId;
	private boolean isWerewolf;
	private boolean isLeader;
	

	public Player(String id, boolean isDead, boolean canKill, double lat,
			double lng, String userId, boolean isWerewolf, boolean isLeader) {
		super();
		this.id = id;
		this.isDead = isDead;
		this.canKill = canKill;
		this.lat = lat;
		this.lng = lng;
		this.userId = userId;
		this.isWerewolf = isWerewolf;
		this.isLeader = isLeader;
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

	public boolean isCanKill() {
		return canKill;
	}

	public void setCanKill(boolean canKill) {
		this.canKill = canKill;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isWerewolf() {
		return isWerewolf;
	}

	public void setWerewolf(boolean isWerewolf) {
		this.isWerewolf = isWerewolf;
	}

	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}
	
	

}
