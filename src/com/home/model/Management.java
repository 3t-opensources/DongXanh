package com.home.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Management {
	
	public Integer id;
	public String file_path;
	public String file_name;
	public Integer step;
	public Integer status;
	public String created_by;
	public Timestamp created_time;
	public String hash_file;
	public Integer duplicate_status;
	public Integer capture_status;
	public Integer present_user;
	public Integer invoice_data_id;
	public InvoiceData invoice_data;
	public Date date2_company_receipt_of_invoice;
	public String owner;
	public Timestamp index_time;
	
	public Timestamp getIndex_time() {
		return index_time;
	}
	public void setIndex_time(Timestamp index_time) {
		this.index_time = index_time;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getDate2_company_receipt_of_invoice() {
		return date2_company_receipt_of_invoice;
	}
	public void setDate2_company_receipt_of_invoice(
			Date date2_company_receipt_of_invoice) {
		this.date2_company_receipt_of_invoice = date2_company_receipt_of_invoice;
	}
	public InvoiceData getInvoice_data() {
		return invoice_data;
	}
	public void setInvoice_data(InvoiceData invoice_data) {
		this.invoice_data = invoice_data;
	}
	public Integer getInvoice_data_id() {
		return invoice_data_id;
	}
	public void setInvoice_data_id(Integer invoice_data_id) {
		this.invoice_data_id = invoice_data_id;
	}
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
	public Timestamp getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Timestamp created_time) {
		this.created_time = created_time;
	}
	
}
