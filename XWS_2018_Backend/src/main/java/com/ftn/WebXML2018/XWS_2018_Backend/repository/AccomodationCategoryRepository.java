package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType;

public interface AccomodationCategoryRepository extends JpaRepository<AccomodationCategory, Long> {
	
	public AccomodationCategory getByName(String name);
}
