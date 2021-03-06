package com.home.entities;

import java.math.BigDecimal;
import java.util.Date;

public class ReportInvoiceDetailByCus2 {

	public int no;
	public String customer2_code;
	public String customer2_name;
	public String customer1_name;
	public Date date1_receipt_of_product;
	public Date date2_company_receipt_of_invoice;
	public Integer date_sent_late;
	public String product_ids;
	public String product_names;
	public String total_boxs;
	public String quantitys;
	public String unit_prices;
	public String total_prices;
	public BigDecimal sum_total_price;
	
	public int getNo() {
		return no;
	}
	public String getCustomer2_code() {
		return customer2_code;
	}
	public String getCustomer2_name() {
		return customer2_name;
	}
	public String getCustomer1_name() {
		return customer1_name;
	}
	public Integer getDate_sent_late() {
		return date_sent_late;
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
	public String getUnit_prices() {
		return unit_prices;
	}
	public String getTotal_prices() {
		return total_prices;
	}
	public BigDecimal getSum_total_price() {
		return sum_total_price;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setCustomer2_code(String customer2_code) {
		this.customer2_code = customer2_code;
	}
	public void setCustomer2_name(String customer2_name) {
		this.customer2_name = customer2_name;
	}
	public void setCustomer1_name(String customer1_name) {
		this.customer1_name = customer1_name;
	}
	public void setDate_sent_late(Integer date_sent_late) {
		this.date_sent_late = date_sent_late;
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
	public void setUnit_prices(String unit_prices) {
		this.unit_prices = unit_prices;
	}
	public void setTotal_prices(String total_prices) {
		this.total_prices = total_prices;
	}
	public void setSum_total_price(BigDecimal sum_total_price) {
		this.sum_total_price = sum_total_price;
	}
	public Date getDate1_receipt_of_product() {
		return date1_receipt_of_product;
	}
	public Date getDate2_company_receipt_of_invoice() {
		return date2_company_receipt_of_invoice;
	}
	public void setDate1_receipt_of_product(Date date1_receipt_of_product) {
		this.date1_receipt_of_product = date1_receipt_of_product;
	}
	public void setDate2_company_receipt_of_invoice(
			Date date2_company_receipt_of_invoice) {
		this.date2_company_receipt_of_invoice = date2_company_receipt_of_invoice;
	}
		
	
}
