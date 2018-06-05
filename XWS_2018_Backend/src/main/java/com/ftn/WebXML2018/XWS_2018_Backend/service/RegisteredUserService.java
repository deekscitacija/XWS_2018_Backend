package com.ftn.WebXML2018.XWS_2018_Backend.service;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.RegisteredUser;

public interface RegisteredUserService {
	
	public RegisteredUser saveRegUser(RegisteredUser user);
	
	public RegisteredUser getRegUser(Long id);

}
