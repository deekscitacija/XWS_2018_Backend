package com.ftn.WebXML2018.XWS_2018_Backend.agentDto;

import java.util.ArrayList;
import java.util.HashMap;

public class BookingUnit_DTO {

	private String name;

	private Long cityMainServerId;

	private String address;

	private String description;

	private int peopleNo;

	private Long agentMainServerId;

	private Long accomodationTypeMainServerId;

	private Long accomodationCategoryMainServerId;

	private ArrayList<Long> bonusFeaturesMainServerIds;

	private HashMap<String,String> base64ImagesList;

	public BookingUnit_DTO(String name, Long cityMainServerId, String address, String description, int peopleNo,
			Long agentMainServerId, Long accomodationTypeMainServerId, Long accomodationCategoryMainServerId,
			ArrayList<Long> bonusFeaturesMainServerIds, HashMap<String,String> base64ImagesList) {
		super();
		this.name = name;
		this.cityMainServerId = cityMainServerId;
		this.address = address;
		this.description = description;
		this.peopleNo = peopleNo;
		this.agentMainServerId = agentMainServerId;
		this.accomodationTypeMainServerId = accomodationTypeMainServerId;
		this.accomodationCategoryMainServerId = accomodationCategoryMainServerId;
		this.bonusFeaturesMainServerIds = bonusFeaturesMainServerIds;
		this.base64ImagesList = base64ImagesList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCityMainServerId() {
		return cityMainServerId;
	}

	public void setCityMainServerId(Long cityMainServerId) {
		this.cityMainServerId = cityMainServerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPeopleNo() {
		return peopleNo;
	}

	public void setPeopleNo(int peopleNo) {
		this.peopleNo = peopleNo;
	}

	public Long getAgentMainServerId() {
		return agentMainServerId;
	}

	public void setAgentMainServerId(Long agentMainServerId) {
		this.agentMainServerId = agentMainServerId;
	}

	public Long getAccomodationTypeMainServerId() {
		return accomodationTypeMainServerId;
	}

	public void setAccomodationTypeMainServerId(Long accomodationTypeMainServerId) {
		this.accomodationTypeMainServerId = accomodationTypeMainServerId;
	}

	public Long getAccomodationCategoryMainServerId() {
		return accomodationCategoryMainServerId;
	}

	public void setAccomodationCategoryMainServerId(Long accomodationCategoryMainServerId) {
		this.accomodationCategoryMainServerId = accomodationCategoryMainServerId;
	}

	public ArrayList<Long> getBonusFeaturesMainServerIds() {
		return bonusFeaturesMainServerIds;
	}

	public void setBonusFeaturesMainServerIds(ArrayList<Long> bonusFeaturesMainServerIds) {
		this.bonusFeaturesMainServerIds = bonusFeaturesMainServerIds;
	}

	public HashMap<String,String> getBase64ImagesList() {
		return base64ImagesList;
	}

	public void setBase64ImagesList(HashMap<String,String> base64ImagesList) {
		this.base64ImagesList = base64ImagesList;
	}
}
