package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Environment environment;

	@Override
	@Async
	public void changePassword(User user, String token) throws MessagingException {
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, false);
		helper.setFrom(environment.getProperty("spring.mail.username"));
		helper.setTo(user.getRegisteredUser().getEmail());
		helper.setSubject("XWS_Booking Izmena Lozinke");
		
		String poruka = "";
		String link = "http://localhost:4200/passwordReset/"+user.getUsername();
		poruka += "<html>"+
				  "<body>"
				  + "<div>\r\n" + 
				  "  <table>\r\n" + 
				  "    <tr>\r\n" + 
				  "      <td>Postovani "+user.getName()+" "+user.getSurname()+" primili smo Vas zahtev za promenu lozinke.</td>\r\n" + 
				  "    </tr>\r\n" + 
				  "    <tr>\r\n" + 
				  "      <td>Username: </td>\r\n" + 
				  "      <td>"+user.getUsername()+"</td>\r\n" + 
				  "    </tr>\r\n" + 
				  "    <tr>\r\n" + 
				  "      <td>Kod: </td>\r\n" + 
				  "      <td>"+token+"</td>\r\n" + 
				  "    </tr>\r\n" + 
				  "    <tr>\r\n" + 
				  "      <td rowspan=\"2\">Unesite sigurnosni kod na ovoj stranici: </td>\r\n" + 
				  "    </tr>\r\n" + 
				  "    <tr>\r\n" + 
				  "      <td><a href=\""+link+"\">Kliknite ovde</a></td>\r\n" + 
				  "    </tr>\r\n" + 
				  "  </table>\r\n" + 
				  "</div>"+
				  "</body>"+
				  "</html>";
		
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(poruka,"UTF-8","html");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		message.setContent(multipart);
			
		mailSender.send(message);
	}
 
	
}
