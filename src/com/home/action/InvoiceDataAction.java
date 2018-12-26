package com.home.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.dao.CustomerHome;
import com.home.dao.InvoiceDataHome;
import com.home.dao.UserHome;
import com.home.model.Customer;
import com.home.model.InvoiceData;
import com.home.model.ResultMessage;
import com.home.model.User;
import com.home.util.DateUtils;
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
			rsMess.setMessage(e.toString());
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
			rsMess.setMessage(e.toString());
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
			rsMess.setMessage(e.toString());
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
				throw new Exception("List params[start_day/end_day/invoice_type/user_id/customer_id/sent_late] invalid, error: " + e.toString());
			}

			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			listData = home.getListInvoiceData(date_company_received_from, date_company_received_to, invoice_type, user_id, customer_id, sent_late);
			result = listData;//new Gson().toJson(listData);
			System.out.println("Total data: " + listData.size());
			//System.out.println(datas.get(0).getManagement_id().getFile_name());
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(e.toString());
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public static void main(String[] args) {
		try {
			new InvoiceDataAction().loadListStaff();
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