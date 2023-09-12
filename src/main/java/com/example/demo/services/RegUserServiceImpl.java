package com.example.demo.services;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.RegularUser;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.UserWithEmailExistsException;
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
	


	public ResponseEntity<UserDTO> updateRegularUser(UserDTO updatedUser, Integer id) {
		
		Optional<RegularUser> changeUser = regUserRepository.findById(id);
	
		
		String email = "filepivana@gmail.com";//(String) authentication.getName();
		UserEntity currentUser = userRepository.findByEmail(email);
	
		
		if (currentUser.getRole().equals("ROLE_REGULAR_USER")) {
			RegularUser regularUser = (RegularUser) currentUser;

			if (regularUser.getId().equals(changeUser.get().getId())) {
				
			
			changeUser.get().setEmail(updatedUser.getEmail());
			changeUser.get().setUsername(updatedUser.getUsername());
			changeUser.get().setPassword(updatedUser.getPassword());
			changeUser.get().setName(updatedUser.getName());
			changeUser.get().setLastName(updatedUser.getLastName());
			regUserRepository.save(changeUser.get());
			
			return new ResponseEntity<UserDTO>(HttpStatus.OK);
			
			}
		
		} return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	
	}	
}

		

