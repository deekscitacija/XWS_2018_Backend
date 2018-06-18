package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.BookingUnitDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.CityCountryDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BookingUnit;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.MonthlyPrices;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Reservation;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BookingUnitService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.CityService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.CountryService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.ReservationService;

@RestController
@RequestMapping(value = "/rest/")
public class SearchController {

	@Autowired
	private BookingUnitService bookingUnitService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CityService cityService;
	
	@RequestMapping(value="getBookingUnits/{page}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ArrayList<BookingUnitDTO>> getBookingUnits(@PathVariable int page, @RequestParam(value = "peopleNumber", required = true) int peopleNumber,
															  @RequestParam(value="dateFrom", required = true) String dateFrom, 
															  @RequestParam(value="dateTo", required = true) String dateTo,
															  @RequestParam(value="country", required = false) Long countryId,
															  @RequestParam(value="city", required = false) Long cityId){
		
		
		if(countryId==null && cityId==null) {
			return new ResponseWrapper<ArrayList<BookingUnitDTO>>(null,"Morate uneti ili grad ili drzavu za pretragu.",false);
		}
		
		if(countryId!=null && cityId!=null) {
			return new ResponseWrapper<ArrayList<BookingUnitDTO>>(null,"Ne smete uneti grad i drzavu za pretragu.",false);
		}
			
		Country country = null;
		City city = null;
		
		Page<MonthlyPrices> monthlyPrices = null;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateFromDate = null;;
		try {
			dateFromDate = dateFormat.parse(dateFrom);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date dateToDate = null;
		try {
			dateToDate = dateFormat.parse(dateTo);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(countryId!=null) {
			country = countryService.getOne(countryId);
			if(country==null) {
				return new ResponseWrapper<ArrayList<BookingUnitDTO>>(null,"Drzava koju ste uneli ne postoji.",false);
			}
			monthlyPrices = bookingUnitService.findBookingUnitsByCountry(country, peopleNumber, new PageRequest(page-1,10));
		}else if(cityId!=null) {
			city = cityService.getOne(cityId);
			if(city==null) {
				return new ResponseWrapper<ArrayList<BookingUnitDTO>>(null,"Grad koji ste uneli ne postoji.",false);
			}
			monthlyPrices = bookingUnitService.findBookingUnitsByCity(city, peopleNumber, new PageRequest(page-1,10));
		}
		
		ArrayList<BookingUnitDTO> retVal = new ArrayList<BookingUnitDTO>();
		
		for(MonthlyPrices monthlyPrice : monthlyPrices.getContent()) {
			boolean reserved = false;		
			/*for(Reservation reservation : bookingUnit.getReservations()) {
				boolean expression1 = dateFromDate.getTime()>=reservation.getFromDate().getTime() && dateToDate.getTime()<=reservation.getToDate().getTime();
				boolean expression2 = dateFromDate.getTime()<=reservation.getFromDate().getTime() && dateToDate.getTime()>=reservation.getFromDate().getTime();
				boolean expression3 = dateFromDate.getTime()<=reservation.getToDate().getTime() && dateToDate.getTime() >= reservation.getToDate().getTime();
				if(expression1 || expression2 || expression3) {
					reserved = true;
					break;
				}		
			}
			*/
			retVal.add(new BookingUnitDTO(monthlyPrice.getBookingUnit(),reserved,monthlyPrice.getAmount(),null));		
		}
		
		return new ResponseWrapper<ArrayList<BookingUnitDTO>>(retVal,"Uspesno vracene smestajne jedinice.",true);
	}
	
	@RequestMapping(value="getCountriesAndCitiesSearch",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ArrayList<CityCountryDTO>> getCountriesAndCitiesSearch(@RequestParam(value="name", required=true) String name){
		
		int number = 5;
		
		Page<City> cities = cityService.findByNameLike(name, new PageRequest(0,number));
		
		Page<Country> countries = countryService.findByNameLike(name, new PageRequest(0,number));
		
		ArrayList<CityCountryDTO> retVal = new ArrayList<CityCountryDTO>();
		
		for(City city : cities.getContent()) {
			retVal.add(new CityCountryDTO(city,null));
		}
		
		int stop = number - retVal.size(); 
				
		for(Country country : countries.getContent()) {
			if(stop==0)
				break;
			
			retVal.add(new CityCountryDTO(null,country));
			
			stop--;		
		}
		System.out.println("sagggggggg");
		return new ResponseWrapper<ArrayList<CityCountryDTO>>(retVal,"Uspesno vracena lista gradova i drzava.", true);
	}
	
}
