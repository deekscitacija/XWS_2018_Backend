package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.UserRoles;
import com.ftn.WebXML2018.XWS_2018_Backend.enums.UserRolesType;

public interface UserRepository extends JpaRepository<User, Long>{

	public User getByUsername(String username);
	
	public List<User> findAllByIdIn(List<Long> idList);
	
	public List<User> findAllByUserRole(UserRoles userRole);
	
	public List<User> findAllByUserRoleAndIdIn(UserRoles userRole, List<Long> ids);
}
