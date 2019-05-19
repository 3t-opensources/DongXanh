package com.home.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtil {

	public static String MAIL_ACCOUNT = "infor@dongxanhvn.com";
	public static String MAIL_PASSWORD = "dongxanhMAIL!23";
	public static String MAIL_HOST = "112.78.4.14";
	public static String MAIL_PORT = "465";

	/**
	   Outgoing Mail (SMTP) Server
	   requires TLS or SSL: smtp.gmail.com (use authentication)
	   Use Authentication: Yes
	   Port for SSL: 465
	 */
	public static void main(String[] args) {
		try {
			final String toEmail = "tttoan@digi-texx.vn"; // can be any email id 		

			EmailUtil.sendEmail(toEmail,"SSLEmail Testing Subject", "SSLEmail Testing Body");

			EmailUtil.sendAttachmentEmail(toEmail,"SSLEmail Testing Subject with Attachment", "SSLEmail Testing Body with Attachment", new File("/media/administrator/data_ntxuan/TTTOAN/Jee Projects/DongXanh_Proj/DongXanh_V2/PendingList.xls"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendEmail(String toEmail, String subject, String body) throws Exception{
		sendEmail(createSession(), toEmail, subject, body);
	}
	
	public static void sendAttachmentEmail(String toEmail, String subject, String body, File fileAttachment) throws Exception{
		sendAttachmentEmail(createSession(), toEmail, subject, body, fileAttachment);
	}
	
	private static Session createSession(){
		Properties props = new Properties();
		props.put("mail.smtp.host", MAIL_HOST); //SMTP Host
		props.put("mail.smtp.socketFactory.port",MAIL_PORT); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.port", MAIL_PORT); //SMTP Host
		props.put("mail.smtp.auth", "true"); //SMTP Host
		props.put("mail.smtp.starttls.enable", "true"); //SMTP Host
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MAIL_ACCOUNT, MAIL_PASSWORD);
			}
		};
		return Session.getDefaultInstance(props, auth);
	}


	/**
	 * Utility method to send simple HTML email
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	private static void sendEmail(Session session, String toEmail, String subject, String body) throws Exception{
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress(MAIL_ACCOUNT, "NoReply-DongXanhVN"));

	      msg.setReplyTo(InternetAddress.parse(MAIL_ACCOUNT, false));

	      msg.setSubject(subject, "UTF-8");

	      msg.setText(body, "UTF-8");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      System.out.println("Message is ready");
    	  Transport.send(msg);  
	      System.out.println("EMail Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      throw e;
	    }
	}
	
	/**
	 * Utility method to send email with attachment
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	private static void sendAttachmentEmail(Session session, String toEmail, String subject, String body, File fileAttachment) throws Exception{
		try{
	         MimeMessage msg = new MimeMessage(session);
	         msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		     msg.addHeader("format", "flowed");
		     msg.addHeader("Content-Transfer-Encoding", "8bit");
		      
		     msg.setFrom(new InternetAddress(MAIL_ACCOUNT, "NoReply-DongXanhVN"));

		     msg.setReplyTo(InternetAddress.parse(MAIL_ACCOUNT, false));

		     msg.setSubject(subject, "UTF-8");

		     msg.setSentDate(new Date());

		     msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		      
	         // Create the message body part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setText(body);
	         
	         // Create a multipart message for attachment
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Second part is attachment
	         messageBodyPart = new MimeBodyPart();
	         DataSource source = new FileDataSource(fileAttachment);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(fileAttachment.getName());
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         msg.setContent(multipart);

	         // Send message
	         Transport.send(msg);
	         System.out.println("EMail Sent Successfully with attachment!!");
	      }catch (MessagingException e) {
	         e.printStackTrace();
	         throw e;
	      } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
			 throw e;
	      }
	}
	
}
