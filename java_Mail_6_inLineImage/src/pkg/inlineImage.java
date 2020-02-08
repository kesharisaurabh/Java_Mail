package pkg;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class inlineImage 
{
	public static void main(String[] args) throws Exception 
	{
		String userName="kesaharisaurabh@gmail.com";
		String password="xxxxxxx";
		
		Properties properties=new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session=Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(userName, password);
			}
		});
		
		Message message=new MimeMessage(session);
		message.setFrom(new InternetAddress(userName));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("saurabh4321keshari@gmail.com"));
		
		System.out.println("mimeBoby Starts");
		MimeBodyPart bodyPart1=new MimeBodyPart();
		bodyPart1.setText("Hi Welcome Bodypart 1");
		
		MimeBodyPart bodyPart2=new MimeBodyPart();
		bodyPart2.setText("Hi Boby Part 2");
		
		MimeBodyPart bodyPart3=new MimeBodyPart();
		
		DataSource dataSource=new FileDataSource("D:\\Untitled.png");
		bodyPart3.setDataHandler(new DataHandler(dataSource));
		
		MimeMultipart mimeMultipart=new MimeMultipart();
		mimeMultipart.addBodyPart(bodyPart1);
		mimeMultipart.addBodyPart(bodyPart2);
		mimeMultipart.addBodyPart(bodyPart3);
		
		message.setContent(mimeMultipart);
		System.out.println("Email Sended");
		Transport.send(message);
		System.out.println("Mail sended successfuly");
	}
}
