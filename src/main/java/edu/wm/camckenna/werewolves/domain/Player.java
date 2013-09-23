package edu.wm.camckenna.werewolves.domain;

public class Player {

	// Pojo! Plain old java object
	private String id;
	private boolean isDead;
	private double lat;
	private double lng;
	private String userId;
	private boolean isWerewolf;
	
	public Player(String id, boolean isDead, float lat, float lng,
			String userId, boolean isWerewolf) {
		super();
		this.id = id;
		this.isDead = isDead;
		this.lat = lat;
		this.lng = lng;
		this.userId = userId;
		this.isWerewolf = isWerewolf;
	}

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public boolean isWerewolf() {
		return isWerewolf;
	}

	public void setWerewolf(boolean isWerewolf) {
		this.isWerewolf = isWerewolf;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
