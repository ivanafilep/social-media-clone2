package com.example.demo.controllers;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ServiceImplementation.RegularUserServiceImpl;
import com.example.demo.dto.UpdatedUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.ConfirmedPasswordException;
import com.example.demo.exceptions.UserDoesntExist;
import com.example.demo.exceptions.UserWithEmailExistsException;
import com.example.demo.exceptions.UserWithLastNameException;
import com.example.demo.exceptions.UserWithNameException;
import com.example.demo.exceptions.UserWithUsernameExistsException;

@RestController
@RequestMapping(path = "project/regularusers")
public class RegularUserController {
	
	@Autowired 
	private RegularUserServiceImpl regUserService;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody UserDTO newUser) throws UserWithEmailExistsException, UserWithUsernameExistsException, ConfirmedPasswordException {
		try {
			return new ResponseEntity<> (regUserService.create(newUser), HttpStatus.OK);
		} catch (UserWithEmailExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (UserWithUsernameExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@Secured("ROLE_REGULAR_USER")
	@PutMapping
	public ResponseEntity<UpdatedUserDTO> update(@RequestBody UpdatedUserDTO updatedUser, Authentication authentication) throws UserWithEmailExistsException, UserWithUsernameExistsException, UserWithNameException, UserWithLastNameException{
		return new ResponseEntity<UpdatedUserDTO>(regUserService.update(updatedUser, authentication.getName()), HttpStatus.OK);
	}
	
	//@Secured("ROLE_REGULAR_USER")
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) throws UserDoesntExist {
		return new ResponseEntity<>(regUserService.getById(id), HttpStatus.OK);
	}
	
	
	@PostMapping(path= "/follow/{id}")
	public ResponseEntity<?> followUser (@PathVariable Integer id, Authentication authentication) {
		return new ResponseEntity<>(regUserService.followUser(id, authentication.getName()), HttpStatus.OK);
	}
		
	
	
}
