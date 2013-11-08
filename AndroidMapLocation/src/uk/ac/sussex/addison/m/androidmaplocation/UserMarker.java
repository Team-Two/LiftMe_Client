//081113 - MtpA -	Refactor to change getDeviceID return variable to String
//311013 - MtpA -	Changed ID data from Integer to String to reflect XML changes
//					Changed variable name deviceID to deviceID to be more reflective of actual meaning
//221013 - MtpA - Create class with details of the current position of the app

package uk.ac.sussex.addison.m.androidmaplocation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserMarker {

	private final String deviceID;
	private final double markerLat;
	private final double markerLong;
	private final Calendar markerCal;
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final String markerDate;
	private final String markerTime;

	public static class Builder {
		// Required parameters
		private final String deviceID;
		private double markerLat;
		private double markerLong;
		private Calendar markerCal;
		private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		private String markerDate;
		private String markerTime;

		// Optional parameters

		public Builder(String deviceID, double markerLat, double markerLong, Calendar markerCal) {
			this.deviceID = deviceID;
			this.markerLat = markerLat;
			this.markerLong = markerLong;
			this.markerCal = markerCal;
			this.markerDate = dateFormat.format(markerCal.getTime()).substring(0, 10);
			this.markerTime = dateFormat.format(markerCal.getTime()).substring(11);		
		} // Builder constructor - no args

		public UserMarker build() {
			return new UserMarker(this);
		} // method build
	} // class Builder

	private UserMarker(Builder builder) {
		deviceID = builder.deviceID;
		markerLat = builder.markerLat;
		markerLong = builder.markerLong;
		markerCal = builder.markerCal;
		markerDate = builder.markerDate;
		markerTime = builder.markerTime;
	} // builder constructor

	public String getDeviceID() {
		return deviceID;
	}

	public double getMarkerLat() {
		return markerLat;
	}

	public double getMarkerLong() {
		return markerLong;
	}

	public Calendar getMarkerCal() {
		return markerCal;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public String getMarkerDate() {
		return markerDate;
	}

	public String getMarkerTime() {
		return markerTime;
	}
	
} // class userMarker
