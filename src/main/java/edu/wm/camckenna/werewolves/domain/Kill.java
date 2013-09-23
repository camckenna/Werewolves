package edu.wm.camckenna.werewolves.domain;

import java.util.Date;

public class Kill {

	private String killerID;
	private String victimID;
	private Date timestampDate;
	private float lat;
	private float lng;
	
	public Kill(String killerID, String victimID, Date timestampDate,
			float lat, float lng) {
		super();
		this.killerID = killerID;
		this.victimID = victimID;
		this.timestampDate = timestampDate;
		this.lat = lat;
		this.lng = lng;
	}
	
	public String getKillerID() {
		return killerID;
	}
	public void setKillerID(String killerID) {
		this.killerID = killerID;
	}
	public String getVictimID() {
		return victimID;
	}
	public void setVictimID(String victimID) {
		this.victimID = victimID;
	}
	public Date getTimestampDate() {
		return timestampDate;
	}
	public void setTimestampDate(Date timestampDate) {
		this.timestampDate = timestampDate;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLng() {
		return lng;
	}
	public void setLng(float lng) {
		this.lng = lng;
	}
	
	
}
