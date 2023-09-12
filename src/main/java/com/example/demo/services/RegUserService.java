package com.example.demo.services;
import org.springframework.http.ResponseEntity;
import com.example.demo.dto.UserDTO;
import com.example.demo.exceptions.UserWithEmailExistsException;
import com.example.demo.exceptions.UserWithUsernameExistsException;

public interface RegUserService {

	ResponseEntity<UserDTO> createRegularUser(UserDTO newUser) throws UserWithEmailExistsException, UserWithUsernameExistsException;

	ResponseEntity<UserDTO> updateRegularUser(UserDTO updatedUser, Integer id);
}
