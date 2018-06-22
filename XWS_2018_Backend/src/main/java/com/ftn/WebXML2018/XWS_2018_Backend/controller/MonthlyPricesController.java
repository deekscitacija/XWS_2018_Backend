package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.BookingUnitDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.PriceDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.MonthlyPrices;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BookingUnitService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.MonthlyPricesService;

@RestController
@RequestMapping(value = "/rest/")
public class MonthlyPricesController {
	
	@Autowired
	private MonthlyPricesService monthlyPricesService;
	
	@Autowired
	private BookingUnitService bookingUnitService;
	
	
	@RequestMapping(value = "getPriceForUnit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<MonthlyPrices> getPriceForUnit(@RequestParam Long unitId, @RequestParam int month, @RequestParam int year, HttpServletResponse response){
		
		BookingUnit bookingUnit = bookingUnitService.findById(unitId);
		
		if(bookingUnit == null) {
			return new ResponseWrapper<MonthlyPrices>(null, "Neuspesno dobavljanje cene za trazeni entitet.", false);
		}
		
		MonthlyPrices retVal = monthlyPricesService.findOneByBookingUnitAndMonthAndYear(bookingUnit, month, year);
		
		if(retVal == null) {
			return new ResponseWrapper<MonthlyPrices>(null, "Neuspesno dobavljanje cene za trazeni entitet.", false);
		}
		
		return new ResponseWrapper<MonthlyPrices>(retVal, true);
	}
	
	@RequestMapping(value = "calculateTotalPrice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Double> calculateTotalPrice(@RequestBody PriceDTO params, HttpServletResponse response){
		
		BookingUnit bookingUnit = bookingUnitService.findById(params.getUnitId());
		
		if(bookingUnit == null) {
			return new ResponseWrapper<Double>(null, "Neuspesno dobavljanje cene boravka.", false);
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateFromDate = null;
		try {
			dateFromDate = dateFormat.parse(params.getFromDate());
		} catch (ParseException e) {
			return new ResponseWrapper<Double>(null,"Neuspesno vracena cena.",false);
		}
		Date dateToDate = null;
		try {
			dateToDate = dateFormat.parse(params.getToDate());
		} catch (ParseException e) {
			return new ResponseWrapper<Double>(null,"Neuspesno vracena cena.",false);
		}
			
		double total = monthlyPricesService.calculateTotalPrice(bookingUnit, dateFromDate, dateToDate);
		
		if(total < 0) {
			return new ResponseWrapper<Double>(null, "Neuspesno dobavljanje cene boravka.", false);
		}
		
		return new ResponseWrapper<Double>(new Double(total), true);
	}
	
	
}
