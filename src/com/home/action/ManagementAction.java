package com.home.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.home.dao.ManagementHome;
import com.home.model.InvoiceData;
import com.home.model.Management;
import com.home.util.DateUtils;
import com.home.util.HashGeneratorUtils;
import com.home.util.HibernateUtil;
import com.home.util.SystemUtil;
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
}