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
	
	@Column(nullable = true)
	@Size(max = 90)
	private String postcode;
	
	@ManyToOne(optional=false)
	private Country country;
	
	public City() {
		
	}

	public City(Long id, String name, Country country, String postcode) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.postcode = postcode;
	}

	public City(String name, Country country, String postcode) {
		super();
		this.name = name;
		this.country = country;
		this.postcode = postcode;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", postcode=" + postcode + ", country=" + country + "]";
	}
}
