package com.home.model;

// Generated Mar 17, 2016 9:59:50 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * GroupCustomer generated by hbm2java
 */
public class GroupCustomerDetail implements java.io.Serializable {
	private Integer id;
	private Integer groupCustomerId;
	private String groupName;
	private String note;
	
	public GroupCustomerDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GroupCustomerDetail(Integer id, Integer groupCustomerId,
			String groupName, String note) {
		super();
		this.id = id;
		this.groupCustomerId = groupCustomerId;
		this.groupName = groupName;
		this.note = note;
	}
	
	public GroupCustomerDetail(Integer groupCustomerId,
			String groupName) {
		super();
		this.groupCustomerId = groupCustomerId;
		this.groupName = groupName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGroupCustomerId() {
		return groupCustomerId;
	}
	public void setGroupCustomerId(Integer groupCustomerId) {
		this.groupCustomerId = groupCustomerId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
