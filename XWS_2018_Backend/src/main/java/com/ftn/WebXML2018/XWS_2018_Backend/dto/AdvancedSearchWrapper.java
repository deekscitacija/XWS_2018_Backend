package com.ftn.WebXML2018.XWS_2018_Backend.dto;

import java.util.List;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BonusFeatures;

public class AdvancedSearchWrapper {

	private List<AccomodationType> accomodationTypes;
	private List<AccomodationCategory> accomodationCategories;
	private List<BonusFeatures> bonusFeatures;
	
	public AdvancedSearchWrapper() {}
	
	public AdvancedSearchWrapper(List<AccomodationType> accomodationTypes,
			List<AccomodationCategory> accomodationCategories, List<BonusFeatures> bonusFeatures) {
		super();
		this.accomodationTypes = accomodationTypes;
		this.accomodationCategories = accomodationCategories;
		this.bonusFeatures = bonusFeatures;
	}



	public List<AccomodationType> getAccomodationTypes() {
		return accomodationTypes;
	}

	public void setAccomodationTypes(List<AccomodationType> accomodationTypes) {
		this.accomodationTypes = accomodationTypes;
	}

	public List<AccomodationCategory> getAccomodationCategories() {
		return accomodationCategories;
	}

	public void setAccomodationCategories(List<AccomodationCategory> accomodationCategories) {
		this.accomodationCategories = accomodationCategories;
	}

	public List<BonusFeatures> getBonusFeatures() {
		return bonusFeatures;
	}

	public void setBonusFeatures(List<BonusFeatures> bonusFeatures) {
		this.bonusFeatures = bonusFeatures;
	}

}
