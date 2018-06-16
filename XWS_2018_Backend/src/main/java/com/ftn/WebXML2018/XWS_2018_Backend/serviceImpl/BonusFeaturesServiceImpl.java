package com.ftn.WebXML2018.XWS_2018_Backend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BonusFeatures;
import com.ftn.WebXML2018.XWS_2018_Backend.repository.BonusFeaturesRepository;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BonusFeaturesService;

@Service
public class BonusFeaturesServiceImpl implements BonusFeaturesService{

	@Autowired
	private BonusFeaturesRepository repository;
	
	@Override
	public BonusFeatures save(BonusFeatures bonusFeatures) {
		return repository.save(bonusFeatures);
	}

	@Override
	public BonusFeatures getByName(String name) {
		return repository.getByName(name);
	}

	@Override
	public BonusFeatures updateAttribute(BonusFeatures attribute) {
		BonusFeatures bf = repository.getOne(attribute.getId());
		
		bf.setName(attribute.getName());
		
		return repository.save(bf);
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
	public BonusFeatures getById(Long id) {
		return repository.findOne(id);
	}

}
