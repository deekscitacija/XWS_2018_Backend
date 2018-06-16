package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.List;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.AttributesType;

public interface AccomodationTypeService {
	
	public AccomodationType save(AccomodationType at);
	
	public AccomodationType getByName(String name);
	
	public AccomodationType getById(Long id);
	
	public AccomodationType updateAttribute(AccomodationType at);
	
	public boolean deleteAttribute(Long id);
}
