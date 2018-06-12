package com.ftn.WebXML2018.XWS_2018_Backend.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class BookingUnit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	@Size(max = 90)
	private String name;
	
	@Column(nullable = false)
	@Size(max = 90)
	private String address;
	
	@ManyToOne(optional = false)
	private City city;
	
	@Column(nullable = true)
	@Size(max = 1000)
	private String description;
	
	@Column(nullable = false)
	@Min(1)
	private int peopleNumber;
	
	@ManyToOne(optional = false)
	private AgentUser agent;

	@ManyToOne(optional = false)
	private AccomodationType accomodationType;
	
	@ManyToOne(optional = false)
	private AccomodationCategory accomodationCategory;
	
	@OneToMany(mappedBy = "bookingUnit", fetch = FetchType.EAGER)
	private Set<BookingUnitPicture> pictures;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<BonusFeatures> bonusFeatures;
	
	@OneToMany(mappedBy="bookingUnit", fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<Reservation> reservations;
	
	public BookingUnit() {
		
	}

	public BookingUnit(Long id, String name, String address, String dascription, int peopleNumber, City city, AgentUser agent,
			Set<BonusFeatures> bonusFeatures, AccomodationType accomodationType, AccomodationCategory accomodationCategory,
			Set<BookingUnitPicture> pictures) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = dascription;
		this.peopleNumber = peopleNumber;
		this.city = city;
		this.agent = agent;
		this.bonusFeatures = bonusFeatures;
		this.accomodationType = accomodationType;
		this.accomodationCategory = accomodationCategory;
		this.pictures = pictures;
	}
	
	public BookingUnit(String address, String name, String dascription, int peopleNumber, City city, AgentUser agent,
			Set<BonusFeatures> bonusFeatures, AccomodationType accomodationType, AccomodationCategory accomodationCategory,
			Set<BookingUnitPicture> pictures) {
		super();
		this.address = address;
		this.name = name;
		this.description = dascription;
		this.peopleNumber = peopleNumber;
		this.city = city;
		this.agent = agent;
		this.bonusFeatures = bonusFeatures;
		this.accomodationType = accomodationType;
		this.accomodationCategory = accomodationCategory;
		this.pictures = pictures;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String dascription) {
		this.description = dascription;
	}

	public int getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public AgentUser getAgent() {
		return agent;
	}

	public void setAgent(AgentUser agent) {
		this.agent = agent;
	}

	public AccomodationType getAccomodationType() {
		return accomodationType;
	}

	public void setAccomodationType(AccomodationType accomodationType) {
		this.accomodationType = accomodationType;
	}

	public AccomodationCategory getAccomodationCategory() {
		return accomodationCategory;
	}

	public void setAccomodationCategory(AccomodationCategory accomodationCategory) {
		this.accomodationCategory = accomodationCategory;
	}

	public Set<BookingUnitPicture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<BookingUnitPicture> pictures) {
		this.pictures = pictures;
	}

	public Set<BonusFeatures> getBonusFeatures() {
		return bonusFeatures;
	}

	public void setBonusFeatures(Set<BonusFeatures> bonusFeatures) {
		this.bonusFeatures = bonusFeatures;
	}
	
	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BookingUnit [id=" + id + ", name=" + name + ", address=" + address + ", city=" + city + ", description="
				+ description + ", peopleNumber=" + peopleNumber + ", agent=" + agent + ", accomodationType="
				+ accomodationType + ", accomodationCategory=" + accomodationCategory + ", pictures=" + pictures
				+ ", bonusFeatures=" + bonusFeatures + ", reservations=" + reservations + "]";
	}
	
}
