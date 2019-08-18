package com.home.entities;

import java.util.Date;


public class ReportProductDaily {

	private String product_id;
	private String customer1_name;
	private float total_box;
	private float total_box_in_year;
	private float total_box_in_day;
	private Date index_date;
	
	public String getProduct_id() {
		return product_id;
	}
	public String getCustomer1_name() {
		return customer1_name;
	}
	public float getTotal_box() {
		return total_box;
	}
	public float getTotal_box_in_year() {
		return total_box_in_year;
	}
	public float getTotal_box_in_day() {
		return total_box_in_day;
	}
	public Date getIndex_date() {
		return index_date;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public void setCustomer1_name(String customer1_name) {
		this.customer1_name = customer1_name;
	}
	public void setTotal_box(float total_box) {
		this.total_box = total_box;
	}
	public void setTotal_box_in_year(float total_box_in_year) {
		this.total_box_in_year = total_box_in_year;
	}
	public void setTotal_box_in_day(float total_box_in_day) {
		this.total_box_in_day = total_box_in_day;
	}
	public void setIndex_date(Date index_date) {
		this.index_date = index_date;
	}
	
	public void addTotal_box_in_day(float box){
		this.total_box_in_day += box;
	}
	public void addTotal_box_in_year(float box){
		this.total_box_in_year += box;
	}
	
}
