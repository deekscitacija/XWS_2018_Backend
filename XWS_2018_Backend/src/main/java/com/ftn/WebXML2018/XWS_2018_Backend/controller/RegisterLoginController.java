package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.UserDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.PasswordResetToken;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.helpClasses.RandomStringGenerator;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.security.TokenUtils;
import com.ftn.WebXML2018.XWS_2018_Backend.securityBeans.CustomUserDetailsFactory;
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
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
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
	public ResponseWrapper<String> login(@RequestParam(value="username", required = true) String username,
										 @RequestParam(value="password", required=true) String password){
		
		User user = userService.getByUsername(username);
		
		if(user == null) {
			return new ResponseWrapper<>(null, "Nepostojeci username ili password.", false);
		}
		
		if(!passwordEncoder.matches(password, user.getPassword())) {
			return new ResponseWrapper<>(null, "Nepostojeci username ili password.", false);
		}
		
		String token = tokenUtils.generateToken(CustomUserDetailsFactory.createCustomUserDetails(user));
		
		return new ResponseWrapper<String>(token, true);
	}
	
	@PreAuthorize("hasAnyAuthority('REG_USER', 'ADMIN', 'AGENT')")
	@RequestMapping(value = "getUserFromToken", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<UserDTO> getUserFromToken(ServletRequest request){
		
		User user = userService.getUserFromToken(request, tokenUtils);
		
		if(user == null) {
			return new ResponseWrapper<>(null, "Greska prilikom dobavljanja korisnika.", false);
		}
		
		ModelMapper modelMapper = new ModelMapper();
		UserDTO retVal = modelMapper.map(user, UserDTO.class);
		
		return new ResponseWrapper<UserDTO>(retVal, true);
	}
	
	@RequestMapping(value = "resetPass", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<String> resetPassword(@RequestParam(value="username", required = true) String username, HttpServletResponse response) {
		
		User user = userService.getByUsername(username);
		
		if(user == null) {
			return new ResponseWrapper<>(null, "Neuspesno slanje zahteva za izmenu lozinke.", false);
		}
		
		String resetTokenStr = strGenerator.genRandomString8();
		
		long ONE_DAY_IN_MILIS = 3600000*24;

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		long vremeKreiranja = cal.getTimeInMillis();
		Date istice = new Date(vremeKreiranja + ONE_DAY_IN_MILIS);
		
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
	
	@RequestMapping(value = "checkToken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<String> resetPassword(@RequestParam(value="username", required = true) String username, 
												 @RequestParam(value="codeToken", required = true) String codeToken, 
												 HttpServletResponse response){
		
		if(username.trim().isEmpty() || codeToken.trim().isEmpty()) {
			return new ResponseWrapper<>(null, "Neispravan username ili token, pokusajte ponovo.", false);
		}
		
		User user = userService.getByUsername(username);
		
		if(user == null) {
			return new ResponseWrapper<>(null, "Neispravan username ili token, pokusajte ponovo.", false);
		}
		
		List<PasswordResetToken> tokens = this.passwordResetTokenService.getTokensByUser(user);
		
		if(tokens.isEmpty()) {
			return new ResponseWrapper<>(null, "Neispravan username ili token, pokusajte ponovo.", false);
		}
		
		PasswordResetToken latestToken = tokens.get(0);

		if(latestToken.getUser().getUsername().equals(username) && latestToken.getToken().equals(codeToken)) {
			return new ResponseWrapper<>(null, "Uspesno unet kod za izmenu lozinke, izaberite svoju novu lozinku.", true);
		}
		
		return new ResponseWrapper<>(null, "Neispravan username ili token, pokusajte ponovo.", false);
	}
	
	@RequestMapping(value = "changePass", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<String> changePassword(@RequestParam(value="username", required = true) String username,
											      @RequestParam(value="newPass", required = true) String newPass){
		
		if(username.trim().isEmpty() || newPass.trim().isEmpty()) {
			return new ResponseWrapper<>(null, "Greska prilikom promene lozinke.", false);
		}
		
		User user = userService.getByUsername(username);
		
		if(user == null) {
			return new ResponseWrapper<>(null, "Greska prilikom promene lozinke.", false);
		}
		
		user.setPassword(this.passwordEncoder.encode(newPass));
		userService.saveUser(user);
		
		return new ResponseWrapper<>(null, "Uspesna izmena lozinke, mozete se prijaviti sa svojom novom lozinkom.", true);
	}
	

}
