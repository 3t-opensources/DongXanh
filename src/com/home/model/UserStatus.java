package com.home.model;

public class UserStatus implements java.io.Serializable {
	private short status;
	private String description;
	
	
	public UserStatus(short status, String description) {
		super();
		this.status = status;
		this.description = description;
	}
	
	public short getStatus() {
		return status;
	}
	public String getDescription() {
		return description;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
