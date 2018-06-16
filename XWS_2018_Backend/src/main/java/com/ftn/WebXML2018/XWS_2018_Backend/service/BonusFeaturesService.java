package com.ftn.WebXML2018.XWS_2018_Backend.service;

import java.util.List;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BonusFeatures;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.AttributesType;

public interface BonusFeaturesService {

	public BonusFeatures save(BonusFeatures bonusFeatures);
	
	public BonusFeatures getByName(String name);
	
	public BonusFeatures updateAttribute(BonusFeatures attribute);
	
	public boolean deleteAttribute(Long id);
}
