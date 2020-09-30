package ctdDemo.service;

import java.io.File;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("EmailServiceImpl")
public class EmailServiceImpl implements EmailService{
	@Value("${spring.mail.username}")
	private String username;
	@Value("${spring.mail.password}")
	private String password;
	
	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private int port;

	@Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {

    	JavaMailSender javaMailSender = getJavaMailSender();
        MimeMessage message = javaMailSender.createMimeMessage();
         
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("noreply@payment.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
            
        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("paper_payment_form.pdf", file);
	     
        javaMailSender.send(message);
	}
    
   
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        
        return mailSender;
    }
    
    public static void main(String[] args) throws MessagingException {
		new EmailServiceImpl().sendMessageWithAttachment("igor26.78@gmail.com", "test", "tt", "C:\\Users\\IgorL\\Downloads\\paper_payment_form.pdf");
	}
}
