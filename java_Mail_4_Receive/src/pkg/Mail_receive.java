package pkg;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

import com.sun.mail.pop3.POP3Store;

public class Mail_receive 
{
	public static void main(String[] args) throws Exception 
	{
		
		String host = "pop.gmail.com";
		String username= "kesaharisaurabh@gmail.com";  
		String password= "xxxx";  
		
		
		Properties properties=new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session=Session.getInstance(properties);
		
		Store emailStore=session.getStore("pop3s");
		emailStore.connect(host, username, password);
		
		
		Folder emailFolder=emailStore.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);
		
		Message message[]=emailFolder.getMessages();
		
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
		
		emailFolder.close();
		emailStore.close();
		
		
	}
}
