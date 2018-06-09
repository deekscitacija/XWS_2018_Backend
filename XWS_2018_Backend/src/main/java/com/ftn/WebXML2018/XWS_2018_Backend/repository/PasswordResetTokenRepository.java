package com.ftn.WebXML2018.XWS_2018_Backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.WebXML2018.XWS_2018_Backend.entity.PasswordResetToken;
import com.ftn.WebXML2018.XWS_2018_Backend.entity.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{

	List<PasswordResetToken> getByUserOrderByIdDesc(User user);
	
}
