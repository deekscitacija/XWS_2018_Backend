package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.AttributesType;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.AccomodationCategoryRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AccomodationCategoryService;

@Service
public class AccomodationCategoryServiceImpl implements AccomodationCategoryService {

	@Autowired
	private AccomodationCategoryRepository repository;
	
	@Override
	public AccomodationCategory save(AccomodationCategory ac) {
		return repository.save(ac);
	}

	@Override
	public AccomodationCategory getByName(String name) {
		return repository.getByName(name);
	}

	@Override
	public AccomodationCategory updateAttribute(AccomodationCategory attribute) {
		AccomodationCategory acat = repository.findOne(attribute.getId());
		
		acat.setName(attribute.getName());
		
		return repository.save(acat);
	}

	@Override
	public boolean deleteAttribute(Long id) {
		boolean success = true;
		
		try {
			repository.delete(id);
		} catch(Exception e) {
			success = false;
		}
		
		return success;
	}

	@Override
	public AccomodationCategory getById(Long id) {
		return repository.findOne(id);
	}
}
