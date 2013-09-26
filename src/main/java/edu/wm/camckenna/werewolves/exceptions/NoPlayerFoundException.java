package edu.wm.camckenna.werewolves.exceptions;


public class NoPlayerFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userID;
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public NoPlayerFoundException(String userId) {
		super();
		this.userID = userId;
	}
	public String toString(){
		return "No player with ID: " + userID + " was found in this database"; 
	}
}
