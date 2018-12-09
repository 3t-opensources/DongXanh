package com.home.model;

import java.math.BigDecimal;
import java.util.Date;

public class InvoiceData {
	private Integer id;
	private Management management_id;
	private InvoiceType invoice_type_id;
	private Customer customer_id;
	private String customer_name;
	private String invoice_name;
	private Customer customer_id_level1;
	private String customer_name_level1;
	private User user_id;
	private String user_name;
	private Date date_company_received;
	private Date date_product_received;
	private Integer date_sent_late;
	private String notes;
	private Product product_id;
	private String product_name;
	private Float total_box;
	private Integer quantity;
	public BigDecimal total_price;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Management getManagement_id() {
		return management_id;
	}
	public void setManagement_id(Management management_id) {
		this.management_id = management_id;
	}
	public InvoiceType getInvoice_type_id() {
		return invoice_type_id;
	}
	public void setInvoice_type_id(InvoiceType invoice_type_id) {
		this.invoice_type_id = invoice_type_id;
	}
	public Customer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Customer customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getInvoice_name() {
		return invoice_name;
	}
	public void setInvoice_name(String invoice_name) {
		this.invoice_name = invoice_name;
	}
	public Customer getCustomer_id_level1() {
		return customer_id_level1;
	}
	public void setCustomer_id_level1(Customer customer_id_level1) {
		this.customer_id_level1 = customer_id_level1;
	}
	public String getCustomer_name_level1() {
		return customer_name_level1;
	}
	public void setCustomer_name_level1(String customer_name_level1) {
		this.customer_name_level1 = customer_name_level1;
	}
	public User getUser_id() {
		return user_id;
	}
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Date getDate_company_received() {
		return date_company_received;
	}
	public void setDate_company_received(Date date_company_received) {
		this.date_company_received = date_company_received;
	}
	public Date getDate_product_received() {
		return date_product_received;
	}
	public void setDate_product_received(Date date_product_received) {
		this.date_product_received = date_product_received;
	}
	public Integer getDate_sent_late() {
		return date_sent_late;
	}
	public void setDate_sent_late(Integer date_sent_late) {
		this.date_sent_late = date_sent_late;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Product getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Product product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Float getTotal_box() {
		return total_box;
	}
	public void setTotal_box(Float total_box) {
		this.total_box = total_box;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getTotal_price() {
		return total_price;
	}
	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}
	
	
}
