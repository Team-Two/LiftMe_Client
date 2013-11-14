package com.liftme.liftmeclient;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Journey {

	private String deviceID;
	private String sessionID;
	private double startLatitude;
	private double endLatitude;
	private double startLongditude;
	private double endLongditude;
	private String routeName;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Calendar journeyCal;
	private String journeyDate;
	private String journeyTime;

	public Journey() {
		this.deviceID = "";
		this.sessionID = "";
		this.startLatitude = 0;
		this.endLatitude = 0;
		this.startLongditude = 0;
		this.endLongditude = 0;
		this.routeName = "";
		this.journeyDate = "";
		this.journeyTime = "";
	} // constructor - no args
	
	public Journey(String vDeviceID, String vSessionID, double vStartLatitude, double vEndLatitude, double vStartLongditude, double vEndLongditude, String vRouteName, Calendar vJourneyCalendar) {
		this.deviceID = vDeviceID;
		this.sessionID = vSessionID;
		this.startLatitude = vStartLatitude;
		this.endLatitude = vEndLatitude;
		this.startLongditude = vStartLongditude;
		this.endLongditude = vEndLongditude;
		this.routeName = vRouteName;
		this.journeyDate = dateFormat.format(vJourneyCalendar.getTime()).substring(0, 10);
		this.journeyTime = dateFormat.format(vJourneyCalendar.getTime()).substring(11);		
	} // constructor - full args

	public String getDeviceID() {
		return deviceID;
	}

	public String getSessionID() {
		return sessionID;
	}

	public double getStartLatitude() {
		return startLatitude;
	}

	public double getEndLatitude() {
		return endLatitude;
	}

	public double getStartLongditude() {
		return startLongditude;
	}

	public double getEndLongditude() {
		return endLongditude;
	}

	public String getRouteName() {
		return routeName;
	}

	public Calendar getJourneyCal() {
		return journeyCal;
	}

	public String getJourneyDate() {
		return journeyDate;
	}

	public String getJourneyTime() {
		return journeyTime;
	}
	
} // class Journey
