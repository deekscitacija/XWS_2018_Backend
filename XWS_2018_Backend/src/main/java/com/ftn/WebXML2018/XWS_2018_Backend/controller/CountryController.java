package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.CountryService;

@RestController
@RequestMapping(value = "rest/")
public class CountryController {
	
	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value = "getAllCountries", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseWrapper<List<Country>> testMethod(){
	
		return new ResponseWrapper<List<Country>>(countryService.getAll(), true);
	}

}
