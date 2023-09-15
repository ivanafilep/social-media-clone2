package com.example.demo.services.implementation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.example.demo.repositories.RegularUserRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.RegularUserService;

@Service
public class RegularUserServiceImpl implements RegularUserService{
	
	private final UserRepository userRepository;

	private final RegularUserRepository regUserRepository;
	
    private final PasswordEncoder passwordEncoder;

	protected final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
	
	public RegularUserServiceImpl(UserRepository userRepository, RegularUserRepository regUserRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.regUserRepository = regUserRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserDTO create(UserDTO newUser) throws UserWithEmailExistsException, UserWithUsernameExistsException, ConfirmedPasswordException {

		User existingUserWithEmail = userRepository.findByEmail(newUser.getEmail());
		User existingUserWithUsername = userRepository.findByUsername(newUser.getUsername());
	
		if (existingUserWithEmail != null) {
			throw new UserWithEmailExistsException("User with email already exists!");
		}

		if (existingUserWithUsername != null) {
			throw new UserWithUsernameExistsException("User with username already exists!");
		}
		
		if(!newUser.getPassword().equals(newUser.getConfirmedPassword())) {
			throw new ConfirmedPasswordException ("Confirmed password does not match with password");
		}
		
		RegularUser newRegularUser = new RegularUser();
		newRegularUser.setEmail(newUser.getEmail());
		newRegularUser.setUsername(newUser.getUsername());
		newRegularUser.setPassword(newUser.getPassword());
		newRegularUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		newRegularUser.setConfirmedPassword(passwordEncoder.encode(newUser.getPassword()));
		newRegularUser.setName(newUser.getName());
		newRegularUser.setLastName(newUser.getLastName());
		newRegularUser.setRole("ROLE_REGULAR_USER");

		regUserRepository.save(newRegularUser);
	
		return newUser;

	}

	@Override
	public UpdatedUserDTO update(UpdatedUserDTO updatedUser, String name) throws UserWithEmailExistsException, UserWithUsernameExistsException, UserWithNameException, UserWithLastNameException {
		
		
		User currentUser = userRepository.findByEmail(name);
				
			
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
	
	@Override
	public Optional<RegularUser> getById(Integer id) throws UserDoesntExist {
		Optional<RegularUser> user = regUserRepository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserDoesntExist ("User does not found.");
		}
		return user;
	}
	
	@Override
	public User followUser(Integer id, String name) {
	    
	    User currentUser = userRepository.findByEmail(name);

	    Optional<User> userToFollowing = userRepository.findById(id);

	    if (userToFollowing.isPresent()) {
	        User userToFollow = userToFollowing.get();

	       
	        userToFollow.getFollowers().add(currentUser);
	        userRepository.save(userToFollow);

	        
	        currentUser.getFollowing().add(userToFollow);
	        userRepository.save(currentUser);
	        

	        
	    } return currentUser; 
	        
	}
}

		

