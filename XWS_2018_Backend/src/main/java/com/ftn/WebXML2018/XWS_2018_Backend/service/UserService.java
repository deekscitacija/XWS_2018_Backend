package com.ftn.WebXML2018.XWS_2018_Backend.service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;

public interface UserService {
	
	public User saveUser(User user);
	
	public User getUser(Integer id);

}
