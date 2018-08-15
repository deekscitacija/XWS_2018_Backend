package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;

public interface AccomodationCategoryRepository extends JpaRepository<AccomodationCategory, Long> {
	
	public AccomodationCategory getByName(String name);
	public List<AccomodationCategory> findByIdIn(List<Long> accomodationCategoryIds);
}
