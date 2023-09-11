package com.example.demo.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.RegularUser;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.RegUserRepository;
import com.example.demo.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RegUserServiceImpl implements RegUserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RegUserRepository regUserRepository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;

	protected final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	public ResponseEntity<UserDTO> createRegularUser(@Valid UserDTO newUser, Authentication authentication) {

		
		UserEntity existingUserWithEmail = userRepository.findByEmail(newUser.getEmail());
		logger.info("Finding out whether there's a user with the same email.");

		UserEntity existingUserWithUsername = userRepository.findByUsername(newUser.getUsername());
		logger.info("Finding out whether there's a user with the same username.");

		if (existingUserWithEmail != null) {
			logger.error("There is a user with the same email.");
			return new ResponseEntity (HttpStatus.CONFLICT);
		}

		if (existingUserWithUsername != null) {
			logger.error("There is a user with the same username.");
			return new ResponseEntity(HttpStatus.CONFLICT);
		}

		//dodaj da se lozinka poklapa sa potvrdjenom lozinkom 
		RegularUser newRegularUser = new RegularUser();

		newRegularUser.setEmail(newUser.getEmail());
		newRegularUser.setUsername(newUser.getUsername());
		newRegularUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		newRegularUser.setConfirmedPassword(newUser.getConfirmedPassword());
		newRegularUser.setName(newUser.getName());
		newRegularUser.setLastName(newUser.getLastName());
		newRegularUser.setRole("ROLE_REGULAR_USER");
		
		logger.info("Setting users role.");

		regUserRepository.save(newRegularUser);
		logger.info("Saving regular user to the database");


		
		regUserRepository.save(newRegularUser);
		

		return new ResponseEntity<UserDTO>(HttpStatus.CREATED);

	}
	
	
	
	

}
