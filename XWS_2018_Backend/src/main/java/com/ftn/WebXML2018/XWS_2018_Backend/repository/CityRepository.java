package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;

public interface CityRepository extends JpaRepository<City, Long>{

	List<City> findByCountry(Country country);
	
	List<City> findByCountryAndNameAndPostcode(Country country, String name, String postcode);
	
	public Page<City> findByNameLikeIgnoreCase(String name, Pageable pageable);
}
