package com.home.entities;

import java.util.Date;

public class ReportInvoiceDaily {
	public int no;
	public String staff_name;
	public String customer1_codes;
	public String customer1_names;
	public Date dates_received;
	public String total_moneys;
	public int getNo() {
		return no;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public String getCustomer1_codes() {
		return customer1_codes;
	}
	public String getCustomer1_names() {
		return customer1_names;
	}
	public Date getDates_received() {
		return dates_received;
	}
	public String getTotal_moneys() {
		return total_moneys;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public void setCustomer1_codes(String customer1_codes) {
		this.customer1_codes = customer1_codes;
	}
	public void setCustomer1_names(String customer1_names) {
		this.customer1_names = customer1_names;
	}
	public void setDates_received(Date dates_received) {
		this.dates_received = dates_received;
	}
	public void setTotal_moneys(String total_moneys) {
		this.total_moneys = total_moneys;
	}
	
	
	
}
