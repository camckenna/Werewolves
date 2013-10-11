package edu.wm.camckenna.werewolves.domain;

public class BooleanMessage {
	private boolean booleanValue;
	private String message;
	
	public BooleanMessage(boolean bool, String message) {
		super();
		this.booleanValue = bool;
		this.message = message;
	}

	public boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(boolean bool) {
		this.booleanValue = bool;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
