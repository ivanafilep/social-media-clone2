package com.example.demo.services;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.example.demo.dto.UpdatedUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.UserWithEmailExistsException;
import com.example.demo.exceptions.UserWithLastNameException;
import com.example.demo.exceptions.UserWithNameException;
import com.example.demo.exceptions.UserWithUsernameExistsException;

public interface RegUserService {

	ResponseEntity<UserDTO> createRegularUser(UserDTO newUser) throws UserWithEmailExistsException, UserWithUsernameExistsException;

	UpdatedUserDTO updateRegularUser(UpdatedUserDTO updatedUser, Authentication authentication) throws UserWithEmailExistsException, UserWithUsernameExistsException, UserWithNameException, UserWithLastNameException;
}
