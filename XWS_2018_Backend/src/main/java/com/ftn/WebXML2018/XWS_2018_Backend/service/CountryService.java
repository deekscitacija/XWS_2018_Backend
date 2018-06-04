package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.List;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;

public interface CountryService {

	public Country getOne(Integer id);
	
	public List<Country> getAll();
	
}
