package com.home.entities;

import java.util.Date;

public class ReportInvoiceDaily {
	public int no;
	public String staff_name;
	public String customer1_codes;
	public String customer1_names;
	public Date date1_receipt_of_product;
	public Date date2_company_receipt_of_invoice;
	public Date getDate2_company_receipt_of_invoice() {
		return date2_company_receipt_of_invoice;
	}
	public void setDate2_company_receipt_of_invoice(
			Date date2_company_receipt_of_invoice) {
		this.date2_company_receipt_of_invoice = date2_company_receipt_of_invoice;
	}
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
	public void setTotal_moneys(String total_moneys) {
		this.total_moneys = total_moneys;
	}
	public Date getDate1_receipt_of_product() {
		return date1_receipt_of_product;
	}
	public void setDate1_receipt_of_product(Date date1_receipt_of_product) {
		this.date1_receipt_of_product = date1_receipt_of_product;
	}
	
	
	
}
