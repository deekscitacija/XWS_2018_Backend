package com.ftn.WebXML2018.XWS_2018_Backend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.WebXML2018.XWS_2018_Backend.dto.UserDTO;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper.ResponseWrapper;
import com.ftn.WebXML2018.XWS_2018_Backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/getByActive/{active}")
	public ResponseEntity<?> getUserDTOsByActive(@PathVariable("active") Boolean active) {
		ResponseWrapper<List<UserDTO>> wrapper = null;
		List<User> users = null;
		String msg = "";
		
		try {
			if(active) {
				users = userService.getActivatedUsers();
			} else {
				users = userService.getDisabledUsers();
			}
			
			List<UserDTO> usersDTO = new ArrayList<UserDTO>();
			
			for(User u : users) {
				usersDTO.add(userService.convertToDTO(u));
			}
			
			msg = "Success";
			wrapper = new ResponseWrapper<List<UserDTO>>(usersDTO, msg, true);
			
			return ResponseEntity.ok(wrapper);
		} catch(Exception e) {
			e.printStackTrace();
			msg = "Error fetching users";
			wrapper = new ResponseWrapper<List<UserDTO>>(null, msg, false);
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(wrapper);
		}
	}
	
	@PutMapping("/activate/{id}")
	public ResponseEntity<?> activateUserDTO(@PathVariable("id") Long id) {
		User u = userService.activateUser(id);
		ResponseWrapper<UserDTO> resp;
		String msg = "Activation successfull!";
		
		if(u == null) {
			msg = "Error activating user.";
			resp = new ResponseWrapper<UserDTO>(userService.convertToDTO(u), msg, false);
		} else {
			resp = new ResponseWrapper<UserDTO>(userService.convertToDTO(u), msg, true);
		}
		
		return ResponseEntity.ok(resp);
	}
	
	@PutMapping("/block/{id}")
	public ResponseEntity<?> blockUserDTO(@PathVariable("id")Long id) {
		User u = userService.blockUser(id);
		ResponseWrapper<UserDTO> resp;
		String msg = "Blocking successfull!";
		
		if(u == null) {
			msg = "Error blocking user.";
			resp = new ResponseWrapper<UserDTO>(userService.convertToDTO(u), msg, false);
		} else {
			resp = new ResponseWrapper<UserDTO>(userService.convertToDTO(u), msg, true);
		}
		
		return ResponseEntity.ok(resp);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUserDTO(@PathVariable("id")Long id) {
		User u = userService.deleteUser(id);
		ResponseWrapper<UserDTO> resp;
		String msg = "Deletion successfull!";
		
		if(u == null) {
			msg = "Error deleting user.";
			resp = new ResponseWrapper<UserDTO>(userService.convertToDTO(u), msg, false);
		} else {
			resp = new ResponseWrapper<UserDTO>(userService.convertToDTO(u), msg, true);
		}
		
		return ResponseEntity.ok(resp);
	}
}
