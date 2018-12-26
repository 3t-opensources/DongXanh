package com.home.model;

import com.google.gson.Gson;

public class JobImport {
	private String file_name = "";
	private String base64 = "";
	private String user_name = "";

	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(new JobImport());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
