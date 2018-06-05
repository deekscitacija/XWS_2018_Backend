package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.CityService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.CountryService;

@RestController
@RequestMapping(value = "/rest/")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value = "getAllCities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseWrapper<List<City>> testMethod(){
	
		return new ResponseWrapper<List<City>>(cityService.getAll(), true);
	}
	
	@RequestMapping(value = "getCitiesByCountry", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseWrapper<List<City>> testMethod(@RequestParam String id, HttpServletResponse response){
		
		Long idVal = null;
		
		try {
			idVal = Long.parseLong(id);
		}catch(NumberFormatException e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseWrapper<List<City>>(null,"Nemoguce pronaci drzavu.", false);
		}
		
		Country country = countryService.getOne(idVal);
		
		if(country == null) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return new ResponseWrapper<List<City>>(null,"Nepostojeca drzava.", false);
		}
	
		response.setStatus(HttpStatus.OK.value());
		return new ResponseWrapper<List<City>>(cityService.getCitiesByCountry(country), true);
	}

}
