package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.AccomodationTypeRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AccomodationTypeService;

@Service
public class AccomodationTypeServiceImpl implements AccomodationTypeService {

	@Autowired
	private AccomodationTypeRepository repository;
	
	@Override
	public AccomodationType save(AccomodationType at) {
		return repository.save(at);
	}

	@Override
	public AccomodationType getByName(String name) {
		return repository.getByName(name);
	}

	@Override
	public AccomodationType updateAttribute(AccomodationType at) {
		AccomodationType act = repository.getOne(at.getId());
		
		act.setName(at.getName());
		
		return repository.save(act);
	}

	@Override
	public boolean deleteAttribute(Long id) {
		boolean success = true;
		
		try {
			repository.delete(id);
		}catch(Exception e) {
			success = false;
		}
		
		return success;
	}

	@Override
	public AccomodationType getById(Long id) {
		return repository.findOne(id);
	}
}
