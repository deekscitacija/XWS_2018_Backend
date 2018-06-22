package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.AdvancedSearchWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.BookingUnitDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.dto.CityCountryDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BonusFeatures;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AccomodationCategoryService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AccomodationTypeService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BonusFeaturesService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BookingUnitService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.CityService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.CountryService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.MonthlyPricesService;


@RestController
@RequestMapping(value = "/rest/")
public class SearchController {

	@Autowired
	private BookingUnitService bookingUnitService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private AccomodationTypeService accomodationTypeService;
	
	@Autowired
	private AccomodationCategoryService accomodationCategoryService;
	
	@Autowired
	private BonusFeaturesService bonusFeaturesService;
	
	@Autowired
	private MonthlyPricesService monthlyPricesService;
	
	@RequestMapping(value="getBookingUnits/page={page}&num={num}", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<Page<BookingUnitDTO>> getBookingUnits(@PathVariable int page, @PathVariable int num, @RequestParam(value = "peopleNumber", required = true) int peopleNumber,
															  @RequestParam(value="dateFrom", required = true) String dateFrom, 
															  @RequestParam(value="dateTo", required = true) String dateTo,
															  @RequestParam(value="country", required = false) Long countryId,
															  @RequestParam(value="city", required = false) Long cityId,
															  @RequestBody AdvancedSearchWrapper advancedSearchWrapper){
		
		if(countryId==null && cityId==null) {
			return new ResponseWrapper<Page<BookingUnitDTO>>(null,"Morate uneti ili grad ili drzavu za pretragu.",false);
		}
		
		if(countryId!=null && cityId!=null) {
			return new ResponseWrapper<Page<BookingUnitDTO>>(null,"Ne smete uneti grad i drzavu za pretragu.",false);
		}
			
		Country country = null;
		City city = null;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateFromDate = null;;
		try {
			dateFromDate = dateFormat.parse(dateFrom);
		} catch (ParseException e) {
			return new ResponseWrapper<Page<BookingUnitDTO>>(null,"Neuspesno vracene smestajne jedinice.",false);
		}
		Date dateToDate = null;
		try {
			dateToDate = dateFormat.parse(dateTo);
		} catch (ParseException e) {
			return new ResponseWrapper<Page<BookingUnitDTO>>(null,"Neuspesno vracene smestajne jedinice.",false);
		}
		
		if(countryId!=null) {
			country = countryService.getOne(countryId);
			if(country==null) {
				return new ResponseWrapper<Page<BookingUnitDTO>>(null,"Drzava koju ste uneli ne postoji.",false);
			}
		}else if(cityId!=null) {
			city = cityService.getOne(cityId);
			if(city==null) {
				return new ResponseWrapper<Page<BookingUnitDTO>>(null,"Grad koji ste uneli ne postoji.",false);
			}
		}	
		
		Page<BookingUnitDTO> bookingUnits = bookingUnitService.findBookingUnits(city, country, peopleNumber, dateFromDate, dateToDate, advancedSearchWrapper.getSelectedAccomodationTypes(), advancedSearchWrapper.getSelectedAccomodationCategories(), advancedSearchWrapper.getSelectedBonusFeatures(),  new PageRequest(page,num));
		
		if(bookingUnits == null) {
			return new ResponseWrapper<Page<BookingUnitDTO>>(null,"Neuspesno vracene smestajne jedinice.",false);
		}
		
		return new ResponseWrapper<Page<BookingUnitDTO>>(bookingUnits,"Uspesno vracene smestajne jedinice.",true);
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
		
		return new ResponseWrapper<ArrayList<CityCountryDTO>>(retVal,"Uspesno vracena lista gradova i drzava.", true);
	}
	
	@RequestMapping(value="getAllAccomodationTypes",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ArrayList<AccomodationType>> getAllAccomodationTypes(){
		
		ArrayList<AccomodationType> accomodationTypes = (ArrayList<AccomodationType>) accomodationTypeService.getAll();
		
		if(accomodationTypes==null) {
			return new ResponseWrapper<ArrayList<AccomodationType>>(null,"Neuspesno vraceni tipovi smestaja.", false);
		}
		
		return new ResponseWrapper<ArrayList<AccomodationType>>(accomodationTypes,"Uspesno vraceni tipovi smestaja.", true);
	}
	
	@RequestMapping(value="getAllAccomodationCategories",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ArrayList<AccomodationCategory>> getAllAccomodationCategories(){
		
		ArrayList<AccomodationCategory> accomodationCategories = (ArrayList<AccomodationCategory>) accomodationCategoryService.getAll();
		
		if(accomodationCategories==null) {
			return new ResponseWrapper<ArrayList<AccomodationCategory>>(null,"Neuspesno vracene kategorije smestaja.", false);
		}
		
		return new ResponseWrapper<ArrayList<AccomodationCategory>>(accomodationCategories,"Uspesno vracene kategorije smestaja.", true);
	}
	
	@RequestMapping(value="getAllBonusFeatures",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<ArrayList<BonusFeatures>> getAllBonusFeatures(){
		
		ArrayList<BonusFeatures> bonusFeatures = (ArrayList<BonusFeatures>) bonusFeaturesService.getAll();
		
		if(bonusFeatures==null) {
			return new ResponseWrapper<ArrayList<BonusFeatures>>(null,"Neuspesno vracene dodatne usluge.", false);
		}
		
		return new ResponseWrapper<ArrayList<BonusFeatures>>(bonusFeatures,"Uspesno vracene dodatne usluge.", true);
	}
	
	@RequestMapping(value = "/getImage", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public void getImage(@RequestParam(required=true,value="path") String path, HttpServletResponse response) throws IOException {
		System.out.println("gasg");
        ClassPathResource imgFile = new ClassPathResource("images/"+path);

        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
        
    }
   
	@RequestMapping(value= "/getBookingUnit", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseWrapper<BookingUnitDTO> getBookingUnit(@RequestParam(value="bookingUnitId", required = true) Long id){
		
		
		BookingUnitDTO bookingUnitDTO = monthlyPricesService.findByBookingUnitId(id);
		
		if(bookingUnitDTO==null) {
			return new ResponseWrapper<BookingUnitDTO>(null,"Neuspesno vracena smestajna jedinica.",false);
		}
		
		return new ResponseWrapper<BookingUnitDTO>(bookingUnitDTO,"Uspesno vracena smestajna jedinica.",true);
	}
	
	
}
