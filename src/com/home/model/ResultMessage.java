package com.home.model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class ResultMessage {
	private int status_error;
	private String message;
	
	public int getStatusError() {
		return status_error;
	}
	public String getMessage() {
		return message;
	}
	public void setStatusError(int status) {
		this.status_error = status;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	public JsonElement toJsonTree() {
		return new Gson().toJsonTree(this);
	}
	
	public static void main(String[] args) {
		System.out.println(new ResultMessage());
		System.out.println(new ResultMessage().toJsonTree());
	}
}
