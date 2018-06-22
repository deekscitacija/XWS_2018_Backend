package com.ftn.WebXML2018.XWS_2018_Backend.dto;

import java.util.Date;

public class PriceDTO {
	
	private Long unitId;
	private String fromDate;
	private String toDate;
	private Date currentDate;
	
	public PriceDTO() {
		
	}

	public PriceDTO(Long unitId, String fromDate, String toDate, Date currentDate) {
		super();
		this.unitId = unitId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.currentDate = currentDate;
	}
	
	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}
	

}
