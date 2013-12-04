package com.liftme.liftmeclient;

public class TripRequest {

	String destination;
	String date;
	String time;
	String liftee;
	String status;
	
	public TripRequest(String destination, String date, String time,
			String liftee, String status) {
		super();
		this.destination = destination;
		this.date = date;
		this.time = time;
		this.liftee = liftee;
		this.status = status;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLiftee() {
		return liftee;
	}

	public void setLiftee(String liftee) {
		this.liftee = liftee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
