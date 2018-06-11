package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.security.TokenUtils;
import com.ftn.WebXML2018.XWS_2018_Backend.service.RegisteredUserService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;

@RestController
@RequestMapping(value = "/rest/secured/")
public class RegisteredUserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegisteredUserService registeredUserService;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@PreAuthorize("hasAuthority('REG_USER')")
	@RequestMapping(value = "changePersonalInfo", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseWrapper<User> changePersonalInfo(@RequestBody User user, HttpServletRequest request) {
	
		User currentUser = userService.getUserFromToken(request, tokenUtils);
		
		if(currentUser == null) {
			return new ResponseWrapper<>(null, "Neuspesno izmenjeni podaci.", false);
		}
		
		currentUser = userService.changeUserInfo(currentUser, user.getName(), user.getSurname(), user.getCity().getName(), user.getCity().getCountry().getId().toString(), 
				user.getHomeAddress(), user.getRegisteredUser().getEmail(), user.getRegisteredUser().getPhoneNumber(), user.getCity().getPostcode());
		
		if(currentUser == null) {
			return new ResponseWrapper<>(null, "Neuspesno izmenjeni podaci.", false);
		}
		
		return new ResponseWrapper<>(currentUser, "Uspesno izmenjeni podaci.", true);
	}

}
