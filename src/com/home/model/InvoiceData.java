package com.home.model;

import java.math.BigDecimal;
import java.util.Date;

public class InvoiceData {
	
	public Integer id;
	public Management management_id;
	public InvoiceType invoice_type_id;
	public String invoice_type_name;
	public Customer customer_id;
	public int customer_id_id;
	public String customer_code;
	public String customer_name;
	public Customer customer_id_level1;
	public int customer_id_level1_id;
	public String customer_code_level1;
	public String customer_name_level1;
	public User staff_id;
	public int staff_id_id;
	public String staff_name;
	public Date date_invoice_sent;// moi them vao
	public Date date_company_received;
	public Date date_product_received;
	public Integer date_sent_late;
	public String notes;
	public String product_ids;
	public String product_names;
	public String total_boxs;
	public String quantitys;
	public String unit_prices;
	public String total_prices;
	public BigDecimal sum_total_price;
	
	public String getUnit_prices() {
		return unit_prices;
	}
	public void setUnit_prices(String unit_prices) {
		this.unit_prices = unit_prices;
	}
	public String getInvoice_type_name() {
		return invoice_type_name;
	}
	public void setInvoice_type_name(String invoice_type_name) {
		this.invoice_type_name = invoice_type_name;
	}
	public Integer getId() {
		return id;
	}
	public Management getManagement_id() {
		return management_id;
	}
	public InvoiceType getInvoice_type_id() {
		return invoice_type_id;
	}
	public Customer getCustomer_id() {
		return customer_id;
	}
	public String getCustomer_code() {
		return customer_code;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public Customer getCustomer_id_level1() {
		return customer_id_level1;
	}
	public String getCustomer_code_level1() {
		return customer_code_level1;
	}
	public String getCustomer_name_level1() {
		return customer_name_level1;
	}
	public User getStaff_id() {
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
	public Integer getDate_sent_late() {
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
	public BigDecimal getSum_total_price() {
		return sum_total_price;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setManagement_id(Management management_id) {
		this.management_id = management_id;
	}
	public void setInvoice_type_id(InvoiceType invoice_type_id) {
		this.invoice_type_id = invoice_type_id;
	}
	public void setCustomer_id(Customer customer_id) {
		this.customer_id = customer_id;
	}
	public void setCustomer_code(String customer_code) {
		this.customer_code = customer_code;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public void setCustomer_id_level1(Customer customer_id_level1) {
		this.customer_id_level1 = customer_id_level1;
	}
	public void setCustomer_code_level1(String customer_code_level1) {
		this.customer_code_level1 = customer_code_level1;
	}
	public void setCustomer_name_level1(String customer_name_level1) {
		this.customer_name_level1 = customer_name_level1;
	}
	public void setStaff_id(User staff_id) {
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
	public void setDate_sent_late(Integer date_sent_late) {
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
	public void setSum_total_price(BigDecimal sum_total_price) {
		this.sum_total_price = sum_total_price;
	}
	public Date getDate_invoice_sent() {
		return date_invoice_sent;
	}
	public void setDate_invoice_sent(Date date_invoice_sent) {
		this.date_invoice_sent = date_invoice_sent;
	}
	public int getCustomer_id_id() {
		return customer_id_id;
	}
	public int getCustomer_id_level1_id() {
		return customer_id_level1_id;
	}
	public int getStaff_id_id() {
		return staff_id_id;
	}
	public void setCustomer_id_id(int customer_id_id) {
		this.customer_id_id = customer_id_id;
	}
	public void setCustomer_id_level1_id(int customer_id_level1_id) {
		this.customer_id_level1_id = customer_id_level1_id;
	}
	public void setStaff_id_id(int staff_id_id) {
		this.staff_id_id = staff_id_id;
	}
		
}
