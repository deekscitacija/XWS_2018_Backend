package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.AccomodationCategory;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.BonusFeatures;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.BonusFeaturesService;

@RestController
@RequestMapping("/bonusFeatures")
public class BonusFeaturesController {
	
	@Autowired
	private BonusFeaturesService service;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/new")
	public ResponseEntity<?> createNewAccomodation(@RequestBody BonusFeatures bonus) {
		ResponseWrapper<BonusFeatures> ret = null;
		String message = "Success!";
		
		try {
			BonusFeatures newOne = service.save(bonus);
			
			if(newOne != null) {
				ret = new ResponseWrapper<BonusFeatures>(newOne, message, true);
				return ResponseEntity.ok(ret);
			} else {
				message = "No such feature";
				ret = new ResponseWrapper<BonusFeatures>(newOne, message, false);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
			message = "No such feature";
			ret = new ResponseWrapper<BonusFeatures>(null, message, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ret);
		}
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		ResponseWrapper<BonusFeatures> ret = null;
		String message = "Success!";
		
		try {
			BonusFeatures newOne = service.getById(id);
			
			if(newOne != null) {
				ret = new ResponseWrapper<BonusFeatures>(newOne, message, true);
				return ResponseEntity.ok(ret);
			} else {
				message = "No such feature";
				ret = new ResponseWrapper<BonusFeatures>(newOne, message, false);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
			message = "No such feature";
			ret = new ResponseWrapper<BonusFeatures>(null, message, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ret);
		}
	}
	
	@GetMapping("getByName/{name}")
	public ResponseEntity<?> getByName(@PathVariable("name") String name) {
		ResponseWrapper<BonusFeatures> ret = null;
		String message = "Success!";
		
		try {
			BonusFeatures newOne = service.getByName(name);
			
			if(newOne != null) {
				ret = new ResponseWrapper<BonusFeatures>(newOne, message, true);
				return ResponseEntity.ok(ret);
			} else {
				message = "No such feature";
				ret = new ResponseWrapper<BonusFeatures>(newOne, message, false);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
			message = "No such feature";
			ret = new ResponseWrapper<BonusFeatures>(null, message, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ret);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<BonusFeatures> ret = null;
		ResponseWrapper<List<BonusFeatures>> resp = null;
		String msg = "Success!";
		
		try {
			ret = service.getAll();
			resp = new ResponseWrapper<>(ret, msg, true);
			
			return ResponseEntity.ok(resp);
		} catch(Exception e) {
			resp = new ResponseWrapper<>(ret, msg, false);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody BonusFeatures bonus) {
		ResponseWrapper<BonusFeatures> ret = null;
		String message = "Success!";
		
		try {
			BonusFeatures newOne = service.updateAttribute(bonus);
			
			if(newOne != null) {
				ret = new ResponseWrapper<BonusFeatures>(newOne, message, true);
				return ResponseEntity.ok(ret);
			} else {
				message = "Error updating feature";
				ret = new ResponseWrapper<BonusFeatures>(newOne, message, false);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ret);
			}
		} catch(Exception e) {
			e.printStackTrace();
			message = "Error updating feature";
			ret = new ResponseWrapper<BonusFeatures>(null, message, false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ret);
		}
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
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
