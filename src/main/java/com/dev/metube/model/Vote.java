package com.dev.metube.model;

public class Vote {
	private Integer video_id;
	private Integer user_id;
	private int value;
	
	public Integer getVideo_id() {
		return video_id;
	}
	public void setVideo_id(Integer video_id) {
		this.video_id = video_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
}
