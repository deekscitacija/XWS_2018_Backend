package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class BonusFeatures {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Size(max = 60)
	private String name;
	
	public BonusFeatures() {
		
	}

	public BonusFeatures(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public BonusFeatures(String name) {
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

	@Override
	public String toString() {
		return "BonusFeatures [id=" + id + ", name=" + name + "]";
	}
}
