package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.BookingUnitDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.ReservationStatus;
import com.ftn.WebXML2018.XWS_2018_Backend.exceptions.ReservationAlredyExsistsException;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.security.TokenUtils;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BookingUnitService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.MonthlyPricesService;
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
	private MonthlyPricesService monthlyPricesService;
	
	@Autowired
	private BookingUnitService bookingUnitService;
	
	@Autowired 
	private UserService userService;
	
	
	@PreAuthorize("hasAuthority('REG_USER')")
	@RequestMapping(value = "getUserReservations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Page<Reservation>> getUserReservations(@RequestParam int pageNum, @RequestParam int mode, HttpServletRequest request, HttpServletResponse response){
		
		User user = userService.getUserFromToken(request, tokenUtils);
		
		if(user == null) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return null;
		}
		
		ReservationStatus reservationStatus;
		
		if(mode == 0) {
			reservationStatus = ReservationStatus.WAITING;
		}else if(mode == 1) {
			reservationStatus = ReservationStatus.CONFIRMED;
		}else{
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
		
		Page<Reservation> retVal = reservationService.findByRegisteredUserAndConfirmed(user.getRegisteredUser(), reservationStatus, new PageRequest(pageNum-1, 5));
		
		return new ResponseWrapper<Page<Reservation>>(retVal, true);
	}
	
	@PreAuthorize("hasAuthority('REG_USER')")
	@RequestMapping(value = "makeReservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Reservation> makeReservation(@RequestParam(value="unitId", required = true) Long unitId,
														@RequestParam(value="dateFrom", required = true) String dateFrom, 
			  											@RequestParam(value="dateTo", required = true) String dateTo, @RequestBody Reservation newReservation,  HttpServletRequest request, HttpServletResponse response){
		
		User user = userService.getUserFromToken(request, tokenUtils);
		
		if(user == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
		
		BookingUnit bookingUnit = bookingUnitService.findById(unitId);
		
		if(bookingUnit == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateFromDate = null;
		try {
			dateFromDate = dateFormat.parse(dateFrom);
		} catch (ParseException e) {
			return new ResponseWrapper<Reservation>(null,"Neuspesna rezervacija.",false);
		}
		Date dateToDate = null;
		try {
			dateToDate = dateFormat.parse(dateTo);
		} catch (ParseException e) {
			return new ResponseWrapper<Reservation>(null,"Neuspesna rezervacija.",false);
		}

		newReservation.setFromDate(dateFromDate);
		newReservation.setToDate(dateToDate);
		
		if(!reservationService.findCriticalReservations(bookingUnit, dateFromDate, dateToDate).isEmpty()) {
			return new ResponseWrapper<Reservation>(null, "Smestaj je zauzet u ovom periodu!", false);
		}
		
		if(newReservation.getFromDate() == null || newReservation.getToDate() == null || 
				newReservation.getSubjectName().isEmpty() || newReservation.getSubjectSurname().isEmpty()) {
			
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
		
		if(newReservation.getFromDate().after(newReservation.getToDate())) {
			
			return new ResponseWrapper<>(null, "Neodgovarajuci datumi, ponovite unos.", false);
		}
		
		double totalPrice = monthlyPricesService.calculateTotalPrice(bookingUnit, newReservation.getFromDate(), newReservation.getToDate());
		
		if(totalPrice < 0.00) {
			
			return new ResponseWrapper<>(null, "Nemoguce obaviti rezerveciju. Pokusajte kasnije.", false);
		}
		
		newReservation.setBookingUnit(bookingUnit);
		newReservation.setRegisteredUser(user.getRegisteredUser());
		newReservation.setTotalPrice(totalPrice);
		newReservation.setReservationStatus(ReservationStatus.WAITING);
		
		Reservation retVal = null;
		
		try {
			retVal = reservationService.saveReservation(newReservation);
		} catch (ReservationAlredyExsistsException e) {
			
			return new ResponseWrapper<>(null, "Nemoguce obaviti rezerveciju. Pokusajte kasnije.", false);
		}
				
		return new ResponseWrapper<Reservation>(retVal, "Vasa rezervacija je uspesno zabelezena.", true);
	}
	
	@PreAuthorize("hasAuthority('REG_USER')")
	@RequestMapping(value = "cancelReservation/{reservationId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Boolean> cancelReservation(@PathVariable Long reservationId, HttpServletRequest request, HttpServletResponse response){
		
		Reservation forDelete = reservationService.findById(reservationId);
		User user = userService.getUserFromToken(request, tokenUtils);
		
		if(user == null || forDelete == null || !forDelete.getRegisteredUser().getId().equals(user.getId())) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return null;
		}
		
		reservationService.cancelReservation(forDelete);
		
		return new ResponseWrapper<Boolean>(true, true);
	}

}
