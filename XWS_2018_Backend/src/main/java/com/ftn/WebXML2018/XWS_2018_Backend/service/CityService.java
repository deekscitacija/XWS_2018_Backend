package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;

public interface CityService {
	
	public City getOne(Long id);
	
	public City saveCity(City city);
	
	public List<City> getAll();
	
	public void deleteCity(City city);
	
	public List<City> getCitiesByCountry(Country country);
	
	public Page<City> findByNameLike(String name, Pageable pageable);
	
}
