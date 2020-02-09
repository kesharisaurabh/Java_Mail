package pkg;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail_forword 
{
	public static void main(String[] args) throws Exception 
	{
		String userName="kesaharisaurabh@gmail.com";
		String password= "xxx";
		
		Properties properties=new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(userName, password);
			}
		});
		
		Store store=session.getStore("pop3s");
		store.connect("pop.gmail.com", userName, password);
		
		Folder folder=store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		
		Message msg1[]=folder.getMessages();
		
		for(int i=0;i<msg1.length;i++) {
			Message msg=msg1[i];
			System.out.println("Email Number "+(i+1));
			System.out.println("Email Subject"+msg.getSubject());
			System.out.println("From "+msg.getFrom()[0]);
			System.out.println("Text "+msg.getContent().toString());
			System.out.println(msg.getSentDate());
			System.out.println();	
		}		
		
		System.out.println("Enter Email no which u want to forword ");
		String emailno=new BufferedReader(new InputStreamReader(System.in)).readLine();
		
		System.out.println("Enter Email Address to forword mail");
		String Emailadd=new BufferedReader(new InputStreamReader(System.in)).readLine();
		
		Message eamilmsg=folder.getMessage(Integer.parseInt(emailno));
		
		Message message=new MimeMessage(session);
		message=eamilmsg.reply(false);
		message.setFrom(new InternetAddress(userName));
		message.setSubject("Forword");
		message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(Emailadd));
		
		BodyPart bodyPart=new MimeBodyPart();
		bodyPart.setText("Hii Body Part 1");
		
		MimeMultipart mimeMultipart=new MimeMultipart();
		mimeMultipart.addBodyPart(bodyPart);
		
		message.setContent(mimeMultipart);
		
		Transport.send(message);
		System.out.println("Forword mail successful");
		
	}
}
