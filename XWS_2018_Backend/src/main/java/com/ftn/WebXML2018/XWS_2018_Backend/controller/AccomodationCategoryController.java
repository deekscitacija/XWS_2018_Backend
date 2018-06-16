package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationType;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AccomodationCategoryService;
import com.ftn.WebXML2018.XWS_2018_Backend.service.AccomodationTypeService;

@RestController
@RequestMapping("/accomodationCategory")
public class AccomodationCategoryController {

	@Autowired
	private AccomodationCategoryService service;
	
	
	@PostMapping("/new")
	public ResponseEntity<?> createNewAccomodation(@RequestBody AccomodationCategory ACategory) {
		ResponseWrapper<AccomodationCategory> ret = null;
		String message = "Success!";
		
		try {
			AccomodationCategory newOne = service.save(ACategory);
			
			if(newOne != null) {
				ret = new ResponseWrapper<AccomodationCategory>(newOne, message, true);
				return ResponseEntity.ok(ret);
			} else {
				message = "No such feature";
				ret = new ResponseWrapper<AccomodationCategory>(newOne, message, false);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
			message = "No such feature";
			ret = new ResponseWrapper<AccomodationCategory>(null, message, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ret);
		}
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		ResponseWrapper<AccomodationCategory> ret = null;
		String message = "Success!";
		
		try {
			AccomodationCategory newOne = service.getById(id);
			
			if(newOne != null) {
				ret = new ResponseWrapper<AccomodationCategory>(newOne, message, true);
				return ResponseEntity.ok(ret);
			} else {
				message = "No such feature";
				ret = new ResponseWrapper<AccomodationCategory>(newOne, message, false);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
			message = "No such feature";
			ret = new ResponseWrapper<AccomodationCategory>(null, message, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ret);
		}
	}
	
	@GetMapping("getByName/{name}")
	public ResponseEntity<?> getByName(@PathVariable("name") String name) {
		ResponseWrapper<AccomodationCategory> ret = null;
		String message = "Success!";
		
		try {
			AccomodationCategory newOne = service.getByName(name);
			
			if(newOne != null) {
				ret = new ResponseWrapper<AccomodationCategory>(newOne, message, true);
				return ResponseEntity.ok(ret);
			} else {
				message = "No such feature";
				ret = new ResponseWrapper<AccomodationCategory>(newOne, message, false);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
			message = "No such feature";
			ret = new ResponseWrapper<AccomodationCategory>(null, message, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ret);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody AccomodationCategory ACType) {
		ResponseWrapper<AccomodationCategory> ret = null;
		String message = "Success!";
		
		try {
			AccomodationCategory newOne = service.updateAttribute(ACType);
			
			if(newOne != null) {
				ret = new ResponseWrapper<AccomodationCategory>(newOne, message, true);
				return ResponseEntity.ok(ret);
			} else {
				message = "Error updating feature";
				ret = new ResponseWrapper<AccomodationCategory>(newOne, message, false);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
			message = "Error updating feature";
			ret = new ResponseWrapper<AccomodationCategory>(null, message, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ret);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		ResponseWrapper<Boolean> ret = null;
		String message = "Success!";
		
		try {
			Boolean success = service.deleteAttribute(id);
			
			if(success) {
				ret = new ResponseWrapper<Boolean>(success, message, true);
				return ResponseEntity.ok(ret);
			} else {
				message = "Error deleting feature";
				ret = new ResponseWrapper<Boolean>(success, message, false);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
			message = "Error deleting feature";
			ret = new ResponseWrapper<Boolean>(false, message, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ret);
		}
	}
}
