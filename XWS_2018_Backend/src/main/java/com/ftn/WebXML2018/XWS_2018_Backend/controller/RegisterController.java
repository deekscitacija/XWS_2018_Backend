package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;

@RestController
@RequestMapping(value = "/rest/")
public class RegisterController {
	
	@Autowired 
	private UserService userService;
	
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

}
