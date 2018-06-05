package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.CountryRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public Country getOne(Long id) {
		
		return countryRepository.findOne(id);
	}

	@Override
	public List<Country> getAll() {
		
		return countryRepository.findAll();
	}
	
	
	
}
