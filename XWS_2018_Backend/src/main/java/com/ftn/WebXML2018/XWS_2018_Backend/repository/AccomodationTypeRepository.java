package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.AttributesType;

public interface AccomodationTypeRepository extends JpaRepository<AccomodationType, Long> {
	
	public AccomodationType getByName(String name);
	
}
