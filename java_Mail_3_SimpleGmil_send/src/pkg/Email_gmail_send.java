package pkg;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email_gmail_send
{
	public static void main(String[] args) 
	{
		Properties properties=new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String Ac="kesaharisaurabh@gmail.com";
		String psw="xxxx";
		String MailTO="saurabh4321keshari@gmail.com";
		
		
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(Ac, psw);
			}
		});
		Message message=new MimeMessage(session);
		try {
			
			message.setFrom(new InternetAddress(Ac));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(MailTO));
			message.setSubject("From Java Application 2");
			message.setText("Congrats... mail Successufuly Send");
			Transport.send(message);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}
}
