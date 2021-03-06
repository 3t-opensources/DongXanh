package com.home.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.home.dao.CustomerHome;
import com.home.dao.InvoiceDataHome;
import com.home.dao.ProductHome;
import com.home.dao.UserHome;
import com.home.entities.ReportIndexDaily;
import com.home.entities.ReportInvoiceByCus1;
import com.home.entities.ReportInvoiceByCus2;
import com.home.entities.ReportInvoiceByStaff;
import com.home.entities.ReportInvoiceDaily;
import com.home.entities.ReportInvoiceDetailByCus2;
import com.home.entities.ReportProductDaily;
import com.home.model.Customer;
import com.home.model.InvoiceData;
import com.home.model.Product;
import com.home.model.ResultMessage;
import com.home.model.User;
import com.home.util.DateUtils;
import com.home.util.ExcelUtil;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.home.util.SystemUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InvoiceDataAction extends ActionSupport implements ServletContextAware {
	private static final long serialVersionUID = 5109187855480607759L;
	private final Log log = LogFactory.getLog(InvoiceDataAction.class);
	//servletcontext for getting the context
	private ServletContext servletContext;
	private List<InvoiceData> listData = new ArrayList<InvoiceData>();
	private List<User> listStaff = new ArrayList<User>();
	private List<Customer> listCustomer = new ArrayList<Customer>();
	private Object result;
	private ResultMessage rsMess = new ResultMessage();
	private InputStream fileInputStream;
	private Workbook workbook;
	private File reportXlsIndexDaily;
	
	public File getReportXlsIndexDaily() {
		return reportXlsIndexDaily;
	}
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
			System.out.println("listData export:" + listData.size());
			
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
						"Loại bảng kê","Mã khách hàng","Tên khách hàng","Khách hàng cấp 1","NVTT","Ngày nhận toa","Ngày nhận hàng","Ngày cấp1 giao toa","Số ngày gửi trể","Ghi chú","Mã sản phẩm","Tên sản phẩm","Số lượng","Số thùng","Đơn giá","Thành tiền");
				startIndexRow++;
				for (InvoiceData entry : listData) {
  	        		String c1 =  StringUtil.notNull(entry.getInvoice_type_name());
					String c2 =  StringUtil.notNull(entry.getCustomer_code());
					String c3 =  StringUtil.notNull(entry.getCustomer_name());
					String c4 =  StringUtil.notNull(entry.getCustomer_name_level1());
					String c5 =  StringUtil.notNull(entry.getStaff_name());
					String c6 =  StringUtil.notNull(entry.getDate2_company_receipt_of_invoice());
					String c7 =  StringUtil.notNull(entry.getDate1_receipt_of_product());
					String c71 =  StringUtil.notNull(entry.getDate3_cus1_delivery_invoice());
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
											new Object[]{c1, ""},
											new Object[]{c2, ""},
											new Object[]{c3, ""},
											new Object[]{c4, ""},
											new Object[]{c5, ""},
											new Object[]{c6, ""},
											new Object[]{c7, ""},
											new Object[]{c71, ""},
											new Object[]{c8, 1},
											new Object[]{c9, ""},
											new Object[]{arr1[i], ""},
											new Object[]{arr2[i], ""},
											new Object[]{arr3[i], 1},
											new Object[]{arr4[i], 1d},
											new Object[]{arr5[i], ""},
											new Object[]{arr6[i], ""}
//											c1,c2,c3,c4,c5,c6,c7,c8,c9,
//											arr1[i],
//											arr2[i],
//											arr3[i],
//											arr4[i],
//											arr5[i],
//											arr6[i]
									);
									startIndexRow++;
								}								
							}
						}
					}
					//System.out.println(new java.util.Date() + ">>>startIndexRow: " + startIndexRow);
				}
				xls.autoSizeColumn(sheet, 1,2,3,4,5,6,7,10,11,12,13,14,15);
				System.out.println("init xls done!");
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				workbook.write(baos);
				System.out.println("write xls done!");
				fileInputStream = new ByteArrayInputStream(baos.toByteArray());
				System.out.println("export xls done!");
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
	
	
	public String getReportIndexDaily(){
		try {
			List<ReportIndexDaily> reports = getDataIndexDaily(null, -1);
			System.out.println("Total data: " + reports.size());
			result = reports;			
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}
	
	private List<ReportIndexDaily> getDataIndexDaily(Date index_date, int present_user) throws Exception{
		List<ReportIndexDaily> reports = new ArrayList<ReportIndexDaily>();
		try {
			if(index_date == null || present_user < 0){
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				// Fetch Data from User Table
				if(index_date == null){
					try {
						String day = StringUtil.notNull(request.getParameter("index_date"));
						if(day.length() == 10){
							index_date = new Date(DateUtils.getDateFromString(day, "dd/MM/yyyy").getTime());
						}else{
							index_date = new Date(DateUtils.getDateFromString(DateUtils.getStringFromDate(new java.util.Date(), "dd/MM/yyyy"), "dd/MM/yyyy").getTime()-24*60*60*1000l);
						}				
					} catch (Exception e) {
						index_date = new Date(DateUtils.getDateFromString(DateUtils.getStringFromDate(new java.util.Date(), "dd/MM/yyyy"), "dd/MM/yyyy").getTime()-24*60*60*1000l);
					}
				}
				if(present_user < 0){
					try {
						present_user =  Integer.parseInt(StringUtil.notNull(request.getParameter("present_user")));
					} catch (Exception e) {
						present_user = 0;
					}
				}
			}
			
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			listData = home.getReportIndexDaily(index_date, present_user);
			
			int no = 1;
			for (InvoiceData data : listData) {
				ReportIndexDaily report = new ReportIndexDaily();
				report.setNo(no++);
				report.setCustomer_code(data.getCustomer_code());
				report.setCustomer_name(data.getCustomer_name());
				report.setCustomer1_code(data.getCustomer_code_level1());
				report.setCustomer1_name(data.getCustomer_name_level1());
				report.setStaff_name(data.getStaff_name());
				report.setDate1_receipt_of_product(data.getDate1_receipt_of_product());
				report.setProduct_ids(data.getProduct_ids());
				report.setProduct_names(data.getProduct_names());
				report.setTotal_boxs(data.getTotal_boxs());
				report.setTotal_quantities(data.getQuantitys());
				report.setTotal_prices(data.getTotal_prices());
				report.setSum_total_price(data.getSum_total_price());
				report.setNotes(data.getNotes());
				report.setDate_sent_late(data.getDate_sent_late());
				reports.add(report);
			}
			listData.clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return reports;
	}
		
	public String exportExcelIndexDaily(ServletContext servletContext) {
		try {
			String str_now = DateUtils.getStringFromDate(new java.util.Date(), "dd-MM-yyyy");
			String str_index_date = DateUtils.getStringFromDate(new java.util.Date(new java.util.Date().getTime() - 24*60*60*1000l), "dd-MM-yyyy");
			Date index_date = new Date(DateUtils.getDateFromString(DateUtils.getStringFromDate(new java.util.Date(), "dd/MM/yyyy"), "dd/MM/yyyy").getTime()-24*60*60*1000l);
			//Date index_date = new Date(DateUtils.getDateFromString("23/05/2019", "dd/MM/yyyy").getTime()-24*60*60*1000l);
			List<ReportIndexDaily> listData = getDataIndexDaily(index_date, 0);
			if(listData.size() > 0){
				ProductHome proHome = new ProductHome(getSessionFactory());
				HashMap<String, Product> hmProduct = proHome.getProduct2Export();
				String pathname;
				try {
					if(servletContext != null){
						pathname = servletContext.getRealPath("/WEB-INF/template/excel/report_index_data_daily.xlsx");
					}else{
						pathname = ServletActionContext.getServletContext().getRealPath("/WEB-INF/template/excel/report_index_data_daily.xlsx");
					}
					//pathname = ((ServletContext) ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/WEB-INF/template/excel/report_index_data_daily.xlsx");
				} catch (Exception e) {
					e.printStackTrace();
					pathname = "/media/administrator/data_ntxuan/TTTOAN/Jee Projects/DongXanh_Proj/DongXanh_V2/DongXanh/WebContent/WEB-INF/template/excel/report_index_data_daily.xlsx";
					if(!new File(pathname).exists()){
						pathname = "C:/xampp/tomcat/webapps/DongXanh/WEB-INF/template/excel/report_index_data_daily.xlsx";
					}
				}
				File theFile = new File(pathname);
				ExcelUtil xls = new ExcelUtil();

				try (FileInputStream fis = new FileInputStream(theFile)) {
					workbook = xls.getWorkbook(fis,
							FilenameUtils.getExtension(theFile.getAbsolutePath()));
					Sheet sheet = workbook.getSheetAt(0);
					int startIndexRow = 5;
					int startIndexCell = 0;
					//BigDecimal sum_total_money = new BigDecimal(0);
					BigDecimal sum_total_money222 = new BigDecimal(0);
					BigDecimal sum_total_money_by_cus1 = new BigDecimal(0);
					int idx_start_cus1 = Integer.parseInt("" + startIndexRow);
					int idx_end_cus1 = Integer.parseInt("" + startIndexRow);
					int no = 0;
					String customer_code_tmp = "";
					String customer_name_tmp = "";
					String customer_name1_tmp = "";
					for (int i = 0; i < listData.size(); i++) {
						boolean flag = true;
						String customer_code = listData.get(i).getCustomer_code();
						String customer_name = listData.get(i).getCustomer_name();
						String customer1_name = listData.get(i).getCustomer1_name();
						String notes = listData.get(i).getNotes();
						int date_sent_late = listData.get(i).getDate_sent_late();
						String[] arr1 =  StringUtil.notNull(listData.get(i).getProduct_ids()).split("`");
						String[] arr2 =  StringUtil.notNull(listData.get(i).getProduct_names()).split("`");
						String[] arr3 =  StringUtil.notNull(listData.get(i).getTotal_boxs()).split("`");
						String[] arr31 =  StringUtil.notNull(listData.get(i).getTotal_quantities()).split("`");
						String[] arr4 =  StringUtil.notNull(listData.get(i).getTotal_prices()).split("`");
						if(customer_code_tmp.isEmpty() || customer_name_tmp.isEmpty() || (!customer_code_tmp.equals(customer_code)) && !customer_name_tmp.equals(customer_name)){
							no++;
						}else{
							flag = false;
						}
						if(arr1.length > 0){
							int startRowIndx = Integer.parseInt("" + startIndexRow);
							for (int j = 0; j < arr1.length; j++) {								
								if(arr1[j].length() > 0){
									String product_code = arr1[j];
									String total_price = (arr4.length > j ? arr4[j]:"");
									if(hmProduct.containsKey(product_code)){
										try {
											long price = hmProduct.get(product_code).getUnitPrice().longValue();
											total_price = "" + (Integer.valueOf(arr31[j]) * price);
											//System.out.println(Integer.valueOf(arr31[j])  + " x " +  price + " = " + total_price);
										} catch (Exception e) {}
									}
									xls.addRowData(sheet, startIndexRow, startIndexCell, 
											flag?no:"", 
											flag?customer_code:"",
											flag?customer_name:"",
											customer1_name,
											listData.get(i).getStaff_name(),
											DateUtils.getStringFromDate(listData.get(i).getDate1_receipt_of_product(), "dd/MM/yyyy"),
											product_code,
											arr2.length > j ? arr2[j]:"",
											arr3.length > j ? arr3[j]:"",
											SystemUtil.format2MoneyNoVND(total_price),
											"",
											flag?notes:"",
											flag?date_sent_late:""
									);
									startIndexRow++;
									flag = false;
									//System.out.println(customer_code + " => " + arr4[j]);
									sum_total_money222 = sum_total_money222.add(new BigDecimal(total_price));
									//System.out.println(customer_name1_tmp + " vs " + customer1_name);
									if(customer_name1_tmp.length() > 0 && !customer_name1_tmp.equals(customer1_name)){
										//Insert & new
										xls.updateRowData(sheet, idx_start_cus1, 10, SystemUtil.format2MoneyNoVND(sum_total_money_by_cus1.toString()));
										System.out.println(idx_start_cus1  + " - " +  (idx_end_cus1-1));
										sheet.addMergedRegion(new CellRangeAddress(idx_start_cus1, idx_end_cus1-1, 10, 10));
										idx_start_cus1 = Integer.parseInt("" + (idx_end_cus1));
										idx_end_cus1++;
										sum_total_money_by_cus1 = new BigDecimal(0);
										sum_total_money_by_cus1 = sum_total_money_by_cus1.add(new BigDecimal(total_price));
										customer_name1_tmp = customer1_name;
									}else{
										idx_end_cus1++;
										sum_total_money_by_cus1 = sum_total_money_by_cus1.add(new BigDecimal(total_price));
									}
								}								
							}
							int endRowIndx = Integer.parseInt("" + (startIndexRow-1));
							//System.out.println(startRowIndx + "-" + endRowIndx);
							sheet.addMergedRegion(new CellRangeAddress(startRowIndx, endRowIndx, 0, 0));
							sheet.addMergedRegion(new CellRangeAddress(startRowIndx, endRowIndx, 1, 1));
							sheet.addMergedRegion(new CellRangeAddress(startRowIndx, endRowIndx, 2, 2));
							sheet.addMergedRegion(new CellRangeAddress(startRowIndx, endRowIndx, 11, 11));
							sheet.addMergedRegion(new CellRangeAddress(startRowIndx, endRowIndx, 12, 12));
						}
						//sum_total_money = sum_total_money.add(listData.get(i).getSum_total_price());
						customer_code_tmp = customer_code;
						customer_name_tmp = customer_name;
						customer_name1_tmp = customer1_name;
					}
					//Update tong doanh thu Cus1 cuoi cung
					xls.updateRowData(sheet, idx_start_cus1, 10, SystemUtil.format2MoneyNoVND(sum_total_money_by_cus1.toString()));
					sheet.addMergedRegion(new CellRangeAddress(idx_start_cus1, idx_end_cus1-1, 10, 10));
					
					xls.updateRowData(sheet, 2, 2, str_now);
					xls.updateRowData(sheet, 2, 6, str_index_date);
					xls.updateRowData(sheet, 2, 10, SystemUtil.format2MoneyNoVND(sum_total_money222.toString()));
					//System.out.println("========" + sum_total_money222);
					
					/**
					 * Report product daily
					 */
					genarateSheetDataProductDaily(xls, workbook, getDataProductDaily(index_date), str_now, str_index_date);
					
					/**
					 * write Excel for send mail
					 */
					reportXlsIndexDaily = new File(SystemUtil.getUserDir() + "/DX_Reports/IndexData_Daily"+str_index_date+".xlsx");
					if(!reportXlsIndexDaily.getParentFile().exists()){
						reportXlsIndexDaily.getParentFile().mkdirs();
					}
					try(FileOutputStream fileOut = new FileOutputStream(reportXlsIndexDaily)){
						workbook.write(fileOut);	
					}
					
					/**
					 * For export Excel
					 */
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					workbook.write(baos);
					fileInputStream = new ByteArrayInputStream(baos.toByteArray());
					workbook.close();
					System.out.println("Report Done!");
				}
			}else{
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	private void genarateSheetDataProductDaily(ExcelUtil xls, Workbook workbook, LinkedHashMap<String, LinkedHashMap<String, ReportProductDaily>> products, String str_now, String str_index_date){
		try {
			CellStyle cell_style1 = workbook.createCellStyle();            
            cell_style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cell_style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cell_style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cell_style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
            //cell_style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            cell_style1.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
    		cell_style1.setFillBackgroundColor(HSSFColor.PALE_BLUE.index);
    		cell_style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    		
    		CellStyle cell_style2 = workbook.createCellStyle();            
    		cell_style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
    		cell_style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    		cell_style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    		cell_style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            
            Sheet sheet = workbook.getSheetAt(1);
			int startIndexRow = 6;
			int startIndexCell = 0;
			for (String product_code : products.keySet()) {
				LinkedHashMap<String, ReportProductDaily> customers = products.get(product_code);
				List<ReportProductDaily> listCus = new ArrayList<ReportProductDaily>(customers.values());
				/**
				 * Sort data
				 */
				Collections.sort(listCus, new Comparator<ReportProductDaily>() {
					@Override
					public int compare(ReportProductDaily o1, ReportProductDaily o2) {
						try {
							return Float.compare(o1.getTotal_box_in_year(), o2.getTotal_box_in_year());
						} catch (Exception e) {
							return 0;
						}
					}
				});
				Collections.reverse(listCus);
				float total_box_in_year_by_product = 0;
				float total_box_in_day_by_product = 0;
				for (ReportProductDaily cus : listCus) {
					xls.addRowData(sheet, startIndexRow, startIndexCell, cell_style2,
							product_code,
							cus.getCustomer1_name(),
							SystemUtil.format2MoneyNoVND(cus.getTotal_box_in_year()),
							cus.getTotal_box_in_day() > 0? SystemUtil.format2MoneyNoVND(cus.getTotal_box_in_day()):""
					);
					startIndexRow ++;
					total_box_in_year_by_product += cus.getTotal_box_in_year();
					total_box_in_day_by_product += cus.getTotal_box_in_day();
				}
				xls.addRowData(sheet, startIndexRow, startIndexCell, cell_style1,
						product_code + " Tổng cộng:",
						"",
						SystemUtil.format2MoneyNoVND(total_box_in_year_by_product),
						SystemUtil.format2MoneyNoVND(total_box_in_day_by_product)
				);
				startIndexRow += 2;
			}
			xls.updateRowData(sheet, 2, 1, str_now);
			xls.updateRowData(sheet, 2, 3, str_index_date);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	///////////////////////////////////////REPORT BY PRODUCT DAILY////////////////////////////////////////////////////
	
	private LinkedHashMap<String, LinkedHashMap<String, ReportProductDaily>> getDataProductDaily(Date index_date) throws Exception{
		LinkedHashMap<String, LinkedHashMap<String, ReportProductDaily>> results = new LinkedHashMap<String, LinkedHashMap<String,ReportProductDaily>>();
		try {
			if(index_date == null){
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				// Fetch Data from User Table
				if(index_date == null){
					try {
						String day = StringUtil.notNull(request.getParameter("index_date"));
						if(day.length() == 10){
							index_date = new Date(DateUtils.getDateFromString(day, "dd/MM/yyyy").getTime());
						}else{
							index_date = new Date(DateUtils.getDateFromString(DateUtils.getStringFromDate(new java.util.Date(), "dd/MM/yyyy"), "dd/MM/yyyy").getTime()-24*60*60*1000l);
						}				
					} catch (Exception e) {
						index_date = new Date(DateUtils.getDateFromString(DateUtils.getStringFromDate(new java.util.Date(), "dd/MM/yyyy"), "dd/MM/yyyy").getTime()-24*60*60*1000l);
					}
				}
			}
			
			/**
			 * Get start day-end day, from 1/10 -> current day
			 */
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(index_date);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			if(month < 10){
				year = year -1;
			}
			java.sql.Date start_day = new Date(DateUtils.getDateFromString("01/10/"+(year), "dd/MM/yyyy").getTime());
			java.sql.Date end_day = new Date(index_date.getTime());				
			
			
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			listData = home.getReportProductDaily(start_day, end_day);
			
			List<ReportProductDaily> reports = new ArrayList<ReportProductDaily>();
			for (InvoiceData data : listData) {
				String customer1_name = data.getCustomer_name_level1();
				java.util.Date index_time = data.getIndex_time();
				String[] arrPid =  StringUtil.notNull(data.getProduct_ids()).split("`");
				String[] arrBox =  StringUtil.notNull(data.getTotal_boxs()).split("`");
				if(arrPid.length > 0 && arrBox.length > 0){
					for (int j = 0; j < arrPid.length; j++) {	
						String product_code = arrPid[j];
						float total_box = Float.valueOf(arrBox[j]);
						total_box = (float) Math.round(total_box * 100.0f) / 100.0f;
						if(product_code.length() > 0){
							if(product_code.length() > 5){
								product_code = product_code.substring(0, 5);
								product_code = product_code.replace("DO4S1", "DO48S").replace("DI8W1", "DI80W");
							}
							ReportProductDaily report = new ReportProductDaily();
							report.setCustomer1_name(customer1_name);
							report.setProduct_id(product_code);
							report.setIndex_date(index_time);
							report.setTotal_box(total_box);
							reports.add(report);
						}
						
					}
				}
			}
			listData.clear();
			
			/**
			 * Sort data
			 */
			Collections.sort(reports, new Comparator<ReportProductDaily>() {
				@Override
				public int compare(ReportProductDaily o1, ReportProductDaily o2) {
					try {
						int v1 = o1.getIndex_date().compareTo(o2.getIndex_date());
						if(v1 == 0){
							return o1.getProduct_id().compareTo(o2.getProduct_id());						
						}
						return v1;
					} catch (Exception e) {
						return 0;
					}
				}
			});
			Collections.reverse(reports);
			for (ReportProductDaily r : reports) {
				//System.out.println(r.getIndex_date() + "#" + r.getProduct_id() + "#" + r.getCustomer1_name() + "#" + r.getTotal_box());
				String product_code = r.getProduct_id();
				String customer1_name = r.getCustomer1_name();
				float box = r.getTotal_box();
				boolean isCurrentDay = DateUtils.formmatStringDate(end_day, "ddMMyyyy").equals(DateUtils.formmatStringDate(r.getIndex_date(), "ddMMyyyy"));
				
				if(results.containsKey(product_code)){
					if(results.get(product_code).containsKey(customer1_name)){
						results.get(product_code).get(customer1_name).addTotal_box_in_day(isCurrentDay?box:0);
						results.get(product_code).get(customer1_name).addTotal_box_in_year((box));
					}else{
						ReportProductDaily rp = new ReportProductDaily();
						rp.setCustomer1_name(customer1_name);
						rp.setProduct_id(product_code);
						rp.setTotal_box_in_day(isCurrentDay?box:0);
						rp.setTotal_box_in_year(box);						
						results.get(product_code).put(customer1_name, rp);
					}
				}else{
					ReportProductDaily rp = new ReportProductDaily();
					rp.setCustomer1_name(customer1_name);
					rp.setProduct_id(product_code);
					rp.setTotal_box_in_day(isCurrentDay?box:0);
					rp.setTotal_box_in_year(box);
					results.put(product_code, new LinkedHashMap<String, ReportProductDaily>());
					results.get(product_code).put(customer1_name, rp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return results;
	}
	
	///////////////////////////////////////////////NEW REPORT/////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		try {
			//new InvoiceDataAction().loadListStaff();
			//InvoiceDataHome home = new InvoiceDataHome(new InvoiceDataAction().getSessionFactory());
			//System.out.println(home.getReportInvoiceDataByCus1(null, null).size());
//			BigDecimal sum_total_money = new BigDecimal(0);
//			sum_total_money = sum_total_money.add(new BigDecimal("2500.001"));
//			System.out.println(sum_total_money);
//			System.out.println(SystemUtil.format2MoneyNoVND(sum_total_money.toString()));
			//System.out.println(new Date(DateUtils.getDateFromString(DateUtils.getStringFromDate(new java.util.Date(), "dd/MM/yyyy"), "dd/MM/yyyy").getTime()-24*60*60*1000));
			new InvoiceDataAction().exportExcelIndexDaily(null);
			//new InvoiceDataAction().updateLaiGiaAndThanhTien();
			//System.out.println((167800 * 100));
			//System.out.println((167800l * 100));
			//new InvoiceDataAction().getDataProductDaily(new Date(new java.util.Date().getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateLaiGiaAndThanhTien(){
		try {
			ProductHome proHome = new ProductHome(getSessionFactory());
			HashMap<String, Product> hmProduct = proHome.getProduct2Export();
			
			InvoiceDataHome invoiceHome = new InvoiceDataHome(getSessionFactory());
			List<InvoiceData> listData = invoiceHome.getReportIndexDaily(null, 0);
			if(listData.size() > 0){
				List<InvoiceData> results = new ArrayList<InvoiceData>();
				for (int i = 0; i < listData.size(); i++) {
					int id = listData.get(i).getId();
					String customer_code = listData.get(i).getCustomer_code();
					System.out.println("+++> id["+id+"]["+customer_code+"]["+listData.get(i).getQuantitys()+"]["+listData.get(i).getUnit_prices()+"]["+listData.get(i).getTotal_prices()+"]");
					String[] arr1 =  StringUtil.notNull(listData.get(i).getProduct_ids()).split("`");
					String[] arr2 =  StringUtil.notNull(listData.get(i).getUnit_prices()).split("`");
					String[] arr31 =  StringUtil.notNull(listData.get(i).getQuantitys()).split("`");
					String[] arr4 =  StringUtil.notNull(listData.get(i).getTotal_prices()).split("`");
					
					String unit_prices = "";
					String total_prices = "";
					BigDecimal sum_of_total_prices = new BigDecimal("0");
					if(arr1.length > 0){
						for (int j = 0; j < arr1.length; j++) {	
							String product_code = arr1[j];
							String unit_price = (arr2.length > j ? arr2[j]:"");
							String total_price = (arr4.length > j ? arr4[j]:"");
							//System.out.println("product_code=["+product_code+"]");
							if(hmProduct.containsKey(product_code)){
								try {
									long price = hmProduct.get(product_code).getUnitPrice().longValue();							
									total_price = "" + (price * Long.valueOf(arr31[j]));
									//System.out.println("============> " + price + " >>> " + total_price);
									sum_of_total_prices = sum_of_total_prices.add(new BigDecimal(total_price));
									unit_price = "" + price;
								} catch (Exception e) {}
							}
							//System.out.println("product_code["+product_code+"]unit_price["+unit_price+"]total_price["+total_price+"]");
							unit_prices += unit_price + "`";
							total_prices += total_price + "`";
						}
						
						//System.out.println("===> id["+id+"]["+customer_code+"]["+listData.get(i).getQuantitys()+"]["+unit_prices+"]["+total_prices+"]["+sum_of_total_prices+"]");
						InvoiceData e = new InvoiceData();
						e.setId(id);
						e.setUnit_prices(unit_prices);
						e.setTotal_prices(total_prices);
						e.setSum_total_price(sum_of_total_prices);
						results.add(e);
					}
				}
				invoiceHome.updateUnitPriceAndTotalMoneys(results);
			}
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