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

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AccomodationTypeService;

@RestController
@RequestMapping("/accomodationType")
public class AccomodationTypeController {

	@Autowired
	private AccomodationTypeService service;
	
	
	@PostMapping("/new")
	public ResponseEntity<?> createNewAccomodation(@RequestBody AccomodationType ACtype) {
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
	public ResponseEntity<?> update(@RequestBody AccomodationType ACType) {
		return null;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		return null;
	}
}
