package com.home.bll;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.home.action.InvoiceDataAction;
import com.home.dao.UserHome;
import com.home.model.User;
import com.home.util.EmailUtil;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.opensymphony.xwork2.Action;

public class Scheduler{
	private static final Log log = LogFactory.getLog(Scheduler.class);
	//create an object of SingleObject
	private static Scheduler instance = new Scheduler();
	private Timer timer = new Timer(true);
	//Get the only object available
	public static Scheduler getInstance(){
		return instance;
	}
	
	private Date startTime(){
		Calendar c2 = Calendar.getInstance();
		//c2.setTimeZone(TimeZone.getTimeZone("GMT+7"));
		c2.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));		
		int curHour = c2.get(Calendar.HOUR_OF_DAY);
		int curMinute = c2.get(Calendar.MINUTE);
		if(curHour > 7){
			c2.setTime(new Date(new Date().getTime() + 24*60*60*1000l));
		}else if(curHour == 7 && curMinute > 30){
			c2.setTime(new Date(new Date().getTime() + 24*60*60*1000l));
		}			
		c2.set(Calendar.HOUR_OF_DAY, 7);
		c2.set(Calendar.MINUTE, 30);
		c2.set(Calendar.SECOND, 0);
		System.out.println("Scheduler.startTime:" + c2.getTime());
		return c2.getTime();
	}
	
//	void test(){
//		System.out.println(startTime());
//		timer.schedule(new XXX(), startTime()); // period: 1 day
//		new Thread(){
//			public void run() {
//				try {
//					while(true){
//						sleep(1000);
//					}		
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			};
//			
//		}.start();
//	}
//	
//	class XXX extends TimerTask{
//		@Override
//		public void run() {
//			System.out.println(new Date() + ">>>ABC");
//			timer.schedule(new XXX(), startTime());
//		}
//	}
	
	public static void main(String[] args) {
		try {
			//Scheduler s  = new Scheduler();
			//s.startTime();
			System.out.println(new Scheduler().startTime());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void scheduleSendEmailDailyIndexData(final ServletContext servletContext){
		try {
			//System.out.println("Init scheduleSendEmailDailyIndexData");
			System.out.println(">>>scheduleSendEmailDailyIndexData at " + startTime());
			timer.schedule(new RemindTask(servletContext), startTime(), 24*60*60*1000l); // period: 1 day
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	class RemindTask extends TimerTask {
		private ServletContext servletContext;
		public RemindTask(ServletContext servletContext){
			this.servletContext = servletContext;
		}
		@Override
		public void run() {
			try {
				System.out.println(new Date() + ">>>scheduleSendEmailDailyIndexData...");
				//String toEmail = "tttoan@digi-texx.vn";
				String subject = "Báo cáo tiến độ nhập toa";
				String body = "Vui lòng xem file đính kèm.";
				InvoiceDataAction action = new InvoiceDataAction();
				if(Action.SUCCESS.equalsIgnoreCase(action.exportExcelIndexDaily(servletContext))){
					File fileAttachment = action.getReportXlsIndexDaily();
					UserHome userHome = new UserHome(HibernateUtil.getSessionFactory());
					List<User> leaders = userHome.getListUserLeader();
					if(leaders != null){
						int retry = 0;
						while(retry < 5){
							try {
								StringBuilder listEmail = new StringBuilder();
								for (User user : leaders) {
									if(StringUtil.notNull(user.getEmail()).length() > 0){
										listEmail.append(user.getEmail()).append(";");
									}
								}
								try {
									if(listEmail.length() > 0){
										EmailUtil.sendAttachmentEmail(listEmail.toString(), subject, body, fileAttachment);	
									}
								} catch (Exception e) {
									for (User user : leaders) {
										if(StringUtil.notNull(user.getEmail()).length() > 0){
											EmailUtil.sendAttachmentEmail(user.getEmail(), subject, body, fileAttachment);
										}
									}
								}
								retry = 5;
								break;
							} catch (Exception e) {
								e.printStackTrace();
								log.error(e);
								Thread.sleep(5*60000);
							} finally{
								retry++;
							}
						}
					}
				}	
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
			}
		}
	}
}
