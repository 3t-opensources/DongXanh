package com.home.action;

import java.sql.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.dao.InvoiceDataHome;
import com.home.dao.ManagementHome;
import com.home.model.InvoiceData;
import com.home.model.Management;
import com.home.util.DateUtils;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InvoiceDataAction extends ActionSupport implements ServletContextAware {
	private static final long serialVersionUID = 5109187855480607759L;
	//servletcontext for getting the context
	private ServletContext servletContext;
	
	private List<Management> managements;
	private List<InvoiceData> datas;

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void validate() {
		//check whether at least one file was selected for upload
	}

	public SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}
	
	public String getAllJob(){
		try {
			ManagementHome home = new ManagementHome(getSessionFactory());
			managements = home.getListManagements();
			System.out.println("Total job: " + managements.size());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String getAllData(){
		try {
			// Fetch Data from User Table
			int management_id;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				management_id = Integer.parseInt(request.getParameter("management_id"));
			} catch (Exception e) {
				management_id = 5;
			}
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			datas = home.getListInvoiceData(management_id);
			System.out.println("Total data: " + datas.size());
			//System.out.println(datas.get(0).getManagement_id().getFile_name());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
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
				customer_id =  Integer.parseInt(StringUtil.notNull(request.getParameter("customer_id")));
				sent_late =  Integer.parseInt(StringUtil.notNull(request.getParameter("sent_late")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			datas = home.getListInvoiceData(date_company_received_from, date_company_received_to, invoice_type, user_id, customer_id, sent_late);
			System.out.println("Total data: " + datas.size());
			//System.out.println(datas.get(0).getManagement_id().getFile_name());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public static void main(String[] args) {
		try {
			new InvoiceDataAction().getAllJob();
			new InvoiceDataAction().getAllData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}