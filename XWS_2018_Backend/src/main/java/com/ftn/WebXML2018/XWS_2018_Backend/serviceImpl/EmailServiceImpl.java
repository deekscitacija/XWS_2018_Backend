package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
				  "      <td>"+link+"</td>\r\n" + 
				  "    </tr>\r\n" + 
				  "  </table>\r\n" + 
				  "</div>"+
				  "</body>"+
				  "</html>";
		
		helper.setText(poruka, true);
		mailSender.send(message);
		
	}
	
	/*
	@Async
	public void sendRezervacijaMail(ArrayList<Rezervacija> rezervacije) throws MessagingException{
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, false);
		helper.setFrom(environment.getProperty("spring.mail.username"));
		helper.setTo(rezervacije.get(0).getRegKorisnik().getReg_korisnik_id().getEmail());
		helper.setSubject("Isa Pozorista i Biskopi vasa rezeracija");
		String datumProjekcije = new SimpleDateFormat("dd-MM-YYYY HH-mm").format(new Timestamp(rezervacije.get(0).getKarta().getProjekcija().getDatum().getTime()));
		
		String rezPodaci = "";
		for(Rezervacija rezervacija : rezervacije) {
			
			rezPodaci+="<tr>"+
					" <td>"+rezervacija.getKarta().getProjekcija().getPredFilm().getNaziv()+"</td>" + 
					"            <td>"+rezervacija.getKarta().getProjekcija().getSala().getPozBio().getNaziv()+"</td>" + 
					"            <td>" + rezervacija.getKarta().getProjekcija().getSala().getNaziv()+"</td>"+ 
					"            <td>RSD "+rezervacija.getKarta().getSediste().getSegment().getTip().getCena()+" "+rezervacija.getKarta().getSediste().getSegment().getTip().getNaziv()+"</td>" + 
					"            <td>"+rezervacija.getKarta().getSediste().getId()+"</td>" + 
					"            <td>"+datumProjekcije+"</td>"						
					+"</tr>";	
		}
		
		String mailMessage = "<html><body><p>Postovani "+rezervacije.get(0).getRegKorisnik().getReg_korisnik_id().getIme()+", ovo su podaci o vasoj rezervaciji: <br>"
				+"<table><tr>" + 
				"          <th>Film/Predstava</th>" + 
				"          <th>Bioskop/Pozoriste</th>"+ 
				"          <th>Sala</th>" + 
				"          <th>Cena</th>" + 
				"          <th>Sifra mesta</th>" + 
				"          <th>Datum</th>" + 
				"        </tr>"+
				
				
				rezPodaci+
				
				"</table><br></p><p>Vasu rezervaciju mozete otkazati do pola sata pred pocetak predstave ili projekcije"
				+ "</p></body></html>";
			helper.setText(mailMessage, true);
			
			mailSender.send(message);
	}
	
	*/
	
}
