package ctdDemo.service;

import javax.mail.MessagingException;

public interface EmailService {
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException ;
}
