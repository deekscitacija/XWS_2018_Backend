package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

	public Page<Country> findByNameLikeIgnoreCase(String name, Pageable pageable);
}
