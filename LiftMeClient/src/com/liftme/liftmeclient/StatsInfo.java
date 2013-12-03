package com.liftme.liftmeclient;

import java.util.ArrayList;

public class StatsInfo {

	String name;
	String address;
	String telNo;
	String PlateNo;
	ArrayList<Feedback> comments = new ArrayList<Feedback>();
	String dtravelled;
	String lifterCount;
	String lifteeCount;
	
	public StatsInfo(String name, String address, String telNo, String plateNo,
			ArrayList<Feedback> comments, String dtravelled,
			String lifterCount, String lifteeCount) {
		super();
		this.name = name;
		this.address = address;
		this.telNo = telNo;
		PlateNo = plateNo;
		this.comments = comments;
		this.dtravelled = dtravelled;
		this.lifterCount = lifterCount;
		this.lifteeCount = lifteeCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getPlateNo() {
		return PlateNo;
	}

	public void setPlateNo(String plateNo) {
		PlateNo = plateNo;
	}

	public ArrayList<Feedback> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Feedback> comments) {
		this.comments = comments;
	}

	public String getDtravelled() {
		return dtravelled;
	}

	public void setDtravelled(String dtravelled) {
		this.dtravelled = dtravelled;
	}

	public String getLifterCount() {
		return lifterCount;
	}

	public void setLifterCount(String lifterCount) {
		this.lifterCount = lifterCount;
	}

	public String getLifteeCount() {
		return lifteeCount;
	}

	public void setLifteeCount(String lifteeCount) {
		this.lifteeCount = lifteeCount;
	}
	

}
