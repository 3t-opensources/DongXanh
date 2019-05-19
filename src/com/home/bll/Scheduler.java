package com.home.bll;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.home.action.InvoiceDataAction;
import com.home.dao.UserHome;
import com.home.model.User;
import com.home.util.EmailUtil;
import com.home.util.HibernateUtil;
import com.home.util.StringUtil;
import com.opensymphony.xwork2.Action;

public class Scheduler{
	
	//create an object of SingleObject
	private static Scheduler instance = new Scheduler();

	//Get the only object available
	public static Scheduler getInstance(){
		return instance;
	}
	
	public void scheduleSendEmailDailyIndexData(){
		try {
			//System.out.println("Init scheduleSendEmailDailyIndexData");
			Calendar c2 = Calendar.getInstance();
			c2.set(Calendar.HOUR_OF_DAY, 7);
			c2.set(Calendar.MINUTE, 30);
			//System.out.println("schedule_restart2 = " + c2.getTime());

			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					try {
						System.out.println(new Date() + ">>>scheduleSendEmailDailyIndexData...");
						//String toEmail = "tttoan@digi-texx.vn";
						String subject = "Báo cáo tiến độ nhập toa";
						String body = "Vui lòng xem file đính kèm.";
						InvoiceDataAction action = new InvoiceDataAction();
						if(Action.SUCCESS.equalsIgnoreCase(action.exportExcelIndexDaily())){
							File fileAttachment = action.getReportXlsIndexDaily();
							UserHome userHome = new UserHome(HibernateUtil.getSessionFactory());
							List<User> leaders = userHome.getListUserLeader();
							if(leaders != null){
								for (User user : leaders) {
									if(StringUtil.notNull(user.getEmail()).length() > 0){
										try {
											EmailUtil.sendAttachmentEmail(user.getEmail(), subject, body, fileAttachment);	
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								}
							}
						}	
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, 
			c2.getTime()
					); // period: 1 day
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
