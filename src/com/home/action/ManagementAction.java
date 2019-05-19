package com.home.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.home.conts.MyConts;
import com.home.dao.CustomerHome;
import com.home.dao.InvoiceDataHome;
import com.home.dao.InvoiceTypeHome;
import com.home.dao.ManagementHome;
import com.home.dao.ProductHome;
import com.home.dao.UserHome;
import com.home.entity.EntInvoiceData;
import com.home.model.Customer;
import com.home.model.InvoiceData;
import com.home.model.InvoiceType;
import com.home.model.JobImport;
import com.home.model.Management;
import com.home.model.ResultMessage;
import com.home.model.User;
import com.home.util.Base64Util;
import com.home.util.DateUtils;
import com.home.util.HashGeneratorUtils;
import com.home.util.HibernateUtil;
import com.home.util.RedisUtil;
import com.home.util.StringUtil;
import com.home.util.SystemUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ManagementAction extends ActionSupport implements ServletContextAware {
	//servletcontext for getting the context
	private ServletContext servletContext;
	private static RedisUtil redis = new RedisUtil();

	private List<Management> managements = new ArrayList<Management>();
	private List<Object[]> listCustomer = new ArrayList<Object[]>();
	private List<Object[]> listStaff = new ArrayList<Object[]>();
	private List<Object[]> listProduct = new ArrayList<Object[]>();
	private Object result;
	private ResultMessage rsMess = new ResultMessage();
	private List<InvoiceType> listInvoiceType = new ArrayList<InvoiceType>();
	private List<JobImport> jobs = new ArrayList<JobImport>();;
	private EntInvoiceData invoice_data = new EntInvoiceData();
	

	public EntInvoiceData getInvoice_data() {
		return invoice_data;
	}

	public void setInvoice_data(EntInvoiceData invoice_data) {
		this.invoice_data = invoice_data;
	}

	public List<JobImport> getJobs() {
		return jobs;
	}

	public void setJobs(List<JobImport> jobs) {
		this.jobs = jobs;
	}

	public List<InvoiceType> getListInvoiceType() {
		return listInvoiceType;
	}

	public void setListInvoiceType(List<InvoiceType> listInvoiceType) {
		this.listInvoiceType = listInvoiceType;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public List<Object[]> getListProduct() {
		return listProduct;
	}

	public void setListProduct(List<Object[]> listProduct) {
		this.listProduct = listProduct;
	}

	public List<Object[]> getListStaff() {
		return listStaff;
	}

	public void setListStaff(List<Object[]> listStaff) {
		this.listStaff = listStaff;
	}

	public List<Object[]> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<Object[]> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public List<Management> getManagements() {
		return managements;
	}

	public void setManagements(List<Management> managements) {
		this.managements = managements;
	}


	public SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public String importImagesCapture1(){
		try {
			StringBuilder strMess = new StringBuilder();
			int rsStatus = 0;
			for (JobImport job : jobs) {
				String imageString = job.getBase64();
				if(imageString.split(",").length>1){
					imageString = imageString.split(",")[1];
				}
				BufferedImage buff = Base64Util.decodeToImage(imageString);

				File dir = new File(SystemUtil.getUserDir() + "/DX_Images/"+DateUtils.getDateToString(new Date(), "ddMMyyyy") + "/" + new Date().getTime());
				if (!dir.exists()) {
					dir.mkdirs();
				}
				//System.out.println("===============156:"+dir);

				ManagementHome managementHome = new ManagementHome(getSessionFactory()); 
				File output = new File(dir.getAbsolutePath() + "/" + job.getFile_name());
				if(!output.getParentFile().exists()){
					output.getParentFile().mkdirs();
				}
				ImageIO.write(buff, FilenameUtils.getExtension(job.getFile_name()).toLowerCase().replace("jpg", "jpeg"), output);
				String hash_file = HashGeneratorUtils.generateMD5(output);
				if(notDuplicate(managementHome, hash_file)){
					importImage2DB(managementHome, output, hash_file, job.getUser_name(), job.getLast_modified());
					strMess.append("Import successed["+output.getName()+"];");
				}else{
					rsStatus = 1;
					strMess.append("File duplicate["+output.getName()+"];");
					output.delete();
				}	
			}
			rsMess.setStatusError(rsStatus);
			rsMess.setMessage(strMess.toString());
			result = rsMess;//rsMess.toString();
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String importImagesCapture2(){
		try {
			ManagementHome managementHome = new ManagementHome(getSessionFactory()); 
			String user_name;
			MultiPartRequestWrapper multiWrapper = (MultiPartRequestWrapper) ServletActionContext.getRequest();
			String[] fileName = multiWrapper.getFileNames("file");
//			for(String s : fileName) {
//				System.out.println(s);
//			}
			user_name = multiWrapper.getParameter("user_name");
			if(user_name == null){
				throw new Exception("user_name is null");
			}

			File dir = new File(SystemUtil.getUserDir() + "/DX_Images/"+DateUtils.getDateToString(new Date(), "ddMMyyyy") + "/" + new Date().getTime());
			if (!dir.exists()) {
				dir.mkdirs();
			}

			// Get a Files[] object for the uploaded File
			File[] files = multiWrapper.getFiles("file");
			StringBuilder strMess = new StringBuilder();
			int rsStatus = 0;
			for(int i = 0; i < files.length; i++) {
				File output = new File(dir.getAbsolutePath() + "/" + fileName[i]);
				FileUtils.copyFile(files[i], output);
				String hash_file = HashGeneratorUtils.generateMD5(output);
				if(notDuplicate(managementHome, hash_file)){
					importImage2DB(managementHome, output, hash_file, user_name, null);
					strMess.append("Import successed["+output.getName()+"];");
				}else{
					rsStatus = 1;
					strMess.append("File duplicate["+output.getName()+"];");
					output.delete();
				}	
			}
			rsMess.setStatusError(rsStatus);
			rsMess.setMessage(strMess.toString());
			result = rsMess;//rsMess.toString();
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}


	public static void main(String[] args) {
		try {
			//			ManagementAction action = new ManagementAction();
			//			ManagementHome managementHome = new ManagementHome(HibernateUtil.getSessionFactory());
			//			
			//			File f = new File("C:/Users/DAT/Desktop/HinhToaChup/image.jpg");
			//			String hash_file = HashGeneratorUtils.generateMD5(f);
			//			System.out.println(action.notDuplicate(managementHome, hash_file));
			//			action.importImage2DB(managementHome, f, hash_file);
			Object[] arr = new Object[]{1, "tran thien toan", true, new Date()};
			System.out.println(new Gson().toJson(arr));
			String json = "[\"1\",\"tran thien toan\",true,\"Dec 14, 2018 8:33:53 PM\"]";
			Object[] arr2 = new Gson().fromJson(json, new TypeToken<Object[]>(){}.getType());
			System.out.println(arr2[0]);

			List<Object[]> list = new ArrayList<Object[]>();
			list.add(arr);
			list.add(arr);
			System.out.println(new Gson().toJson(list));

			json = "[[1,\"tran thien toan\",true,\"Dec 14, 2018 8:38:43 PM\"],[1,\"tran thien toan\",true,\"Dec 14, 2018 8:38:43 PM\"]]";
			Object[][] list2 = new Gson().fromJson(json, Object[][].class);
			System.out.println(list2[0][0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean notDuplicate(ManagementHome managementHome, String hash_file){
		try {
			return !managementHome.isImageDuplicate(hash_file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void importImage2DB(ManagementHome managementHome, File file, String hash_file, String user_name, String last_modified) throws Exception{
		try {
			Management management = new Management();
			management.setFile_path(file.getParent().replace("\\", "/"));
			management.setFile_name(file.getName());
			management.setCreated_time(new java.sql.Timestamp(new Date().getTime()));
			management.setCreated_by(user_name);
			management.setHash_file(hash_file);
			management.setStep(1);
			management.setStatus(0);
			management.setPresent_user(0);
			management.setCapture_status(0);
			management.setDuplicate_status(0);
			management.setIndex_time(new java.sql.Timestamp(new Date().getTime()));
			managementHome.attachDirty(management);
			//System.out.println(management.getId());

			InvoiceData invoiceData = new InvoiceData();
			invoiceData.setManagement_id(management);
			try {
				invoiceData.setDate1_receipt_of_product(new java.sql.Timestamp(new Date().getTime()));
				invoiceData.setDate2_company_receipt_of_invoice(new Date(Long.parseLong(last_modified)));
			} catch (Exception e) {}
			managementHome.attachDirty(invoiceData);
			System.out.println("File " + file.getName() + " imported!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Show list job have not capture
	 * @return
	 */
	public String getPendingJobs(){
		try {
			ManagementHome home = new ManagementHome(getSessionFactory());
			managements = home.getAllJobCapture();			
			result = managements;//new Gson().toJson(managements);
			System.out.println("Total job: " + managements.size());
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	/////////////////////////////////////////CAPTURE DATA//////////////////////////////////////////////////

	public String getJobCapture(){
		try {
			int user_id;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				user_id = Integer.parseInt(request.getParameter("user_id"));
			} catch (Exception e) {
				throw new Exception("Param: user_id invalid, " + e.toString());
			}
			ManagementHome home = new ManagementHome(getSessionFactory());
			managements = home.getJobCapture(user_id, 3);
			result = managements;//new Gson().toJson(managements);
			System.out.println("Total job: " + managements.size());
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}
	
	public String returnJobsCature(){
		try {
			int user_id;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				user_id = Integer.parseInt(request.getParameter("user_id"));
			} catch (Exception e) {
				throw new Exception("Param: user_id invalid, " + e.toString());
			}
			ManagementHome home = new ManagementHome(getSessionFactory());
			int rs = home.returnJobsCapture(user_id);
			result = new String[]{"Reset", ""+rs};
			System.out.println("Reset "+rs+" jobs");
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String saveJobCapture(){
		try {
			CustomerHome custHome = new CustomerHome(getSessionFactory());
			ManagementHome managHome = new ManagementHome(getSessionFactory());
			EntInvoiceData data = this.invoice_data;
			int management_id = Integer.parseInt(data.getManagement_id());

//			try {
//				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
//				management_id = Integer.parseInt(request.getParameter("management_id"));
//				data = EntInvoiceData.fromJson(StringUtil.notNull(request.getParameter("invoice_data")));
//			} catch (Exception e) {
//				throw new Exception("List params[management_id/invoice_data] invalid, error: " + e.toString());
//			}

			Management management = new Management();
			management.setId(management_id);

			InvoiceData invoice_data = new InvoiceData();
			invoice_data.setId(Integer.parseInt(data.getId()));
			invoice_data.setManagement_id(management);

			InvoiceType invoice_type_id = new InvoiceType();
			invoice_type_id.setId(Integer.parseInt(data.getInvoice_type_id()));
			invoice_data.setInvoice_type_id(invoice_type_id);
			invoice_data.setInvoice_type_name(data.getInvoice_type());

			try {
				Customer customer_id = new Customer();
				customer_id.setId(Integer.parseInt(data.getCustomer_id()));
				invoice_data.setCustomer_id(customer_id);
				if(customer_id.getId() == 0){
					//invoice_data.setCustomer_id(null);
					System.out.println(0/0);
				}
			} catch (Exception e) {
				if(StringUtil.notNull(data.getCustomer_code()).length() > 0){
					Customer customer_id = custHome.findCustomerByCode(data.getCustomer_code());
					invoice_data.setCustomer_id(customer_id);
				}else{
					invoice_data.setCustomer_id(null);	
				}
			}
			invoice_data.setCustomer_code(data.getCustomer_code()) ;
			invoice_data.setCustomer_name(data.getCustomer_name()) ;

			try {
				Customer customer_id_level1 = new Customer();
				customer_id_level1.setId(Integer.parseInt(data.getCustomer_id_level1()));
				invoice_data.setCustomer_id_level1(customer_id_level1) ;
				if(customer_id_level1.getId() == 0){
					//invoice_data.setCustomer_id_level1(null);
					System.out.println(0/0);
				}
			} catch (Exception e) {
				if(StringUtil.notNull(data.getCustomer_code_level1()).length() > 0){
					Customer customer_id_level1 = custHome.findCustomerByCode(data.getCustomer_code_level1());
					invoice_data.setCustomer_id_level1(customer_id_level1);
				}else{
					invoice_data.setCustomer_id_level1(null) ;	
				}
			}
			invoice_data.setCustomer_code_level1(data.getCustomer_code_level1());
			invoice_data.setCustomer_name_level1(data.getCustomer_name_level1());

			try {
				User user = new User();
				user.setId(Integer.parseInt(data.getStaff_id()));
				invoice_data.setStaff_id(user);
				invoice_data.setStaff_name(data.getStaff_name()) ;
				
				if(user.getId() == 0){
					System.out.println(0/0);
				}
			} catch (Exception e) {
				User user = new User();
				user.setId(-1);
				invoice_data.setStaff_id(user);
				invoice_data.setStaff_name("Unknown") ;
			}
			
			invoice_data.setDate1_receipt_of_product(data.getDate1_receipt_of_product());
			invoice_data.setDate2_company_receipt_of_invoice(data.getDate2_company_receipt_of_invoice());
			invoice_data.setDate3_cus1_delivery_invoice(data.getDate3_cus1_delivery_invoice());
			try {
				invoice_data.setDate_sent_late(Integer.parseInt(data.getDate_sent_late()));
			} catch (Exception e) {
				invoice_data.setDate_sent_late(0);
			}
			invoice_data.setNotes(data.getNotes());

			invoice_data.setProduct_ids(data.getProduct_ids()) ;
			invoice_data.setProduct_names(data.getProduct_names());
			invoice_data.setTotal_boxs(data.getTotal_boxs());
			invoice_data.setQuantitys(data.getQuantitys());
			invoice_data.setUnit_prices(data.getUnit_prices());
			invoice_data.setTotal_prices(data.getTotal_prices());
			invoice_data.setSum_total_price(new BigDecimal(data.getSum_total_price()));
			//System.out.println(data.getProduct_names());
			//System.out.println(new Gson().toJson(invoice_data));

			managHome.saveJobCapture(management_id, invoice_data);

			System.out.println("Save job["+management_id+"] done!");
			rsMess.setStatusError(0);
			rsMess.setMessage("Save job["+management_id+"] done!");
			result = rsMess;//rsMess.toString();
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String badJobCapture(){
		try {
			int management_id;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				management_id = Integer.parseInt(request.getParameter("management_id"));
			} catch (Exception e) {
				throw new Exception("Param: management_id invalid, " + e.toString());
			}
			ManagementHome home = new ManagementHome(getSessionFactory());
			home.setJobDuplicate(management_id);

			System.out.println("Bad job done!");
			rsMess.setStatusError(0);
			rsMess.setMessage("Bad job done!");
			result = rsMess;//rsMess.toString();
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	////////////////////////////////////////LOOKUP DATA////////////////////////////////////////////////
	private String REDIS_SERVER = "localhost";
	private String REDIS_AUTH = "";
	private int REDIS_PORT = 7379;

	public String lookupInvoiceType(){
		try {
			InvoiceTypeHome home = new InvoiceTypeHome(HibernateUtil.getSessionFactory());
			listInvoiceType = home.getListInvoiceType2();
			result = listInvoiceType;//new Gson().toJson(listInvoiceType);
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String lookupCustomerAndStaff(){
		try {
			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());

			String search_cus;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				search_cus = StringUtil.notNull(request.getParameter("search_cus"));
			} catch (Exception e) {
				throw new Exception("Param: search_cus invalid, " + e.toString());
			}

			if(!redis.isConnected()){
				redis.connect(REDIS_SERVER, REDIS_PORT, REDIS_AUTH);
			}
			//System.out.println("redis.isConnected(): " + redis.isConnected());
			if(redis.isConnected()){
				String key = "lookupCustomerAndStaff"+ search_cus;
				String data_json = StringUtil.notNull(redis.get(key));
				System.out.println("data_json: " + data_json);
				if(data_json.length() > 2){
					//System.out.println("XXX");
					listCustomer = new Gson().fromJson(data_json, ArrayList.class);
				}else{
					//System.out.println("YYY");
					listCustomer = cusHome.lookupCustomerAndStaff(search_cus, ""+MyConts.CUS_L1);
					redis.set(key, new Gson().toJson(listCustomer));
				}
			}else{
				listCustomer = cusHome.lookupCustomerAndStaff(search_cus, ""+MyConts.CUS_L1);
			}
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

	public String lookupCustomer(){
		try {
			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());

			String search_cus;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				search_cus = StringUtil.notNull(request.getParameter("search_cus"));
			} catch (Exception e) {
				throw new Exception("Param: search_cus invalid, " + e.toString());
			}

			if(!redis.isConnected()){
				redis.connect(REDIS_SERVER, REDIS_PORT, REDIS_AUTH);
			}
			if(redis.isConnected()){
				String key = "lookupCustomer"+ search_cus;
				String data_json = StringUtil.notNull(redis.get(key));
				System.out.println("data_json: " + data_json);
				if(data_json.length() > 2){
					listCustomer = new Gson().fromJson(data_json, ArrayList.class);
				}else{
					listCustomer = cusHome.lookupCustomer(search_cus, ""+MyConts.CUS_L1);
					redis.set(key, new Gson().toJson(listCustomer));
				}
			}else{
				listCustomer = cusHome.lookupCustomer(search_cus, ""+MyConts.CUS_L1);
			}
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

	public String lookupStaff(){
		try {
			UserHome userHome = new UserHome(HibernateUtil.getSessionFactory());

			String search_staff;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				search_staff = StringUtil.notNull(request.getParameter("search_start"));
			} catch (Exception e) {
				throw new Exception("Param: search_staff invalid, " + e.toString());
			}

			if(!redis.isConnected()){
				redis.connect(REDIS_SERVER, REDIS_PORT, REDIS_AUTH);
			}
			if(redis.isConnected()){
				String key = "lookupCareStaff"+ search_staff;
				String data_json = StringUtil.notNull(redis.get(key));
				System.out.println("data_json: " + data_json);
				if(data_json.length() > 2){
					listStaff = new Gson().fromJson(data_json, ArrayList.class);
				}else{
					listStaff =  userHome.lookupStaff(search_staff);
					redis.set(key, new Gson().toJson(listStaff));
				}
			}else{

				listStaff =  userHome.lookupStaff(search_staff);
			}
			result = listStaff;//new Gson().toJson(listStaff);
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String lookupProduct(){
		try {
			ProductHome proHome = new ProductHome(HibernateUtil.getSessionFactory());

			String search_product;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				search_product = StringUtil.notNull(request.getParameter("search_product"));
			} catch (Exception e) {
				throw new Exception("Param: search_product invalid, " + e.toString());
			}

			if(!redis.isConnected()){
				redis.connect(REDIS_SERVER, REDIS_PORT, REDIS_AUTH);
			}
			if(redis.isConnected()){
				String key = "lookupProduct"+ search_product;
				String data_json = StringUtil.notNull(redis.get(key));
				System.out.println("data_json: " + data_json);
				if(data_json.length() > 2){
					listProduct = new Gson().fromJson(data_json, ArrayList.class);
				}else{
					listProduct = proHome.lookupProduct(search_product);
					redis.set(key, new Gson().toJson(listProduct));
				}
			}else{

				listProduct = proHome.lookupProduct(search_product);
			}
			result = listProduct;//new Gson().toJson(listProduct);
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	
	////////////////////////////////////////
	public String getJobCaptureRework(){
		try {
			InvoiceDataHome home = new InvoiceDataHome(getSessionFactory());
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String id = StringUtil.notNull(request.getParameter("id"));
			if(id.isEmpty()){
				throw new Exception("Param: id must be not empty!");
			}
			result = home.getInvoiceData(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}
	
	public String deleteListJobCapture(){
		try {
			ManagementHome home = new ManagementHome(getSessionFactory());
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String ids = StringUtil.notNull(request.getParameter("ids"));
			if(ids.isEmpty()){
				throw new Exception("Param: ids must be not empty!");
			}
			result = home.deleteJobCapture(ids);
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}
	
	public String getDuplicateInvoiceRecord(){
		try {
			ManagementHome home = new ManagementHome(getSessionFactory());
			String customer_id_level1;
			java.sql.Date day;
			String product_id;
			String quantity;
			int management_id = -1;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				customer_id_level1 = "" + Integer.parseInt(StringUtil.notNull(request.getParameter("customer_id_level1")));
				product_id = StringUtil.notNull(request.getParameter("product_id"));
				quantity = "" + Integer.parseInt(StringUtil.notNull(request.getParameter("quantity")));
				String str_day = StringUtil.notNull(request.getParameter("day"));
				day = new java.sql.Date(DateUtils.getDateFromString(str_day, "dd/MM/yyyy").getTime());
				//System.out.println(day+"============"+customer_id_level1+"==========="+product_id);
				management_id = Integer.parseInt(StringUtil.notNull(request.getParameter("management_id")));
			} catch (Exception e) {
				throw new Exception("List params[customer_id_level1/day/product_id/quantity/management_id] invalid, error: " + e.toString());
			}
			
			boolean exist = home.checkInvoiceRecordDuplicate(customer_id_level1, day, product_id, quantity, management_id);
			if(exist){
				result = new String[]{"duplicated"};
			}else{
				result = new String[]{"OK"};
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatusError(1);
			rsMess.setMessage(StringUtil.getError(e));
			result = rsMess;//rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}
	
	

}