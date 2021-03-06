package com.liftme.liftmeclient;

public class Feedback {

	String name;
	String date;
	String comment;
	String like;
	String dislike;
	
	public Feedback(String name, String date, String comment, String like, String dislike) {
		super();
		this.name = name;
		this.date = date;
		this.comment = comment;
		this.like = like;
		this.dislike = dislike;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public String getDislike() {
		return dislike;
	}

	public void setDislike(String dislike) {
		this.dislike = dislike;
	}
	
}
