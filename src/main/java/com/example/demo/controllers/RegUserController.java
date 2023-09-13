package com.example.demo.controllers;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProfileUserDTO;
import com.example.demo.dto.UpdatedUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.UserDoesntExist;
import com.example.demo.exceptions.UserWithEmailExistsException;
import com.example.demo.exceptions.UserWithLastNameException;
import com.example.demo.exceptions.UserWithNameException;
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
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<UpdatedUserDTO> updateRegularUser(@RequestBody UpdatedUserDTO updatedUser, Authentication authentication) throws UserWithEmailExistsException, UserWithUsernameExistsException, UserWithNameException, UserWithLastNameException{
		return new ResponseEntity<UpdatedUserDTO>(regUserService.updateRegularUser(updatedUser, authentication), HttpStatus.OK);
	}
	
	//@Secured("ROLE_REGULAR_USER")
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getRegularUserById(@PathVariable Integer id) throws UserDoesntExist {
		return new ResponseEntity<>(regUserService.getRegularUserById(id), HttpStatus.OK);
	}
		
	
	
}
