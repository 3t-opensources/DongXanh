package com.home.entities;

import java.util.List;

public class ReportInvoiceByCus1 {

	public int no;
	public String customer1_code;
	public String getCustomer1_code() {
		return customer1_code;
	}
	public void setCustomer1_code(String customer1_code) {
		this.customer1_code = customer1_code;
	}
	public String customer1_name;
	public int total_cus2_follow;
	public int total_cus2_sent;
	public int total_invoice_valid;
	public float percent_invoice_valid;
	public String product_codes;
	public String product_names;
	public String total_products_before_phase;
	public String total_products_in_phase;
	public String total_products_all_phase;
	
	public int total_invoices;
	public String sum_total_money;
	public List<ReportInvoiceByCus1> listData;
	
	public int getTotal_invoice_valid() {
		return total_invoice_valid;
	}
	public void setTotal_invoice_valid(int total_invoice_valid) {
		this.total_invoice_valid = total_invoice_valid;
	}
	public String getProduct_names() {
		return product_names;
	}
	public void setProduct_names(String product_names) {
		this.product_names = product_names;
	}
	public List<ReportInvoiceByCus1> getListData() {
		return listData;
	}
	public void setListData(List<ReportInvoiceByCus1> listData) {
		this.listData = listData;
	}
	public int getTotal_invoices() {
		return total_invoices;
	}
	public String getSum_total_money() {
		return sum_total_money;
	}
	public void setTotal_invoices(int total_invoices) {
		this.total_invoices = total_invoices;
	}
	public void setSum_total_money(String sum_total_money) {
		this.sum_total_money = sum_total_money;
	}
	public float getPercent_invoice_valid() {
		if(percent_invoice_valid > 0){
			return percent_invoice_valid;	
		}else{
			return (float)total_invoice_valid*100/total_invoices;
		}
		
	}
	public void setPercent_invoice_valid(float percent_invoice_valid) {
		this.percent_invoice_valid = percent_invoice_valid;
	}
	public int getNo() {
		return no;
	}
	public String getCustomer1_name() {
		return customer1_name;
	}
	public int getTotal_cus2_follow() {
		return total_cus2_follow;
	}
	public int getTotal_cus2_sent() {
		return total_cus2_sent;
	}
	
	public String getProduct_codes() {
		return product_codes;
	}
	public String getTotal_products_before_phase() {
		return total_products_before_phase;
	}
	public String getTotal_products_in_phase() {
		return total_products_in_phase;
	}
	public String getTotal_products_all_phase() {
		return total_products_all_phase;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setCustomer1_name(String customer1_name) {
		this.customer1_name = customer1_name;
	}
	public void setTotal_cus2_follow(int total_cus2_follow) {
		this.total_cus2_follow = total_cus2_follow;
	}
	public void setTotal_cus2_sent(int total_cus2_sent) {
		this.total_cus2_sent = total_cus2_sent;
	}
	
	public void setProduct_codes(String product_codes) {
		this.product_codes = product_codes;
	}
	public void setTotal_products_before_phase(String total_products_before_phase) {
		this.total_products_before_phase = total_products_before_phase;
	}
	public void setTotal_products_in_phase(String total_products_in_phase) {
		this.total_products_in_phase = total_products_in_phase;
	}
	public void setTotal_products_all_phase(String total_products_all_phase) {
		this.total_products_all_phase = total_products_all_phase;
	}
	
}
