package edu.wm.camckenna.werewolves.models;

import java.util.Date;

public class Game {
	private int dayNightFreq;
	private Date createdDate;
	private boolean hasStarted;
	private boolean hasEnded;
	public Game(int dayNightFreq, Date createdDate, boolean hasStarted,
			boolean hasEnded) {
		super();
		this.dayNightFreq = dayNightFreq;
		this.createdDate = createdDate;
		this.hasStarted = hasStarted;
		this.hasEnded = hasEnded;
	}
	public int getDayNightFreq() {
		return dayNightFreq;
	}
	public void setDayNightFreq(int dayNightFreq) {
		this.dayNightFreq = dayNightFreq;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public boolean isHasStarted() {
		return hasStarted;
	}
	public void setHasStarted(boolean hasStarted) {
		this.hasStarted = hasStarted;
	}
	public boolean isHasEnded() {
		return hasEnded;
	}
	public void setHasEnded(boolean hasEnded) {
		this.hasEnded = hasEnded;
	}
	
	
	

}
