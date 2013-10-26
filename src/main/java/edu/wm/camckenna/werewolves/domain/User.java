package edu.wm.camckenna.werewolves.domain;

import java.util.ArrayList;
import java.util.List;

public class User implements Comparable{
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String hashedPassword;
	private String imageURL;
	private int score;
	private boolean isAdmin;
	private List<String> achievements;
	
	public User(){
		super();
	}
	public User(String id, String firstName, String lastName, String username,
			String email, String hashedPassword, String imageURL) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.hashedPassword = hashedPassword;
		this.imageURL = imageURL;
		this.score = 0;
		this.isAdmin = false;
		this.achievements = new ArrayList<String>();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public int compareTo(Object obj){
		User user = (User)obj;
		return (user.getScore() - this.score);
		}
	public void increaseScore(int i) {
		this.score+=i;	
		}
	
	public List<String> getAchievements(){
		return this.achievements;
	}
	public void addAchievement(String achievement){
		this.achievements.add(achievement);
	}
	public void setAchievements(List<String> list) {
		this.achievements = list;
		
	}
}
