package com.example.demo.services;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.ProfileUserDTO;
import com.example.demo.dto.UpdatedUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.RegularUser;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.UserDoesntExist;
import com.example.demo.exceptions.UserWithEmailExistsException;
import com.example.demo.exceptions.UserWithLastNameException;
import com.example.demo.exceptions.UserWithNameException;
import com.example.demo.exceptions.UserWithUsernameExistsException;
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
	
	
	
	public ResponseEntity<UserDTO> createRegularUser(@Valid UserDTO newUser) throws UserWithEmailExistsException, UserWithUsernameExistsException {

		UserEntity existingUserWithEmail = userRepository.findByEmail(newUser.getEmail());

		UserEntity existingUserWithUsername = userRepository.findByUsername(newUser.getUsername());
	
		if (existingUserWithEmail != null) {
			throw new UserWithEmailExistsException("User with email already exists!");
		}

		if (existingUserWithUsername != null) {
			throw new UserWithUsernameExistsException("User with username already exists!");
		}

		
		RegularUser newRegularUser = new RegularUser();

		newRegularUser.setEmail(newUser.getEmail());
		newRegularUser.setUsername(newUser.getUsername());
		newRegularUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		newRegularUser.setConfirmedPassword(passwordEncoder.encode(newUser.getPassword()));
		/*
		if(newUser.getPassword().equals(newUser.getConfirmedPassword())){
			newRegularUser.setPassword(newUser.getPassword());
		} else {
			return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
		}
		*/
	
		newRegularUser.setName(newUser.getName());
		newRegularUser.setLastName(newUser.getLastName());
		newRegularUser.setRole("ROLE_REGULAR_USER");

		regUserRepository.save(newRegularUser);
	
		return new ResponseEntity<UserDTO>(HttpStatus.CREATED);

	}

	public UpdatedUserDTO updateRegularUser(UpdatedUserDTO updatedUser, Authentication authentication) throws UserWithEmailExistsException, UserWithUsernameExistsException, UserWithNameException, UserWithLastNameException {
		
		String email = (String) authentication.getName();
		UserEntity currentUser = userRepository.findByEmail(email);
				
			
		currentUser.setEmail(updatedUser.getEmail());
		
		currentUser.setUsername(updatedUser.getUsername());
		if (!updatedUser.getUsername().equals(currentUser.getUsername())) {
	        currentUser.setUsername(updatedUser.getUsername());
	    }

		currentUser.setName(updatedUser.getName());
		if (!updatedUser.getName().equals(currentUser.getName())) {
	        currentUser.setName(updatedUser.getName());
	    }
		currentUser.setLastName(updatedUser.getLastName());
		if (!updatedUser.getLastName().equals(currentUser.getLastName())) {
	        currentUser.setLastName(updatedUser.getLastName());
	    }
		
		userRepository.save(currentUser);
			
		return updatedUser;
	
	}	
	
	
	public Optional<RegularUser> getRegularUserById(@PathVariable Integer id) throws UserDoesntExist {
		Optional<RegularUser> user = regUserRepository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserDoesntExist ("User does not found.");
		}
		return user;
	}
	
}

		

