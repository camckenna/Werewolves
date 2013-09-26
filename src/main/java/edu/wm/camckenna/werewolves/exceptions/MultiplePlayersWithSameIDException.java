package edu.wm.camckenna.werewolves.exceptions;

public class MultiplePlayersWithSameIDException extends Exception {
	private static final long serialVersionUID = 1L;
	private String userID;
	private int num;
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public MultiplePlayersWithSameIDException(String userId, int num) {
		super();
		this.userID = userId;
		this.num = num;
	}
	public String toString(){
		return "Muliple players (" + num+ ") with ID: " + userID + " was found in this database"; 
	}

}
