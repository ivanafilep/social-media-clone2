package com.example.demo.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.RegularUser;

@Qualifier("ReguserService")
public interface RegUserService {

	ResponseEntity<UserDTO> createRegularUser(UserDTO newUser, Authentication authentication);


}
