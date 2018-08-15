package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class AccomodationCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Size(max = 60)
	private String name;

	@Column(nullable = false)
	private Integer strength;
	
	public AccomodationCategory() {
		
	}

	public AccomodationCategory(Long id, String name, Integer strength) {
		super();
		this.id = id;
		this.name = name;
		this.strength = strength;
	}
	
	public AccomodationCategory(String name) {
		super();
		this.name = name;
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

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	@Override
	public String toString() {
		return "AccomodationCategory [id=" + id + ", name=" + name + ", strength=" + strength + "]";
	}
}
