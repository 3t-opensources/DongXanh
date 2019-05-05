package com.home.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.home.dao.CustomerHome;
import com.home.dao.InvoiceDataHome;
import com.home.dao.UserHome;
import com.home.entities.ReportInvoiceByCus1;
import com.home.entities.ReportInvoiceByCus2;
import com.home.entities.ReportInvoiceByStaff;
import com.home.entities.ReportInvoiceDaily;
import com.home.entities.ReportInvoiceDetailByCus2;
import com.home.model.Customer;
import com.home.model.InvoiceData;
import com.home.model.ResultMessage;
import com.home.model.User;
import com.home.util.DateUtils;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InvoiceDataAction extends ActionSupport implements ServletContextAware {
	private static final long serialVersionUID = 5109187855480607759L;
	//servletcontext for getting the context
	private ServletContext servletContext;
	private List<InvoiceData> listData = new ArrayList<InvoiceData>();
	private List<User> listStaff = new ArrayList<User>();
	private List<Customer> listCustomer = new ArrayList<Customer>();
	private Object result;
	private ResultMessage rsMess = new ResultMessage();
	private InputStream fileInputStream;
	private Workbook workbook;

	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void validate() {
		try {
			loadListStaff();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}

	public String loadListStaff() {
		try {
			UserHome userHome = new UserHome(getSessionFactory());
			listStaff = userHome.getLookupEmployee();
			result = listStaff;//new Gson().toJson(listStaff);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String loadListCustomerByStaff() {
		try {
			// Fetch Data from User Table
			int user_id;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				user_id = Integer.parseInt(request.getParameter("user_id"));
			} catch (Exception e) {
				throw new Exception("Param: user_id invalid, " + e.toString());
			}
			CustomerHome cusHome = new CustomerHome(getSessionFactory());
			listCustomer = cusHome.getListCustomerByUserId(user_id);
			result = listCustomer;//new Gson().toJson(listCustomer);
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}


	/////////////////////////////////////////REPORT//////////////////////////////////////////////////

	public String getAllInvoiceData(){
		try {
			// Fetch Data from User Table
			int management_id = 0;
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			try {
				management_id = Integer.parseInt(request.getParameter("management_id"));
			} catch (Exception e) {
				throw new Exception("Param: management_id invalid, " + e.toString());
			}
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			listData = home.getListInvoiceData(management_id);
			result = listData;//new Gson().toJson(listData);
			System.out.println("Total data: " + listData.size());
			//System.out.println(datas.get(0).getManagement_id().getFile_name());
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String getInvoiceDataFilter(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table
			Date date_company_received_from = null;
			Date date_company_received_to = null;
			int user_id = 0;
			int invoice_type = 0;
			int customer_id = 0;
			String customer_name = "";
			int sent_late = 0;
			try {
				String start_day = StringUtil.notNull(request.getParameter("start_day"));
				String end_day = StringUtil.notNull(request.getParameter("end_day"));
				invoice_type = Integer.parseInt(StringUtil.notNull(request.getParameter("invoice_type")));
				if(end_day.length() == 10 && end_day.length() == 10){
					date_company_received_from = new Date(DateUtils.getDateFromString(start_day, "dd/MM/yyyy").getTime());
					date_company_received_to = new Date(DateUtils.getDateFromString(end_day, "dd/MM/yyyy").getTime());
				}
				user_id =  Integer.parseInt(StringUtil.notNull(request.getParameter("user_id")));
				try {
					customer_id =  Integer.parseInt(StringUtil.notNull(request.getParameter("customer_id")));
					customer_name = "XXXYYYZZZ";
				} catch (Exception e) {
					customer_id = 999999999;
					customer_name = StringUtil.notNull(request.getParameter("customer_id"));
				}
				sent_late =  Integer.parseInt(StringUtil.notNull(request.getParameter("sent_late")));
			} catch (Exception e) {
				throw new Exception("List params[start_day/end_day/invoice_type/user_id/customer_id/sent_late] invalid, error: " + e.toString());
			}

			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			listData = home.getListInvoiceData(date_company_received_from, date_company_received_to, invoice_type, user_id, customer_id, customer_name, sent_late);
			result = listData;//new Gson().toJson(listData);
			System.out.println("Total data: " + listData.size());
			//System.out.println(datas.get(0).getManagement_id().getFile_name());
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String exportInvoiceData() {
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table
			Date date_company_received_from = null;
			Date date_company_received_to = null;
			int user_id = 0;
			int invoice_type = 0;
			int customer_id = 0;
			int sent_late = 0;
			String customer_name = "";
			try {
				String start_day = StringUtil.notNull(request.getParameter("start_day"));
				String end_day = StringUtil.notNull(request.getParameter("end_day"));
				invoice_type = Integer.parseInt(StringUtil.notNull(request.getParameter("invoice_type")));
				if(end_day.length() == 10 && end_day.length() == 10){
					date_company_received_from = new Date(DateUtils.getDateFromString(start_day, "dd/MM/yyyy").getTime());
					date_company_received_to = new Date(DateUtils.getDateFromString(end_day, "dd/MM/yyyy").getTime());
				}
				user_id =  Integer.parseInt(StringUtil.notNull(request.getParameter("user_id")));
				try {
					customer_id =  Integer.parseInt(StringUtil.notNull(request.getParameter("customer_id")));
					customer_name = "XXXYYYZZZ";
				} catch (Exception e) {
					customer_id = -1;
					customer_name = StringUtil.notNull(request.getParameter("customer_id"));
				}
				sent_late =  Integer.parseInt(StringUtil.notNull(request.getParameter("sent_late")));
			} catch (Exception e) {
				throw new Exception("List params[start_day/end_day/invoice_type/user_id/customer_id/sent_late] invalid, error: " + e.toString());
			}
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			listData = home.getListInvoiceData(date_company_received_from, date_company_received_to, invoice_type, user_id, customer_id, customer_name, sent_late);
			
			ServletContext servletContext = ServletActionContext.getServletContext();
			String pathname = servletContext.getRealPath("/WEB-INF/template/excel/blank.xlsx");
			File theFile = new File(pathname);
			ExcelUtil xls = new ExcelUtil();
			try (FileInputStream fis = new FileInputStream(theFile)) {
				workbook = xls.getWorkbook(fis, FilenameUtils.getExtension(theFile.getAbsolutePath()));
				Sheet sheet = workbook.getSheetAt(0);
				int startIndexRow = 0;
				int startIndexCell = 0;
				xls.addRowData(sheet, startIndexRow, startIndexCell, 
						"Loại bảng kê","Mã khách hàng","Tên khách hàng","Khách hàng cấp 1","NVTT","Ngày nhận toa","Ngày nhận hàng","Số ngày gởi trể","Ghi chú","Mã sản phẩm","Tên sản phẩm","Số lượng","Số thùng","Đơn giá","Thành tiền");
				startIndexRow++;
				for (InvoiceData entry : listData) {
					
  	        		
  	        		String c1 =  StringUtil.notNull(entry.getInvoice_type_name());
					String c2 =  StringUtil.notNull(entry.getCustomer_code());
					String c3 =  StringUtil.notNull(entry.getCustomer_name());
					String c4 =  StringUtil.notNull(entry.getCustomer_name_level1());
					String c5 =  StringUtil.notNull(entry.getStaff_name());
					String c6 =  StringUtil.notNull(entry.getDate2_company_receipt_of_invoice());
					String c7 =  StringUtil.notNull(entry.getDate1_receipt_of_product());
					String c8 =  StringUtil.notNull(entry.getDate_sent_late());
					String c9 =  StringUtil.notNull(entry.getNotes());
					if(c1.length() > 0){
						String[] arr1 =  StringUtil.notNull(entry.getProduct_ids()).split("`");
						String[] arr2 =  StringUtil.notNull(entry.getProduct_names()).split("`");
						String[] arr3 =  StringUtil.notNull(entry.getQuantitys()).split("`");
						String[] arr4 =  StringUtil.notNull(entry.getTotal_boxs()).split("`");
						String[] arr5 =  StringUtil.notNull(entry.getUnit_prices()).split("`");
						String[] arr6 =  StringUtil.notNull(entry.getTotal_prices()).split("`");
						if(arr1.length > 0){
							for (int i = 0; i < arr1.length; i++) {
								if(arr1[i].length() > 0){
									xls.addRowData(sheet, startIndexRow, startIndexCell, 
											c1,c2,c3,c4,c5,c6,c7,c8,c9,
											arr1[i],
											arr2[i],
											arr3[i],
											arr4[i],
											arr5[i],
											arr6[i]
									);
									startIndexRow++;
								}								
							}
						}
					}
				}
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				workbook.write(baos);
				fileInputStream = new ByteArrayInputStream(baos.toByteArray());
				if (listData.size() <= 0)
					addActionMessage("Không tìm thấy dữ liệu!");
				else
					addActionMessage("Kết xuất hoàn thành!");
			}
		} catch (Exception e) {
			addActionError(e.getMessage());
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//////////////////////////////////////////////NEW REPORT///////////////////////////////////////////////////////////
	public String getReportInvoiceDataByCus1(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table
			Date date_company_received_from = null;
			Date date_company_received_to = null;
			try {
				String start_day = StringUtil.notNull(request.getParameter("start_day"));
				String end_day = StringUtil.notNull(request.getParameter("end_day"));	
				if(end_day.length() == 10 && end_day.length() == 10){
					date_company_received_from = new Date(DateUtils.getDateFromString(start_day, "dd/MM/yyyy").getTime());
					date_company_received_to = new Date(DateUtils.getDateFromString(end_day, "dd/MM/yyyy").getTime());
				}
			} catch (Exception e) {
				throw new Exception("List params[start_day/end_day] invalid, error: " + e.toString());
			}
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			listData = home.getReportInvoiceDataByCus1(date_company_received_from, date_company_received_to);
			
			ReportInvoiceByCus1 report = new ReportInvoiceByCus1();
			report.setListData(new ArrayList<ReportInvoiceByCus1>());
			ReportInvoiceByCus1 reportData = new ReportInvoiceByCus1();
			int total_invoices = 0;
			BigDecimal sum_total_money = new BigDecimal(0);
			String customer_code_temp  = "";
			String customer_name_temp  = "";
			InvoiceData data_temp = null;
			StringBuilder listProductCodes = new StringBuilder();
			StringBuilder listProductNames = new StringBuilder();
			//StringBuilder listProductQuantities = new StringBuilder();
			StringBuilder listProductBoxs = new StringBuilder();
			for (InvoiceData data : listData) {
				String customer_code_level1 = data.getCustomer_code_level1();
				String customer_name_level1 = data.getCustomer_name_level1();
				String product_codes = data.getProduct_ids();
				String product_names = data.getProduct_names();
				//String product_quantities = data.getQuantitys();
				String product_boxs = data.getTotal_boxs();
				
				if((customer_code_temp.length() > 0 && !customer_code_temp.equals(customer_code_level1)) &&
						(customer_name_temp.length() > 0 && !customer_name_temp.equals(customer_name_level1))){
					reportData.setTotal_cus2_follow(getTotalCustomer2FollowByCus1(data_temp.getCustomer_id_level1().getId()));
					String[] arr_products = getProductCodeAndName(listProductCodes, listProductNames);
					reportData.setProduct_codes(arr_products[0]);
					reportData.setProduct_names(arr_products[1]);
					String[] arr_total_products = getTotalProducts(date_company_received_from, data_temp.getCustomer_id_level1().getId(), listProductCodes, listProductNames, listProductBoxs);
					reportData.setTotal_products_before_phase(arr_total_products[0]);
					reportData.setTotal_products_in_phase(arr_total_products[1]);
					reportData.setTotal_products_all_phase(arr_total_products[2]);
					report.getListData().add(reportData);
					reportData = new ReportInvoiceByCus1();
					reportData.setCustomer1_code(customer_code_level1);
					reportData.setCustomer1_name(customer_name_level1);
					reportData.setTotal_cus2_sent(1);
					reportData.setTotal_invoices(total_invoices);
					reportData.setTotal_invoices(1);
					if(data.getDate_sent_late() <= 0){
						reportData.setTotal_invoice_valid(1);
					}
					listProductCodes = new StringBuilder();
					listProductNames = new StringBuilder();
					//listProductQuantities = new StringBuilder();
					listProductBoxs = new StringBuilder();
				}else{
					reportData.setCustomer1_code(customer_code_level1);
					reportData.setCustomer1_name(customer_name_level1);
					reportData.setTotal_cus2_sent(reportData.getTotal_cus2_sent()+1);
					reportData.setTotal_invoices(reportData.getTotal_invoices()+1);
					if(data.getDate_sent_late() <= 0){
						reportData.setTotal_invoice_valid(reportData.getTotal_invoice_valid() + 1);
					}
				}
				listProductCodes.append(product_codes);
				listProductNames.append(product_names);
				//listProductQuantities.append(product_quantities);
				listProductBoxs.append(product_boxs);
				
				data_temp = data;
				customer_code_temp = customer_code_level1;
				customer_name_temp = customer_name_level1;
				total_invoices ++;
				sum_total_money = sum_total_money.add(data.getSum_total_price());
			}
			if(customer_code_temp.length() > 0 || customer_name_temp.length() > 0){
				reportData.setTotal_cus2_follow(getTotalCustomer2FollowByCus1(data_temp.getCustomer_id_level1().getId()));
				String[] arr_products = getProductCodeAndName(listProductCodes, listProductNames);
				reportData.setProduct_codes(arr_products[0]);
				reportData.setProduct_names(arr_products[1]);
				String[] arr_total_products = getTotalProducts(date_company_received_from, data_temp.getCustomer_id_level1().getId(), listProductCodes, listProductNames, listProductBoxs);
				reportData.setTotal_products_before_phase(arr_total_products[0]);
				reportData.setTotal_products_in_phase(arr_total_products[1]);
				reportData.setTotal_products_all_phase(arr_total_products[2]);
				report.getListData().add(reportData);
			}
			
			report.setTotal_invoices(total_invoices);
			report.setSum_total_money(sum_total_money.toString());
			result = report;
			listData.clear();
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}
	
	private String[] getTotalProducts(Date date_company_received_from, int customer1_code, StringBuilder listProductCodes, StringBuilder listProductNames, StringBuilder listQuantities) throws Exception{
		try {
			String arr_code[] = listProductCodes.toString().split("`");
			String arr_quantities[] = listQuantities.toString().split("`");
			
			StringBuilder resultBefore = new StringBuilder();
			StringBuilder resultIn = new StringBuilder();
			StringBuilder resultAll = new StringBuilder();
			LinkedHashMap<String, Float> mapIn = new LinkedHashMap<String, Float>();
			for (int i = 0; i < arr_code.length; i++) {
				float q = 0;
				try {
					q = Float.parseFloat(arr_quantities[i]);
				} catch (Exception e) {}
				if(mapIn.containsKey(arr_code[i])){
					mapIn.put(arr_code[i], mapIn.get(arr_code[i]) + q);
				}else{
					mapIn.put(arr_code[i], q);
				}
			}
	        Set set = mapIn.entrySet();
	        Iterator iterator = set.iterator();
	        while (iterator.hasNext()) {
	            Map.Entry item = (Map.Entry) iterator.next();
	            //System.out.println("Key = " + item.getKey() + " Value = " + item.getValue());
	            
	            int total_products_before_phase = getTotalProductBeforePhase(date_company_received_from, customer1_code, item.getKey().toString());
	            resultBefore.append(total_products_before_phase).append("`");
	            resultIn.append(item.getValue()).append("`");
	            resultAll.append(total_products_before_phase + Float.parseFloat(item.getValue().toString())).append("`");
	        }
	        
			return new String[]{resultBefore.toString(), resultIn.toString(), resultAll.toString()};
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private int getTotalProductBeforePhase(Date date_company_received_from, int customer1_code, String product_code) throws Exception {
		int total = 0;
		try {
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			Calendar cal = Calendar.getInstance();
			cal.setTime(date_company_received_from);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			if(month < 10){
				year = year -1;
			}
			java.sql.Date start_day = new Date(DateUtils.getDateFromString("01/10/"+(year), "dd/MM/yyyy").getTime());
			java.sql.Date end_day = new Date(date_company_received_from.getTime());
			List<String[]> results = home.getTotalProductBeforePhase(start_day, end_day, customer1_code, product_code);
			for (String[] arr : results) {
				String codes[] = arr[0].split("`");
				String quantitys[] = arr[1].split("`");
				int i = 0;
				for (String code : codes) {
					if(product_code.equalsIgnoreCase(code)){
						total += Integer.parseInt(quantitys[i]);
					}
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return total;
	}
	
	private String[] getProductCodeAndName(StringBuilder listProductCodes, StringBuilder listProductNames) throws Exception {
		try {
			String arr_code[] = listProductCodes.toString().split("`");
			String arr_name[] = listProductNames.toString().split("`");
			
			StringBuilder resultCode = new StringBuilder();
			StringBuilder resultName = new StringBuilder();
			List<String> dup = new ArrayList<String>();
			for (int i = 0; i < arr_code.length; i++) {
				if(!dup.contains(arr_code[i])){
					resultCode.append(arr_code[i]).append("`");
					resultName.append(arr_name[i]).append("`");
					dup.add(arr_code[i]);
				}
			}
			return new String[]{resultCode.toString(), resultName.toString()};
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private int getTotalCustomer2FollowByCus1(int customer_id) throws Exception{
		InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
		return (int)home.getTotalCustomer2FollowByCus1(customer_id);
	}
	
	public String getReportInvoiceDataByStaff(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table
			Date date_company_received_from = null;
			Date date_company_received_to = null;
			int staff_id = 0;
			try {
				String start_day = StringUtil.notNull(request.getParameter("start_day"));
				String end_day = StringUtil.notNull(request.getParameter("end_day"));	
				if(end_day.length() == 10 && end_day.length() == 10){
					date_company_received_from = new Date(DateUtils.getDateFromString(start_day, "dd/MM/yyyy").getTime());
					date_company_received_to = new Date(DateUtils.getDateFromString(end_day, "dd/MM/yyyy").getTime());
				}
				staff_id =  Integer.parseInt(StringUtil.notNull(request.getParameter("staff_id")));
			} catch (Exception e) {
				throw new Exception("List params[start_day/end_day/staff_id] invalid, error: " + e.toString());
			}
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			listData = home.getReportInvoiceDataByStaff(date_company_received_from, date_company_received_to, staff_id);
			
			List<ReportInvoiceByStaff> reports = new ArrayList<ReportInvoiceByStaff>();
			String staff_temp  = ""; 
			InvoiceData data_temp = null;
			List<String> listCus = new ArrayList<String>();
			BigDecimal revenue_in_phase = new BigDecimal(0); 
			for (InvoiceData data : listData) {
				String staff_name = StringUtil.notNull(data.getStaff_name());
				String cus_code = data.getCustomer_code();
				BigDecimal sum_total_price = data.getSum_total_price();
				//System.out.println("sum_total_price = " + sum_total_price);
				
				if(staff_temp.length() > 0 && !staff_temp.equalsIgnoreCase(staff_name)){
					ReportInvoiceByStaff report = new ReportInvoiceByStaff();
					report.setStaff_name(data_temp.getStaff_name());
					report.setTotal_customer_follow(getTotalCustomer2FollowByStaff(data_temp.getStaff_id().getId()));
					report.setTotal_customer_no_sent(getTotalCustomerNoSent(date_company_received_from, date_company_received_to, data_temp.getStaff_id().getId()));
					report.setTotal_customer_sent(listCus.size());
					String revenue_before_phase = getTotalRevenueBeforePhaseByStaff(date_company_received_from, data_temp.getStaff_id().getId());
					report.setTotal_revenue_before_phase(revenue_before_phase);
					report.setTotal_revenue_in_phase(revenue_in_phase.toString());
					if(revenue_before_phase != null && revenue_before_phase.length() > 0){
						report.setTotal_revenue_all_phase(revenue_in_phase.add(new BigDecimal(revenue_before_phase)).toString());
					}else{
						report.setTotal_revenue_all_phase(revenue_in_phase.toString());
					}
					reports.add(report);
					listCus.clear();
					revenue_in_phase = new BigDecimal(0); 
				}
				if(!listCus.contains(cus_code)){
					listCus.add(cus_code);
				}
				if(sum_total_price != null){
					revenue_in_phase = revenue_in_phase.add(sum_total_price);	
				}
				staff_temp = staff_name;
				data_temp = data;
			}
			if(staff_temp.length() > 0){
				ReportInvoiceByStaff report = new ReportInvoiceByStaff();
				report.setStaff_name(data_temp.getStaff_name());
				report.setTotal_customer_follow(getTotalCustomer2FollowByStaff(data_temp.getStaff_id().getId()));
				report.setTotal_customer_no_sent(getTotalCustomerNoSent(date_company_received_from, date_company_received_to, data_temp.getStaff_id().getId()));
				report.setTotal_customer_sent(listCus.size());
				String revenue_before_phase = getTotalRevenueBeforePhaseByStaff(date_company_received_from, data_temp.getStaff_id().getId());
				report.setTotal_revenue_before_phase(revenue_before_phase);
				report.setTotal_revenue_in_phase(revenue_in_phase.toString());
				if(revenue_before_phase != null && revenue_before_phase.length() > 0){
					report.setTotal_revenue_all_phase(revenue_in_phase.add(new BigDecimal(revenue_before_phase)).toString());
				}else{
					report.setTotal_revenue_all_phase(revenue_in_phase.toString());
				}
				reports.add(report);
				listCus.clear();
			}
			System.out.println("Total data: " + reports.size());
			result = reports;
			System.out.println(new Gson().toJson(result));
			listData.clear();
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}
	
	private String getTotalRevenueBeforePhaseByStaff(Date date_company_received_from, int staff_id) throws Exception {
		String revenue = "";
		try {
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			Calendar cal = Calendar.getInstance();
			cal.setTime(date_company_received_from);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			if(month < 10){
				year = year -1;
			}
			java.sql.Date start_day = new Date(DateUtils.getDateFromString("01/10/"+(year), "dd/MM/yyyy").getTime());
			java.sql.Date end_day = new Date(date_company_received_from.getTime());
			revenue = home.getTotalRevenueBeforePhaseByStaff(start_day, end_day, staff_id).toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return revenue;
	}
	private int getTotalCustomerNoSent(java.sql.Date start_day, java.sql.Date end_day, int staff_id) throws Exception {
		InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
		return (int)home.getTotalCustomerNoSentInvoice(start_day, end_day, staff_id);
	}
	private int getTotalCustomer2FollowByStaff(int staff_id) throws Exception{
		InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
		return (int)home.getTotalCustomer2FollowByStaff(staff_id);
	}
	public String getReportInvoiceDataByCus2(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table
			Date date_company_received_from = null;
			Date date_company_received_to = null;
			int customer_id = 0;
			try {
				String start_day = StringUtil.notNull(request.getParameter("start_day"));
				String end_day = StringUtil.notNull(request.getParameter("end_day"));	
				if(end_day.length() == 10 && end_day.length() == 10){
					date_company_received_from = new Date(DateUtils.getDateFromString(start_day, "dd/MM/yyyy").getTime());
					date_company_received_to = new Date(DateUtils.getDateFromString(end_day, "dd/MM/yyyy").getTime());
				}
				customer_id =  Integer.parseInt(StringUtil.notNull(request.getParameter("customer_id")));
			} catch (Exception e) {
				throw new Exception("List params[start_day/end_day/customer_id] invalid, error: " + e.toString());
			}
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			listData = home.getReportInvoiceDataByCus2(date_company_received_from, date_company_received_to, customer_id);
			
			String cus_temp  = ""; 
			InvoiceData data_temp = null;
			List<ReportInvoiceByCus2> reports = new ArrayList<ReportInvoiceByCus2>();
			int total_invoice_sent = 0;
			int total_invoice_valid = 0;
			BigDecimal revenue_in_phase = new BigDecimal(0); 
			for (InvoiceData data : listData) {
				String cus_code = data.getCustomer_code();
				BigDecimal sum_total_price = data.getSum_total_price();
				
				if(cus_temp.length() > 0 && !cus_temp.equalsIgnoreCase(cus_code)){
					ReportInvoiceByCus2 report = new ReportInvoiceByCus2();
					report.setCustomer2_code(data_temp.getCustomer_code());
					report.setCustomer2_name(data_temp.getCustomer_name());
					report.setTotal_invoice_sent(total_invoice_sent);
					report.setTotal_invoice_valid(total_invoice_valid);
					report.setStaff_name(data_temp.getStaff_name());
					String revenue_before_phase = getTotalRevenueBeforePhaseByCus(date_company_received_from, data_temp.getCustomer_id().getId());
					report.setTotal_revenue_before_phase(revenue_before_phase);
					report.setTotal_revenue_in_phase(revenue_in_phase.toString());
					if(revenue_before_phase != null && revenue_before_phase.length() > 0){
						report.setTotal_revenue_all_phase(revenue_in_phase.add(new BigDecimal(revenue_before_phase)).toString());
					}else{
						report.setTotal_revenue_all_phase(revenue_in_phase.toString());
					}
					reports.add(report);
					total_invoice_sent = 0;
					total_invoice_valid = 0;
					revenue_in_phase = new BigDecimal(0);
				}
				
				if(sum_total_price != null){
					revenue_in_phase = revenue_in_phase.add(sum_total_price);	
				}
				if(data.getDate_sent_late() <= 0){
					total_invoice_valid ++;
				}
				total_invoice_sent++;
				data_temp = data;
				cus_temp  = cus_code;
			}
			if(cus_temp.length() > 0){
				ReportInvoiceByCus2 report = new ReportInvoiceByCus2();
				report.setCustomer2_code(data_temp.getCustomer_code());
				report.setCustomer2_name(data_temp.getCustomer_name());
				report.setTotal_invoice_sent(total_invoice_sent);
				report.setTotal_invoice_valid(total_invoice_valid);
				report.setStaff_name(data_temp.getStaff_name());
				String revenue_before_phase = getTotalRevenueBeforePhaseByCus(date_company_received_from, data_temp.getCustomer_id().getId());
				report.setTotal_revenue_before_phase(revenue_before_phase);
				report.setTotal_revenue_in_phase(revenue_in_phase.toString());
				if(revenue_before_phase != null && revenue_before_phase.length() > 0){
					report.setTotal_revenue_all_phase(revenue_in_phase.add(new BigDecimal(revenue_before_phase)).toString());
				}else{
					report.setTotal_revenue_all_phase(revenue_in_phase.toString());
				}
				reports.add(report);
			}
			System.out.println("Total data: " + reports.size());
			result = reports;
			listData.clear();
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}
	
	private String getTotalRevenueBeforePhaseByCus(Date date_company_received_from, int customer_id) throws Exception{
		String revenue = "";
		try {
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			Calendar cal = Calendar.getInstance();
			cal.setTime(date_company_received_from);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			if(month < 10){
				year = year -1;
			}
			java.sql.Date start_day = new Date(DateUtils.getDateFromString("01/10/"+(year), "dd/MM/yyyy").getTime());
			java.sql.Date end_day = new Date(date_company_received_from.getTime());
			revenue = home.getTotalRevenueBeforePhaseByCus(start_day, end_day, customer_id).toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return revenue;
	
	}
	public String getReportInvoiceDataDetailByCus2(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table
			Date date_company_received_from = null;
			Date date_company_received_to = null;
			int customer_id = 0;
			try {
				String start_day = StringUtil.notNull(request.getParameter("start_day"));
				String end_day = StringUtil.notNull(request.getParameter("end_day"));	
				if(end_day.length() == 10 && end_day.length() == 10){
					date_company_received_from = new Date(DateUtils.getDateFromString(start_day, "dd/MM/yyyy").getTime());
					date_company_received_to = new Date(DateUtils.getDateFromString(end_day, "dd/MM/yyyy").getTime());
				}
				customer_id =  Integer.parseInt(StringUtil.notNull(request.getParameter("customer_id")));
			} catch (Exception e) {
				throw new Exception("List params[start_day/end_day/customer_id] invalid, error: " + e.toString());
			}
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			listData = home.getReportInvoiceDataDetailByCus2(date_company_received_from, date_company_received_to, customer_id);
			
			List<ReportInvoiceDetailByCus2> reports = new ArrayList<ReportInvoiceDetailByCus2>();
			for (InvoiceData data : listData) {
				ReportInvoiceDetailByCus2 report = new ReportInvoiceDetailByCus2();
				report.setCustomer2_code(data.getCustomer_code());
				report.setCustomer2_name(data.getCustomer_name());
				report.setCustomer1_name(data.getCustomer_name_level1());
				report.setDate1_receipt_of_product(data.getDate1_receipt_of_product());
				report.setDate2_company_receipt_of_invoice(data.getDate2_company_receipt_of_invoice());
				report.setDate_sent_late(data.getDate_sent_late());
				report.setProduct_ids(data.getProduct_ids());
				report.setProduct_names(data.getProduct_names());
				report.setQuantitys(data.getQuantitys());
				report.setTotal_boxs(data.getTotal_boxs());
				report.setTotal_prices(data.getTotal_prices());
				reports.add(report);
			}
			System.out.println("Total data: " + reports.size());
			result = reports;
			listData.clear();
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}
	
	public String getReportInvoiceDataDaily(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			// Fetch Data from User Table
			Date date_company_received_from = null;
			Date date_company_received_to = null;
			int staff_id = 0;
			try {
				String start_day = StringUtil.notNull(request.getParameter("start_day"));
				String end_day = StringUtil.notNull(request.getParameter("end_day"));	
				if(end_day.length() == 10 && end_day.length() == 10){
					date_company_received_from = new Date(DateUtils.getDateFromString(start_day, "dd/MM/yyyy").getTime());
					date_company_received_to = new Date(DateUtils.getDateFromString(end_day, "dd/MM/yyyy").getTime());
				}
				staff_id =  Integer.parseInt(StringUtil.notNull(request.getParameter("staff_id")));
			} catch (Exception e) {
				throw new Exception("List params[start_day/end_day/staff_id] invalid, error: " + e.toString());
			}
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			listData = home.getReportInvoiceDataDaily(date_company_received_from, date_company_received_to, staff_id);
			
			String staff_temp  = ""; 
			BigDecimal revenue_in_phase = new BigDecimal(0); 
			List<ReportInvoiceDaily> reports = new ArrayList<ReportInvoiceDaily>();
			for (InvoiceData data : listData) {
				String staff_name = data.getStaff_name();
				BigDecimal sum_total_price = data.getSum_total_price();
				
				if(staff_temp.length() > 0 && !staff_temp.equals(staff_name)){
					ReportInvoiceDaily reportSum = new ReportInvoiceDaily();
					reportSum.setStaff_name(staff_temp + " Total");
					reportSum.setTotal_moneys(revenue_in_phase.toString());
					reportSum.setCustomer1_codes("");
					reportSum.setCustomer1_names("");
					reports.add(reportSum);
					revenue_in_phase = new BigDecimal(0);
				}
				
				ReportInvoiceDaily report = new ReportInvoiceDaily();
				if(staff_temp.equals(data.getStaff_name())){
					report.setStaff_name("");
				}else{
					report.setStaff_name(data.getStaff_name());	
				}
				report.setCustomer1_codes(data.getCustomer_code());
				report.setCustomer1_names(data.getCustomer_name());
				report.setDate1_receipt_of_product(data.getDate1_receipt_of_product());
				report.setDate2_company_receipt_of_invoice(data.getDate2_company_receipt_of_invoice());
				report.setTotal_moneys(StringUtil.notNull(sum_total_price));
				reports.add(report);
				
				if(sum_total_price != null){
					revenue_in_phase = revenue_in_phase.add(sum_total_price);	
				}
				staff_temp = data.getStaff_name();
			}
			if(staff_temp.length() > 0){
				ReportInvoiceDaily reportSum = new ReportInvoiceDaily();
				reportSum.setStaff_name(staff_temp + " Total");
				reportSum.setTotal_moneys(revenue_in_phase.toString());
				reportSum.setCustomer1_codes("");
				reportSum.setCustomer1_names("");
				reports.add(reportSum);
			}
			System.out.println("Total data: " + reports.size());
			result = reports;
			listData.clear();
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}
	
	///////////////////////////////////////////////NEW REPORT/////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		try {
			//new InvoiceDataAction().loadListStaff();
			//InvoiceDataHome home = new InvoiceDataHome(new InvoiceDataAction().getSessionFactory());
			//System.out.println(home.getReportInvoiceDataByCus1(null, null).size());
			BigDecimal sum_total_money = new BigDecimal(0);
			System.out.println(sum_total_money.add(new BigDecimal("3434")).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<InvoiceData> getListData() {
		return listData;
	}

	public List<User> getListEmployee() {
		return listStaff;
	}

	public List<Customer> getListCustomer() {
		return listCustomer;
	}

	public void setListData(List<InvoiceData> listData) {
		this.listData = listData;
	}

	public void setListEmployee(List<User> listEmployee) {
		this.listStaff = listEmployee;
	}

	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}
}