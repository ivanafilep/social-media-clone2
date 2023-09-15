package com.example.demo.services;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.example.demo.dto.UpdatedUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.RegularUser;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ConfirmedPasswordException;
import com.example.demo.exceptions.UserDoesntExist;
import com.example.demo.exceptions.UserWithEmailExistsException;
import com.example.demo.exceptions.UserWithLastNameException;
import com.example.demo.exceptions.UserWithNameException;
import com.example.demo.exceptions.UserWithUsernameExistsException;

public interface RegularUserService {

	UserDTO create(UserDTO newUser) throws UserWithEmailExistsException, UserWithUsernameExistsException, ConfirmedPasswordException;

	UpdatedUserDTO update(UpdatedUserDTO updatedUser, String name) throws UserWithEmailExistsException, UserWithUsernameExistsException, UserWithNameException, UserWithLastNameException;
	
	Optional<RegularUser> getById(Integer id) throws UserDoesntExist;
	
	User followUser(Integer id, String name);
}

