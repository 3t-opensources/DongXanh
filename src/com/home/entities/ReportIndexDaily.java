package com.home.entities;

import java.math.BigDecimal;
import java.util.Date;

public class ReportIndexDaily {

	public int no;
	public String customer_code;
	public String customer_name;
	public String customer1_code;
	public String customer1_name;
	public String getCustomer1_name() {
		return customer1_name;
	}
	public void setCustomer1_name(String customer1_name) {
		this.customer1_name = customer1_name;
	}
	public String staff_name;
	public Date date1_receipt_of_product;
	public String product_ids;
	public String product_names;
	public String total_quantities;
	public String getTotal_quantities() {
		return total_quantities;
	}
	public void setTotal_quantities(String total_quantities) {
		this.total_quantities = total_quantities;
	}
	public String total_boxs;
	public String total_prices;
	public BigDecimal sum_total_price;
	public String notes;
	public Integer date_sent_late; 
	
	public Integer getDate_sent_late() {
		return date_sent_late;
	}
	public void setDate_sent_late(Integer date_sent_late) {
		this.date_sent_late = date_sent_late;
	}
	public int getNo() {
		return no;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public String getCustomer1_code() {
		return customer1_code;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public Date getDate1_receipt_of_product() {
		return date1_receipt_of_product;
	}
	public String getProduct_ids() {
		return product_ids;
	}
	public String getProduct_names() {
		return product_names;
	}
	public String getTotal_boxs() {
		return total_boxs;
	}
	public String getTotal_prices() {
		return total_prices;
	}
	public BigDecimal getSum_total_price() {
		return sum_total_price;
	}
	public String getNotes() {
		return notes;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public void setCustomer1_code(String customer1_code) {
		this.customer1_code = customer1_code;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public void setDate1_receipt_of_product(Date date1_receipt_of_product) {
		this.date1_receipt_of_product = date1_receipt_of_product;
	}
	public void setProduct_ids(String product_ids) {
		this.product_ids = product_ids;
	}
	public void setProduct_names(String product_names) {
		this.product_names = product_names;
	}
	public void setTotal_boxs(String total_boxs) {
		this.total_boxs = total_boxs;
	}
	public void setTotal_prices(String total_prices) {
		this.total_prices = total_prices;
	}
	public void setSum_total_price(BigDecimal sum_total_price) {
		this.sum_total_price = sum_total_price;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
