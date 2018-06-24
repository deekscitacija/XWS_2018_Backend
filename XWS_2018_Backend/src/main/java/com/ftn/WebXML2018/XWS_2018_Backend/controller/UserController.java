package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.UserMiniDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/getByActive/{active}")
	public ResponseEntity<?> getUserMiniDTOsByActive(@PathVariable("active") Boolean active) {
		ResponseWrapper<List<UserMiniDTO>> wrapper = null;
		List<User> users = null;
		String msg = "";
		
		try {
			if(active) {
				users = userService.getActivatedUsers();
			} else {
				users = userService.getDisabledUsers();
			}
			
			List<UserMiniDTO> usersDTO = new ArrayList<UserMiniDTO>();
			
			for(User u : users) {
				usersDTO.add(userService.convertToDTO(u));
			}
			
			msg = "Success";
			wrapper = new ResponseWrapper<List<UserMiniDTO>>(usersDTO, msg, true);
			
			return ResponseEntity.ok(wrapper);
		} catch(Exception e) {
			e.printStackTrace();
			msg = "Error fetching users";
			wrapper = new ResponseWrapper<List<UserMiniDTO>>(null, msg, false);
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(wrapper);
		}
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAllRegistered() {
		ResponseWrapper<List<UserMiniDTO>> wrapper = null;
		List<User> users = null;
		String msg = "";
		
		try {
			users = userService.getAllRegistered();
			List<UserMiniDTO> usersDTO = new ArrayList<UserMiniDTO>();
			
			for(User u : users) {
				usersDTO.add(userService.convertToDTO(u));
			}
			
			msg = "Success";
			wrapper = new ResponseWrapper<List<UserMiniDTO>>(usersDTO, msg, true);
			
			return ResponseEntity.ok(wrapper);
		} catch(Exception e) {
			e.printStackTrace();
			msg = "Error fetching users";
			wrapper = new ResponseWrapper<List<UserMiniDTO>>(null, msg, false);
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(wrapper);
		}
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/activate/{id}")
	public ResponseEntity<?> activateUserMiniDTO(@PathVariable("id") Long id) {
		User u = userService.activateUser(id);
		ResponseWrapper<UserMiniDTO> resp;
		String msg = "Activation successfull!";
		
		if(u == null) {
			msg = "Error activating user.";
			resp = new ResponseWrapper<UserMiniDTO>(userService.convertToDTO(u), msg, false);
		} else {
			resp = new ResponseWrapper<UserMiniDTO>(userService.convertToDTO(u), msg, true);
		}
		
		return ResponseEntity.ok(resp);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@PutMapping("/block/{id}")
	public ResponseEntity<?> blockUserMiniDTO(@PathVariable("id")Long id) {
		User u = userService.blockUser(id);
		ResponseWrapper<UserMiniDTO> resp;
		String msg = "Blocking successfull!";
		
		if(u == null) {
			msg = "Error blocking user.";
			resp = new ResponseWrapper<UserMiniDTO>(userService.convertToDTO(u), msg, false);
		} else {
			resp = new ResponseWrapper<UserMiniDTO>(userService.convertToDTO(u), msg, true);
		}
		
		return ResponseEntity.ok(resp);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserMiniDTO(@PathVariable("id")Long id) {
		User u = userService.deleteUser(id);
		ResponseWrapper<UserMiniDTO> resp;
		String msg = "Deletion successfull!";
		
		if(u == null) {
			msg = "Error deleting user.";
			resp = new ResponseWrapper<UserMiniDTO>(userService.convertToDTO(u), msg, false);
		} else {
			resp = new ResponseWrapper<UserMiniDTO>(userService.convertToDTO(u), msg, true);
		}
		
		return ResponseEntity.ok(resp);
//		User u = userService.blockUser(id);
//		ResponseWrapper<UserMiniDTO> resp;
//		String msg = "Deleting successfull!";
//		
//		if(u == null) {
//			msg = "Error deleting user.";
//			resp = new ResponseWrapper<UserMiniDTO>(userService.convertToDTO(u), msg, false);
//		} else {
//			resp = new ResponseWrapper<UserMiniDTO>(userService.convertToDTO(u), msg, true);
//		}
//		
//		return ResponseEntity.ok(resp);
	}
}
