package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Entity
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Size(max = 90)
	private String name;
	
	@Column(nullable = false)
	@Size(max = 30)
	private String postalCode;
	
	@ManyToOne(optional=false)
	private Country country;
	
	public City() {
		
	}

	public City(Long id, String name, String postalCode, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.postalCode = postalCode;
		this.country = country;
	}

	public City(String name, String postalCode, Country country) {
		super();
		this.name = name;
		this.postalCode = postalCode;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
