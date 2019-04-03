package br.usp.ime.mail;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.usp.ime.ganimedes.interfaces.MailerRemoteInterface;
import br.usp.ime.util.Recursos;

@Stateless
public class Mailer implements MailerRemoteInterface {

	@Resource(name = "java:/mail/ime")
	private Session mailSession;

	public void send(String from, String to, String cc, String subject, String msg) {
		Recursos recursos = new Recursos();

		String emailAdmin = recursos.getResourceValue("email-admin");

		String bcc = emailAdmin;

		Message message = new MimeMessage(mailSession);

		try {

			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc, true));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc, true));

			message.setSubject(subject);

			message.setContent(msg, "text/html");
			message.setFrom(new InternetAddress(from));

			message.setHeader("X-Mailer", "JavaMail");

			Date timeStamp = new Date();
			message.setSentDate(timeStamp);

			Transport.send(message);

		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}