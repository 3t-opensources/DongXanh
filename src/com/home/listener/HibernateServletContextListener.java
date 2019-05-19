package com.home.listener;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.home.bll.Scheduler;
import com.home.util.EmailUtil;

public class HibernateServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
//		SessionFactory sf = (SessionFactory) sce.getServletContext().getAttribute("SessionFactory");
//		sf.close();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		initMyConfig();
		Scheduler.getInstance().scheduleSendEmailDailyIndexData();
//		URL url = HibernateServletContextListener.class.getResource("/hibernate.cfg.xml");
//		Configuration config = new Configuration();
//		config.configure(url);
//		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//						.applySettings(config.getProperties()).build();
//		SessionFactory sf = config.buildSessionFactory(serviceRegistry);
//		sce.getServletContext().setAttribute("SessionFactory", sf);
	}
	
	private void initMyConfig(){
		try {
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
			Properties prop = new Properties();
			prop.load(inputStream);
			
			EmailUtil.MAIL_HOST = prop.getProperty("mail.smtp.host", "112.78.4.14");
			EmailUtil.MAIL_PORT = prop.getProperty("mail.smtp.port", "465");
			EmailUtil.MAIL_ACCOUNT = prop.getProperty("mail.username", "infor@dongxanhvn.com");
			EmailUtil.MAIL_PASSWORD = prop.getProperty("mail.password", "dongxanhMAIL!23");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
