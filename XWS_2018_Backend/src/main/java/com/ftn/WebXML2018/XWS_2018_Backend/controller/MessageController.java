package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.Message;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.security.TokenUtils;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BookingUnitService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.MessageService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.ReservationService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;

@RestController
@RequestMapping(value = "/rest/secured/")
public class MessageController {
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private BookingUnitService bookingUnitService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private MessageService messageService;
	
	@PreAuthorize("hasAuthority('REG_USER')")
	@RequestMapping(value = "getUserMessages/{pageNum}/{mode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Page<Message>> getUserMessages(@PathVariable int pageNum, @PathVariable int mode, HttpServletRequest request, HttpServletResponse response){
		
		User user = userService.getUserFromToken(request, tokenUtils);
		
		if(user == null) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return null;
		}
		
		if( (mode != 0 && mode != 1) || pageNum < 0) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
		
		Page<Message> retVal = messageService.findBySenderOrRecipient(user, mode == 0 ? true : false, new PageRequest(pageNum-1, 5));
		
		return new ResponseWrapper<Page<Message>>(retVal, true);
	}
	
	@PreAuthorize("hasAuthority('REG_USER')")
	@RequestMapping(value = "sendMessageToAgent/{reservationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Message> sendMessageToAgent(@PathVariable Long reservationId, @RequestBody String messageContent, HttpServletRequest request, HttpServletResponse response){
		
		User sender = userService.getUserFromToken(request, tokenUtils);
		
		if(sender == null) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return null;
		}
		
		if(messageContent.isEmpty()) {
			return new ResponseWrapper<>(null, "Poruka koju pokusavate da posaljete je prazna.", false);
		}
		
		Reservation reservation = reservationService.findById(reservationId);
		
		if(reservation == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
		
		User recipient = userService.getUser(reservation.getBookingUnit().getAgent().getId());
		
		Message retVal = new Message(messageContent, sender, recipient);
		retVal = messageService.saveMessage(retVal);
		
		return new ResponseWrapper<Message>(retVal, "Vasa poruka je uspesno poslata", true);
	}

}
