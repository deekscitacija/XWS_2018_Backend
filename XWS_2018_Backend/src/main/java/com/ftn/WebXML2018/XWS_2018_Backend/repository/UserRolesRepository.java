package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.UserRoles;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.UserRolesType;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long>{

	public UserRoles getByName(UserRolesType role);
	
}
