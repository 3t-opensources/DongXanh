package com.home.model;

import com.google.gson.Gson;

public class ResultMessage {
	private int status;
	private String message;
	
	public int getStatus() {
		return status;
	}
	public String getMessage() {
		return message;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	public static void main(String[] args) {
		System.out.println(new ResultMessage());
	}
}
