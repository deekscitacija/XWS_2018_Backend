package com.ftn.WebXML2018.XWS_2018_Backend.dto;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.City;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;

public class CityCountryDTO {

	private City city;
	private Country country;
	
	public CityCountryDTO() {}
	
	public CityCountryDTO(City city, Country country) {
		super();
		this.city = city;
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "CityCountryDTO [city=" + city + ", country=" + country + "]";
	}
}
