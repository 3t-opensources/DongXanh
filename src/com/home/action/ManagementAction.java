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

import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.home.conts.MyConts;
import com.home.dao.CustomerHome;
import com.home.dao.InvoiceTypeHome;
import com.home.dao.ManagementHome;
import com.home.dao.ProductHome;
import com.home.dao.UserHome;
import com.home.entity.EntInvoiceData;
import com.home.model.Customer;
import com.home.model.InvoiceData;
import com.home.model.InvoiceType;
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
	private String result;
	private ResultMessage rsMess = new ResultMessage();
	private List<InvoiceType> listInvoiceType = new ArrayList<InvoiceType>();

	public List<InvoiceType> getListInvoiceType() {
		return listInvoiceType;
	}

	public void setListInvoiceType(List<InvoiceType> listInvoiceType) {
		this.listInvoiceType = listInvoiceType;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
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

	public String importDataCapture(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String file_name = request.getParameter("file_name");
			String base64 = request.getParameter("base64");
			String user_name = request.getParameter("user_name");
			BufferedImage buff = Base64Util.decodeToImage(base64);

			File dir = new File(SystemUtil.getUserDir() + "/DX_Images/"+DateUtils.getDateToString(new Date(), "dd/MM/yyyy"));
			if (!dir.exists()) {
				dir.mkdirs();
			}

			ManagementHome managementHome = new ManagementHome(getSessionFactory()); 
			File output = new File(dir.getAbsolutePath() + "/" + file_name);
			String hash_file = HashGeneratorUtils.generateMD5(output);
			if(notDuplicate(managementHome, hash_file)){
				ImageIO.write(buff, FilenameUtils.getExtension(file_name).toLowerCase().replace("jpg", "jpeg"), output);
				importImage2DB(managementHome, output, hash_file, user_name);
				rsMess.setStatus(0);
				rsMess.setMessage("Import done!");
				result = rsMess.toString();
			}else{
				System.out.println("File duplicate: " + output);
				rsMess.setStatus(1);
				rsMess.setMessage("File duplicate: " + output);
				result = rsMess.toString();
			}			
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatus(1);
			rsMess.setMessage(e.getMessage());
			result = rsMess.toString();
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

	private void importImage2DB(ManagementHome managementHome, File file, String hash_file, String user_name){
		try {
			Management management = new Management();
			management.setFile_path(file.getParent().replace("\\", "/"));
			management.setFile_name(file.getName());
			management.setCreated_time(new Date());
			management.setCreated_by(user_name);
			management.setHash_file(hash_file);
			management.setStep(1);
			management.setStatus(0);
			management.setDuplicate_status(0);
			managementHome.attachDirty(management);
			//System.out.println(management.getId());

			InvoiceData invoiceData = new InvoiceData();
			invoiceData.setManagement_id(management);
			managementHome.attachDirty(invoiceData);
			System.out.println("File " + file.getName() + " imported!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show list job have not capture
	 * @return
	 */
	public String getAllJob(){
		try {
			ManagementHome home = new ManagementHome(getSessionFactory());
			managements = home.getAllJobCapture();			
			result = new Gson().toJson(managements);
			System.out.println("Total job: " + managements.size());
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatus(1);
			rsMess.setMessage(e.getMessage());
			result = rsMess.toString();
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
				user_id = 0;
			}
			ManagementHome home = new ManagementHome(getSessionFactory());
			managements = home.getJobCapture(user_id, 3);
			result = new Gson().toJson(managements);
			System.out.println("Total job: " + managements.size());
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatus(1);
			rsMess.setMessage(e.getMessage());
			result = rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String saveJobCapture(){
		try {
			ManagementHome home = new ManagementHome(getSessionFactory());
			int management_id;
			EntInvoiceData data;
			
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			management_id = Integer.parseInt(request.getParameter("management_id"));
			data = EntInvoiceData.fromJson(StringUtil.notNull(request.getParameter("invoice_data")));

			Management management = new Management();
			management.setId(management_id);

			InvoiceData invoice_data = new InvoiceData();
			invoice_data.setManagement_id(management);

			InvoiceType invoice_type_id = new InvoiceType();
			invoice_type_id.setId(data.getInvoice_type_id());
			invoice_data.setInvoice_type_id(invoice_type_id);

			Customer customer_id = new Customer();
			customer_id.setId(data.getCustomer_id());
			invoice_data.setCustomer_id(customer_id);
			invoice_data.setCustomer_name(data.getCustomer_name()) ;
			invoice_data.setInvoice_name(data.getInvoice_name()) ;

			Customer customer_id_level1 = new Customer();
			customer_id_level1.setId(data.getCustomer_id_level1());
			invoice_data.setCustomer_id_level1(customer_id_level1) ;
			invoice_data.setCustomer_name_level1(data.getCustomer_name_level1());

			User user = new User();
			user.setId(data.getUser_id());
			invoice_data.setUser_id(user);
			invoice_data.setUser_name(data.getUser_name()) ;
			invoice_data.setDate_company_received(data.getDate_company_received());
			invoice_data.setDate_product_received(data.getDate_product_received()) ;
			invoice_data.setDate_sent_late(data.getDate_sent_late());
			invoice_data.setNotes(data.getNotes());

			invoice_data.setProduct_ids(data.getProduct_ids()) ;
			invoice_data.setProduct_names(data.getProduct_names());
			invoice_data.setTotal_boxs(data.getTotal_boxs());
			invoice_data.setQuantitys(data.getQuantitys());
			invoice_data.setTotal_prices(data.getTotal_prices());
			invoice_data.setSum_total_price(new BigDecimal(data.getSum_total_price()));

			home.saveJobCapture(management_id, invoice_data);

			System.out.println("Save job done!");
			rsMess.setStatus(0);
			rsMess.setMessage("Save job done!");
			result = rsMess.toString();
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatus(1);
			rsMess.setMessage(e.getMessage());
			result = rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String badJobCapture(){
		try {
			int management_id;
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			management_id = Integer.parseInt(request.getParameter("management_id"));
			ManagementHome home = new ManagementHome(getSessionFactory());
			home.setJobDuplicate(management_id);

			System.out.println("Bad job done!");
			rsMess.setStatus(0);
			rsMess.setMessage("Bad job done!");
			result = rsMess.toString();
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatus(1);
			rsMess.setMessage(e.getMessage());
			result = rsMess.toString();
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
			result = new Gson().toJson(listInvoiceType);
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatus(1);
			rsMess.setMessage(e.getMessage());
			result = rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String lookupCustomerAndStaff(){
		try {
			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());

			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String search_cus = StringUtil.notNull(request.getParameter("search_cus"));

			if(!redis.isConnected()){
				redis.connect(REDIS_SERVER, REDIS_PORT, REDIS_AUTH);
			}
			if(redis.isConnected()){
				String key = "lookupCustomerAndStaff"+ search_cus;
				String data_json = StringUtil.notNull(redis.get(key));
				if(data_json.isEmpty()){
					listCustomer = new Gson().fromJson(data_json, ArrayList.class);
				}else{
					listCustomer = cusHome.lookupCustomerAndStaff(search_cus, ""+MyConts.CUS_L1);
					redis.set(key, new Gson().toJson(listCustomer));
				}
			}else{
				listCustomer = cusHome.lookupCustomerAndStaff(search_cus, ""+MyConts.CUS_L1);
			}
			result = new Gson().toJson(listCustomer);
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatus(1);
			rsMess.setMessage(e.getMessage());
			result = rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String lookupCustomer(){
		try {
			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());

			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String search_cus = StringUtil.notNull(request.getParameter("search_cus"));

			if(!redis.isConnected()){
				redis.connect(REDIS_SERVER, REDIS_PORT, REDIS_AUTH);
			}
			if(redis.isConnected()){
				String key = "lookupCustomer"+ search_cus;
				String data_json = StringUtil.notNull(redis.get(key));
				if(data_json.isEmpty()){
					listCustomer = new Gson().fromJson(data_json, ArrayList.class);
				}else{
					listCustomer = cusHome.lookupCustomer(search_cus, ""+MyConts.CUS_L1);
					redis.set(key, new Gson().toJson(listCustomer));
				}
			}else{
				listCustomer = cusHome.lookupCustomer(search_cus, ""+MyConts.CUS_L1);
			}
			result = new Gson().toJson(listCustomer);
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatus(1);
			rsMess.setMessage(e.getMessage());
			result = rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String lookupStaff(){
		try {
			UserHome userHome = new UserHome(HibernateUtil.getSessionFactory());

			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String search_staff = StringUtil.notNull(request.getParameter("search_start"));

			if(!redis.isConnected()){
				redis.connect(REDIS_SERVER, REDIS_PORT, REDIS_AUTH);
			}
			if(redis.isConnected()){
				String key = "lookupCareStaff"+ search_staff;
				String data_json = StringUtil.notNull(redis.get(key));
				if(data_json.isEmpty()){
					listStaff = new Gson().fromJson(data_json, ArrayList.class);
				}else{
					listStaff =  userHome.lookupStaff(search_staff);
					redis.set(key, new Gson().toJson(listStaff));
				}
			}else{

				listStaff =  userHome.lookupStaff(search_staff);
			}
			result = new Gson().toJson(listStaff);
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatus(1);
			rsMess.setMessage(e.getMessage());
			result = rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}

	public String lookupProduct(){
		try {
			ProductHome proHome = new ProductHome(HibernateUtil.getSessionFactory());

			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String search_product = StringUtil.notNull(request.getParameter("search_product"));

			if(!redis.isConnected()){
				redis.connect(REDIS_SERVER, REDIS_PORT, REDIS_AUTH);
			}
			if(redis.isConnected()){
				String key = "lookupProduct"+ search_product;
				String data_json = StringUtil.notNull(redis.get(key));
				if(data_json.isEmpty()){
					listProduct = new Gson().fromJson(data_json, ArrayList.class);
				}else{
					listProduct = proHome.lookupProduct(search_product);
					redis.set(key, new Gson().toJson(listProduct));
				}
			}else{

				listProduct = proHome.lookupProduct(search_product);
			}
			result = new Gson().toJson(listProduct);
		} catch (Exception e) {
			e.printStackTrace();
			rsMess.setStatus(1);
			rsMess.setMessage(e.getMessage());
			result = rsMess.toString();
			//return ERROR;
		}
		return SUCCESS;
	}


}