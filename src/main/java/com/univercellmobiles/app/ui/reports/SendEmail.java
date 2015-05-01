package com.univercellmobiles.app.ui.reports;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
	
	public static void sendHTMLasMail(String htmlstr){
		
		String to="naresh.uppada@gmail.com";//change accordingly

		//Get the session object
		  Properties props = new Properties();
		  props.put("mail.smtp.host", "smtp.gmail.com");
		  props.put("mail.smtp.socketFactory.port", "465");
		  props.put("mail.smtp.socketFactory.class",
		        	"javax.net.ssl.SSLSocketFactory");
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.port", "465");
		 
		  Session session = Session.getDefaultInstance(props,
		   new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		   return new PasswordAuthentication("sales.palasaunivercell@gmail.com","galaxy@100");//change accordingly
		   }
		  });
		 
		//compose message
		  try {
		   MimeMessage message = new MimeMessage(session);
		   message.setFrom(new InternetAddress("sales.palasaunivercell@gmail.com"));//change accordingly
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		   message.setSubject("Daily Sale Report - "+ new Date());
		   message.setContent(htmlstr,"text/html");
		   
		   //send message
		   Transport.send(message);

		   System.out.println("message sent successfully");
		 
		  } catch (MessagingException e) {throw new RuntimeException(e);}
		 
		 }
	
	
	
 public static void main(String[] args) {
	 sendHTMLasMail("<h1>Sample Report</h1><p> This is the report</p>"
		   		+ "<table><tr><td>Cash Value</td><td>50000</td></tr><tr><td>Stock Value</td><td>20000</td></tr></table>" );
 }
 
}

