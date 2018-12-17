package com.home.model;

import java.util.Date;

public class Management {
	
	private Integer id;
	private String file_path;
	private String file_name;
	private Integer step;
	private Integer status;
	private String created_by;
	private Date created_time;
	private String hash_file;
	private Integer duplicate_status;
	private Integer capture_status;
	private Integer present_user;
	
	public Integer getCapture_status() {
		return capture_status;
	}
	public void setCapture_status(Integer capture_status) {
		this.capture_status = capture_status;
	}
	public Integer getPresent_user() {
		return present_user;
	}
	public void setPresent_user(Integer present_user) {
		this.present_user = present_user;
	}
	
	public Integer getDuplicate_status() {
		return duplicate_status;
	}
	public void setDuplicate_status(Integer duplicate_status) {
		this.duplicate_status = duplicate_status;
	}
	public String getHash_file() {
		return hash_file;
	}
	public void setHash_file(String hash_file) {
		this.hash_file = hash_file;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	
}
