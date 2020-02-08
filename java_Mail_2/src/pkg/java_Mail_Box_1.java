
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

public class java_Mail_Box_1 
{
	public static void sendEmail(String recepient) throws Exception
	{
		System.out.println("Prepareing Send Email");
		Properties properties=new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port"	, "587");

		String Acc="saurabh4321keshari@gmail.com";
		String psw="xxxxxx";

		Session session=Session.getDefaultInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(Acc, psw);
			}
		});
		System.out.println("EmailProcessStarted");

		Message message=preparemsg(session, Acc, recepient);
		Transport.send(message);
		System.out.println("Message Sent SuccessFully");

	}


	private static Message preparemsg(Session session,String acc, String recepient) throws Exception
	{
		Message message=new MimeMessage(session);
		message.setFrom(new InternetAddress(acc));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
		message.setSubject("first Email Msg");
		message.setText("hii this mail come  from Java Program");

		String html="<h3>Hii Its HTML tag</h3><br/><a href=\"https://wa.me/919993922225\">WhatsApp</a><br/><a href=\"https://www.facebook.com/keshari.saurabh\">Facebook</a> ";
		message.setContent(html, "text/html");
		return message;

	}
}