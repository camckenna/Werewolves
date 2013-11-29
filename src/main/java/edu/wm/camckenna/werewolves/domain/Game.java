package edu.wm.camckenna.werewolves.domain;

import java.util.Date;

public class Game {
	
	private int dayNightFreq;	
	private Date createdDate;
	private boolean isRunning;
	private boolean isDay;
	private double scentRadius; //in kilometer
	private double killRadius;
	private String result;
	
	public Game(int dayNightFreq, Date createdDate) {
		super();
		this.dayNightFreq = dayNightFreq;
		this.createdDate = createdDate;
		this.isDay = true;
		this.scentRadius = 0.1; 
		this.killRadius = 0.01; 
		this.result = "Running";
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
	public boolean isDay() {
		return this.isDay;
	}
	public void setDay(boolean day) {
		this.isDay = day;
	}
	public double getScentRadius() {
		return scentRadius;
	}
	public void setScentRadius(double scentRadius) {
		this.scentRadius = scentRadius;
	}
	public double getKillRadius() {
		return killRadius;
	}
	public void setKillRadius(double killRadius) {
		this.killRadius = killRadius;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
