package com.liftme.liftmeclient;

import java.util.ArrayList;

public class ProfileInfo {
	
	String name;
	String dtravelled;
	String lifterCount;
	String lifteeCount;
	ArrayList<Feedback> comments = new ArrayList<Feedback>();
	
	public ProfileInfo(String name, String dtravelled, String lifterCount,
			String lifteeCount, ArrayList<Feedback> comments) {
		super();
		this.name = name;
		this.dtravelled = dtravelled;
		this.lifterCount = lifterCount;
		this.lifteeCount = lifteeCount;
		this.comments = comments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ArrayList<Feedback> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Feedback> comments) {
		this.comments = comments;
	}
	
	public void addComment(Feedback fd){
		
		comments.add(fd);
	}
	
	public Feedback getComment(int i){
		
		return comments.get(i);
	}

}
