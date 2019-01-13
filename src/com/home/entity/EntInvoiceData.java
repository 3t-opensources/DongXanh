package com.home.entity;

import java.util.Date;

import com.google.gson.Gson;

public class EntInvoiceData {
	private String id;
	private String management_id;
	private String invoice_type_id;
	private String invoice_type;
	private String customer_id;
	private String customer_code;
	private String customer_name;
	private String customer_id_level1;
	private String customer_code_level1;
	private String customer_name_level1;
	private String staff_id;
	private String staff_name;
	private Date date_company_received;
	private Date date_product_received;
	private String date_sent_late;
	private String notes;
	private String product_ids;
	private String product_names;
	private String total_boxs;
	private String quantitys;
	private String total_prices;
	public String sum_total_price;
	public String unit_prices;

	public String getUnit_prices() {
		return unit_prices;
	}

	public void setUnit_prices(String unit_prices) {
		this.unit_prices = unit_prices;
	}

	public String getId() {
		return id;
	}

	public String getManagement_id() {
		return management_id;
	}

	public String getInvoice_type_id() {
		return invoice_type_id;
	}

	public String getInvoice_type() {
		return invoice_type;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public String getCustomer_code() {
		return customer_code;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public String getCustomer_id_level1() {
		return customer_id_level1;
	}

	public String getCustomer_code_level1() {
		return customer_code_level1;
	}

	public String getCustomer_name_level1() {
		return customer_name_level1;
	}

	public String getStaff_id() {
		return staff_id;
	}

	public String getStaff_name() {
		return staff_name;
	}

	public Date getDate_company_received() {
		return date_company_received;
	}

	public Date getDate_product_received() {
		return date_product_received;
	}

	public String getDate_sent_late() {
		return date_sent_late;
	}

	public String getNotes() {
		return notes;
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

	public String getQuantitys() {
		return quantitys;
	}

	public String getTotal_prices() {
		return total_prices;
	}

	public String getSum_total_price() {
		return sum_total_price;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setManagement_id(String management_id) {
		this.management_id = management_id;
	}

	public void setInvoice_type_id(String invoice_type_id) {
		this.invoice_type_id = invoice_type_id;
	}

	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public void setCustomer_id_level1(String customer_id_level1) {
		this.customer_id_level1 = customer_id_level1;
	}

	public void setCustomer_code_level1(String customer_code_level1) {
		this.customer_code_level1 = customer_code_level1;
	}

	public void setCustomer_name_level1(String customer_name_level1) {
		this.customer_name_level1 = customer_name_level1;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}

	public void setDate_company_received(Date date_company_received) {
		this.date_company_received = date_company_received;
	}

	public void setDate_product_received(Date date_product_received) {
		this.date_product_received = date_product_received;
	}

	public void setDate_sent_late(String date_sent_late) {
		this.date_sent_late = date_sent_late;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

	public void setQuantitys(String quantitys) {
		this.quantitys = quantitys;
	}

	public void setTotal_prices(String total_prices) {
		this.total_prices = total_prices;
	}

	public void setSum_total_price(String sum_total_price) {
		this.sum_total_price = sum_total_price;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	public static EntInvoiceData fromJson(String json){
		try {	
			return  new Gson().fromJson(json, EntInvoiceData.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(new EntInvoiceData().toString());
	}
	
}
