package pkg;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class javaAttach_sender 
{
	public static void main(String[] args) throws Exception 
	{	
		String userName="kesaharisaurabh@gmail.com";
		String password="xxxxxx";
		
		Properties properties=new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session=Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication(userName, password);
			}
		});
		System.out.println("Program Start");
		Message message=new MimeMessage(session);
		message.setFrom(new InternetAddress(userName));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("saurabh4321keshari@gmail.com"));
		
		MimeBodyPart bodyPart1=new MimeBodyPart();
		bodyPart1.setText("Hii MimeBodyPart 1");
		
		MimeBodyPart bodyPart2=new MimeBodyPart();
		System.out.println("Uploading File");
		
		DataSource dataSource=new FileDataSource("D:\\xyz.txt");
		bodyPart2.setDataHandler(new DataHandler(dataSource));
		bodyPart2.setFileName("xyz");
		
		Multipart multipart=new MimeMultipart();
		System.out.println("FileAttach");
		
		multipart.addBodyPart(bodyPart1);
		multipart.addBodyPart(bodyPart2);
		
		message.setContent(multipart);
		message.setText("WithAttach");
		
		Transport.send(message);
		System.out.println("EmailSendSuccessfully");
		
	}
}
