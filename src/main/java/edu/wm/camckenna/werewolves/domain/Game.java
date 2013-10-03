package edu.wm.camckenna.werewolves.domain;

import java.util.Date;

public class Game {

	private int dayNightFreq;
	private Date createdDate;
	private boolean isRunning;
	private long timer;
	
	public Game(int dayNightFreq, Date createdDate) {
		super();
		this.dayNightFreq = dayNightFreq;
		this.createdDate = createdDate;
		this.timer = 0;
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
	public boolean isRunning() {
		return isRunning;
	}
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	public long getTimer() {
		return timer;
	}
	public void setTimer(long timer) {
		this.timer = timer;
	}
}
