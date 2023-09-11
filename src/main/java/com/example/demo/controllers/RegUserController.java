package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.services.RegUserService;
import com.example.demo.services.RegUserServiceImpl;

@RestController
@RequestMapping(path = "project/regularuser")
public class RegUserController {
	
	@Autowired 
	private RegUserService regUserService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createRegularUser(@Valid @RequestBody UserDTO newUser, Authentication authentication) {
		return regUserService.createRegularUser(newUser, authentication);
	}
	
	
	
	
	

}
