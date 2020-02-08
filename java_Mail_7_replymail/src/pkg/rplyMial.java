package pkg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
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

public class rplyMial 
{
	public static void main(String[] args) throws Exception 
	{
		String userName="kesaharisaurabh@gmail.com";
		String password="xxxxx";
		
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
		
		Store store=session.getStore("pop3s");
		store.connect("pop.gmail.com", userName, password);
		
		Folder folder=store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		
		Message message[]=folder.getMessages();
		for(int i=0;i<message.length;i++)
		{
			Message msg=message[i];
			System.out.println("Email Number "+(i+1));
			System.out.println("Email Subject"+msg.getSubject());
			System.out.println("From "+msg.getFrom()[0]);
			System.out.println("Text "+msg.getContent().toString());
			System.out.println(msg.getSentDate());
			System.out.println();
		}
		
		System.out.println("Mail You want to Reply");
		
		String emailno=new BufferedReader(new InputStreamReader(System.in)).readLine();
		System.out.println("Enter Rply msg");
		String emailrply=new BufferedReader(new InputStreamReader(System.in)).readLine();
		
		Message emailmsg=folder.getMessage(Integer.parseInt(emailno));
		
		Message mimemsg=new MimeMessage(session);
		mimemsg=emailmsg.reply(false);
		mimemsg.setFrom(new InternetAddress(userName));
		mimemsg.setText(emailrply);
		mimemsg.setSubject("REplyMail");
		mimemsg.addRecipient(Message.RecipientType.TO, emailmsg.getFrom()[0]);
		
		Transport.send(mimemsg);
		System.out.println("Reply Mail Sended");
		 
	}
}
