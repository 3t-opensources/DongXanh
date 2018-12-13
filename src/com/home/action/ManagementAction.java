package com.home.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.conts.MyConts;
import com.home.dao.CustomerHome;
import com.home.dao.ManagementHome;
import com.home.entity.EntInvoiceData;
import com.home.model.Customer;
import com.home.model.InvoiceData;
import com.home.model.InvoiceType;
import com.home.model.Management;
import com.home.model.User;
import com.home.util.Base64Util;
import com.home.util.DateUtils;
import com.home.util.HashGeneratorUtils;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.home.util.SystemUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ManagementAction extends ActionSupport implements ServletContextAware {
	private static final long serialVersionUID = 5109187855480607759L;
	//input type file name - files
	private File[] files;
	//all uploaded file names
	private String[] filesFileName;
	//all uploaded files content type
	private String[] filesContentType;
	//servletcontext for getting the context
	private ServletContext servletContext;

	private List<Management> managements;
	private List<Object[]> listCustomer;
	

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

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String[] getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType) {
		this.filesContentType = filesContentType;
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
		//check whether at least one file was selected for upload
		if (null == files) {
			addActionMessage("You must select at least one file!");
		}
	}

	public SessionFactory getSessionFactory() {
		return HibernateUtil.getSessionFactory();
	}
	
	@Override
	public String execute() throws Exception {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		//File dir = new File(servletContext.getRealPath(""));
		File dir = new File(SystemUtil.getUserDir() + "/DX_Images/"+DateUtils.getDateToString(new Date(), "dd/MM/yyyy"));
		//System.out.println("servletContext.getRealPath(\"\"): "	+ servletContext.getRealPath(""));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		ManagementHome managementHome = new ManagementHome(getSessionFactory()); 
		try {
			for (int i = 0; i < files.length; i++) {
				if(files[i].exists()){
					String hash_file = HashGeneratorUtils.generateMD5(files[i]);
					if(notDuplicate(managementHome, hash_file)){
						String targetPath = dir.getPath() + File.separator	+ filesFileName[i];
						System.out.println("targetPath: " + targetPath);
						File f = new File(targetPath);
						if(f.exists()){
							f = new File(targetPath.replaceAll("\\..{2,4}$", "") + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(targetPath));
						}
						fileInputStream = new FileInputStream(files[i]);
						fileOutputStream = new FileOutputStream(f);
						int c;
						while ((c = fileInputStream.read()) != -1) {
							fileOutputStream.write(c);
						}
						
						importImage2DB(managementHome, files[i], hash_file);
					}else{
						System.out.println("File duplicate: " + files[i]);
					}
				}else{
					System.out.println("File not found: " + files[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("Error occurred during uploading the file!");
			return INPUT;
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
		addActionMessage("File(s) successfully uploaded!");
		return SUCCESS;
	}
	
	public String importData(){
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String file_name = request.getParameter("file_name");
			String base64 = request.getParameter("base64");
			BufferedImage buff = Base64Util.decodeToImage(base64);
			
			File dir = new File(SystemUtil.getUserDir() + "/DX_Images/"+DateUtils.getDateToString(new Date(), "dd/MM/yyyy"));
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File output = new File(dir.getAbsolutePath() + "/" + file_name);
			ImageIO.write(buff, FilenameUtils.getExtension(file_name).toLowerCase().replace("jpg", "jpeg"), output);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public static void main(String[] args) {
		try {
			ManagementAction action = new ManagementAction();
			ManagementHome managementHome = new ManagementHome(HibernateUtil.getSessionFactory());
			
			File f = new File("C:/Users/DAT/Desktop/HinhToaChup/image.jpg");
			String hash_file = HashGeneratorUtils.generateMD5(f);
			System.out.println(action.notDuplicate(managementHome, hash_file));
			action.importImage2DB(managementHome, f, hash_file);
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
	
	private void importImage2DB(ManagementHome managementHome, File file, String hash_file){
		try {
			Management management = new Management();
			management.setFile_path(file.getParent().replace("\\", "/"));
			management.setFile_name(file.getName());
			management.setCreated_time(new Date());
			management.setCreated_by("admin");
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
			System.out.println("Total job: " + managements.size());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
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
			System.out.println("Total job: " + managements.size());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String saveJobCapture(){
		try {
			int management_id;
			EntInvoiceData data;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				management_id = Integer.parseInt(request.getParameter("management_id"));
				data = EntInvoiceData.fromJson(StringUtil.notNull(request.getParameter("invoice_data")));
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}
			ManagementHome home = new ManagementHome(getSessionFactory());
			
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
			
			System.out.println("saveJobCapture done!!!");
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String setJobDuplicate(){
		try {
			int management_id;
			try {
				HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
				management_id = Integer.parseInt(request.getParameter("management_id"));
			} catch (Exception e) {
				e.printStackTrace();
				return ERROR;
			}
			ManagementHome home = new ManagementHome(getSessionFactory());
			home.setJobDuplicate(management_id);
			
			System.out.println("setJobDuplicate done!!!");
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	////////////////////////////////////////LOOKUP DATA////////////////////////////////////////////////
	
	public String lookupCustomer(){
		try {

			HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get( ServletActionContext.HTTP_REQUEST);
			String search_cus = StringUtil.notNull(request.getParameter("search_cus"));

			CustomerHome cusHome = new CustomerHome(HibernateUtil.getSessionFactory());
			listCustomer = cusHome.lookupCustomer(search_cus, ""+MyConts.CUS_L1);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
}