package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.List;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.AttributesType;

public interface AccomodationCategoryService {

	public AccomodationCategory save(AccomodationCategory ac);
	
	public AccomodationCategory getByName(String name);
	
	public AccomodationCategory getById(Long id);
	
	public List<AccomodationCategory> getAll();
	
	public AccomodationCategory updateAttribute(AccomodationCategory ac);
	
	public boolean deleteAttribute(Long id);
}
