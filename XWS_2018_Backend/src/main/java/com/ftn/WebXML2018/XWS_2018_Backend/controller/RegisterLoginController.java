package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.UserDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.PasswordResetToken;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.helpClasses.RandomStringGenerator;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.EmailService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.PasswordResetTokenService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;

@RestController
@RequestMapping(value = "/rest/")
public class RegisterLoginController {
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private PasswordResetTokenService passwordResetTokenService;
	
	@Autowired
	private RandomStringGenerator strGenerator;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<User> register(
			@RequestParam(value="username", required = true) String username,
			@RequestParam(value="password", required=true) String password,
			@RequestParam(value="ime", required = true) String ime,
			@RequestParam(value="prezime", required = true) String prezime,
			@RequestParam(value="grad", required = true) String grad,
			@RequestParam(value="drzava", required = true) String drzava,
			@RequestParam(value="adresa", required = true) String adresa,
			@RequestParam(value="email", required = true) String email,
			@RequestParam(value="telefon", required = true) String telefon,
			@RequestParam(value="postbroj", required = true) String postbroj,
			HttpServletResponse response
			) {
		
		System.out.println(username+" "+password+" "+ime+" "+prezime+" "+grad+" "+drzava+" "+adresa+" "+email+" "+telefon+" "+postbroj);
		
		User newUser = userService.register(username.trim(), password, ime.trim(), prezime.trim(), grad.trim(), drzava, 
				adresa.trim().isEmpty() ? null : adresa.trim(), email.trim(), telefon.trim().isEmpty() ? null : telefon.trim(), postbroj.trim().isEmpty() ? null : postbroj.trim());
		
		if(newUser == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			new ResponseWrapper<>(null, false);
		}
		
		return new ResponseWrapper<User>(newUser, true);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<UserDTO> login(@RequestParam(value="username", required = true) String username,
										  @RequestParam(value="password", required=true) String password){
		
		User user = userService.getByUsername(username);
		
		if(user == null) {
			return new ResponseWrapper<>(null, "Nepostojeci username ili password.", false);
		}
		
		if(!user.getPassword().equals(password)) {
			return new ResponseWrapper<>(null, "Nepostojeci username ili password.", false);
		}
		
		ModelMapper modelMapper = new ModelMapper();
		UserDTO retVal = modelMapper.map(user, UserDTO.class);
		
		System.out.println("USLOOO EEE");
		
		return new ResponseWrapper<UserDTO>(retVal, true);
	}
	
	@RequestMapping(value = "resetPass", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<String> resetPassword(@RequestParam(value="username", required = true) String username, HttpServletResponse response) {
		
		User user = userService.getByUsername(username);
		
		if(user == null) {
			return new ResponseWrapper<>(null, "Neuspesno slanje zahteva za izmenu lozinke.", false);
		}
		
		String resetTokenStr = strGenerator.genRandomString8();
		
		System.out.println(resetTokenStr);
		
		long ONE_DAY_IN_MILIS = 3600000*24;

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		long vremeKreiranja = cal.getTimeInMillis();
		Date istice = new Date(vremeKreiranja + ONE_DAY_IN_MILIS);
		System.out.println(new Date().toString());
		System.out.println(istice.toString());
		
		PasswordResetToken resetToken = new PasswordResetToken(resetTokenStr, user, istice);
		passwordResetTokenService.generateToken(resetToken);
		
		try {
			emailService.changePassword(user, resetTokenStr);
		} catch (MessagingException e) {
			System.out.println("### Greska kod maila!");
			return new ResponseWrapper<>(null, "Neuspesno slanje zahteva za izmenu lozinke.", false);
		}
		
		return new ResponseWrapper<String>(null, "Zahtev za izmenu lozinke je poslat na Vasu email adresu.", true);
	}

}
