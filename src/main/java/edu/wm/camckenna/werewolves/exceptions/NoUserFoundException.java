package edu.wm.camckenna.werewolves.exceptions;

public class NoUserFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private String userID;
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public NoUserFoundException(String userId) {
		super();
		this.userID = userId;
	}
	public String toString(){
		return "No user with ID: " + userID + " was found in this database"; 
	}
}
