package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.security.TokenUtils;
import com.ftn.WebXML2018.XWS_2018_Backend.service.ReservationService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;

@RestController
@RequestMapping(value = "/rest/secured/")
public class ReservationController {
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired 
	private UserService userService;
	
	
	@PreAuthorize("hasAuthority('REG_USER')")
	@RequestMapping(value = "getUserReservations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Page<Reservation>> getUserReservations(@RequestParam int pageNum, @RequestParam int mode, HttpServletRequest request, HttpServletResponse response){
		
		User user = userService.getUserFromToken(request, tokenUtils);
		
		if(user == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
		
		boolean isConfirmed;
		
		if(mode == 0) {
			isConfirmed = false;
		}else if(mode == 1) {
			isConfirmed = true;
		}else{
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
		
		Page<Reservation> retVal = reservationService.findByRegisteredUserAndConfirmed(user.getRegisteredUser(), isConfirmed, new PageRequest(pageNum-1, 5));
		
		return new ResponseWrapper<Page<Reservation>>(retVal, true);
	}

}
