package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.BonusFeatures;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BonusFeaturesService;

@RestController
@RequestMapping("/bonusFeatures")
public class BonusFeaturesController {
	
	@Autowired
	private BonusFeaturesService service;
	
	
	@PostMapping("/new")
	public ResponseEntity<?> createNewAccomodation(@RequestBody BonusFeatures bonus) {
		return null;
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		return null;
	}
	
	@GetMapping("getByName/{name}")
	public ResponseEntity<?> getByName(@PathVariable("name") String name) {
		return null;
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody BonusFeatures bonus) {
		return null;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return null;
	}	
}
