package com.ftn.WebXML2018.XWS_2018_Backend.dto;

import java.util.List;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BonusFeatures;

public class AdvancedSearchWrapper {

	private List<AccomodationType> selectedAccomodationTypes;
	private List<AccomodationCategory> selectedAccomodationCategories;
	private List<BonusFeatures> selectedBonusFeatures;
	
	public AdvancedSearchWrapper() {}

	public AdvancedSearchWrapper(List<AccomodationType> selectedAccomodationTypes,
			List<AccomodationCategory> selectedAccomodationCategories, List<BonusFeatures> selectedBonusFeatures) {
		super();
		this.selectedAccomodationTypes = selectedAccomodationTypes;
		this.selectedAccomodationCategories = selectedAccomodationCategories;
		this.selectedBonusFeatures = selectedBonusFeatures;
	}

	public List<AccomodationType> getSelectedAccomodationTypes() {
		return selectedAccomodationTypes;
	}

	public void setSelectedAccomodationTypes(List<AccomodationType> selectedAccomodationTypes) {
		this.selectedAccomodationTypes = selectedAccomodationTypes;
	}

	public List<AccomodationCategory> getSelectedAccomodationCategories() {
		return selectedAccomodationCategories;
	}

	public void setSelectedAccomodationCategories(List<AccomodationCategory> selectedAccomodationCategories) {
		this.selectedAccomodationCategories = selectedAccomodationCategories;
	}

	public List<BonusFeatures> getSelectedBonusFeatures() {
		return selectedBonusFeatures;
	}

	public void setSelectedBonusFeatures(List<BonusFeatures> selectedBonusFeatures) {
		this.selectedBonusFeatures = selectedBonusFeatures;
	}

	@Override
	public String toString() {
		return "AdvancedSearchWrapper [selectedAccomodationTypes=" + selectedAccomodationTypes
				+ ", selectedAccomodationCategories=" + selectedAccomodationCategories + ", selectedBonusFeatures="
				+ selectedBonusFeatures + "]";
	}
}
