package edu.wm.camckenna.werewolves.domain;

import java.math.BigDecimal;

public class GPSLocation {
	
	private double lat;
	private double lng;

	public GPSLocation(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	public boolean equals(GPSLocation g){
		
		return BigDecimal.valueOf(lat).equals(BigDecimal.valueOf(g.getLat())) 
				&& BigDecimal.valueOf(lng).equals(BigDecimal.valueOf(g.getLng()));
	
	}

}
