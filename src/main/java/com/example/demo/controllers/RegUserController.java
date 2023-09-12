package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.UserWithEmailExistsException;
import com.example.demo.exceptions.UserWithUsernameExistsException;
import com.example.demo.services.RegUserServiceImpl;

@RestController
@RequestMapping(path = "project/regularuser")
public class RegUserController {
	
	@Autowired 
	private RegUserServiceImpl regUserService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createRegularUser(@Valid @RequestBody UserDTO newUser) throws UserWithEmailExistsException, UserWithUsernameExistsException {
		try {
			return regUserService.createRegularUser(newUser);
		} catch (UserWithEmailExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (UserWithUsernameExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@Secured("ROLE_REGULAR_USER")
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	public ResponseEntity<UserDTO> updateRegularUser(@RequestBody UserDTO updatedUser,@PathVariable Integer id){
		return regUserService.updateRegularUser(updatedUser, id);
	}
	
	
}
