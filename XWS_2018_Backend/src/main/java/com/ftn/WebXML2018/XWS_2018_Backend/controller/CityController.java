package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.service.CityService;

@RestController
@RequestMapping(value = "rest/")
public class CityController {
	
	@Autowired
	private CityService cityService;

}
