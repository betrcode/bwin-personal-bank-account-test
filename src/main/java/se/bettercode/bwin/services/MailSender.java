package se.bettercode.bwin.services;

import java.security.Security;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

/**
 * A simple class to send emails using JavaMail and Gmail
 * TODO: Finish it.
 * @author max
 * @since 2010-05-11
 */
public class MailSender {
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	
	public static void send(String smtpServer, int smtpPort, String to, String from, String subject, String body) {
		try	{
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			
			Properties props = System.getProperties();
			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.port", smtpPort);
			props.put("mail.debug", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.socketFactory.port", smtpPort);
			props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.put("mail.smtp.socketFactory.fallback", "false");

			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("max.wenzin@gmail.com", "xxxxxxxxxxxxxxx");
						}
			});
			
			//Create a new message
			Message msg = new MimeMessage(session);
			//Set the FROM and TO fields
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
			//Set the subject and body text
			msg.setSubject(subject);
			msg.setText(body);
			msg.setSentDate(new Date());
			//Send the message
			Transport.send(msg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
