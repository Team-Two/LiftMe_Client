//021213 - MtpA -	Create class

package com.liftme.liftmeclient;

public class LifterTrip {

	private final String lifterName;
	private final String lifterDestination;
	private final String destLat;
	private final String destLong;
	private final String lifterCurrLat;
	private final String lifterCurrLong;
	private final String lifterDate;
	private final String lifterTime;
	
	public LifterTrip(String vLifterName, String vLifterDest, String vDestLat, String vDestLong, String vCurrLat, String vCurrLong, String vLifterDate, String vLifterTime) {
		this.lifterName = vLifterName;
		this.lifterDestination = vLifterDest;
		this.destLat = vDestLat;
		this.destLong = vDestLong;
		this.lifterCurrLat = vCurrLat;
		this.lifterCurrLong = vCurrLong;
		this.lifterDate = vLifterDate;
		this.lifterTime = vLifterTime;
	}

	public String getLifterName() {
		return lifterName;
	}

	public String getLifterDestination() {
		return lifterDestination;
	}

	public String getDestLat() {
		return destLat;
	}

	public String getDestLong() {
		return destLong;
	}

	public String getLifterCurrLat() {
		return lifterCurrLat;
	}

	public String getLifterCurrLong() {
		return lifterCurrLong;
	}

	public String getLifterDate() {
		return lifterDate;
	}

	public String getLifterTime() {
		return lifterTime;
	}
}
