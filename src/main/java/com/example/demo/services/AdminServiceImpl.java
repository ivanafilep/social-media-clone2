package com.example.demo.services;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.AdminEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdminRepository adminRepository;

	public ResponseEntity<UserDTO> createAdmin(@Valid UserDTO newUser) {

			
			UserEntity existingUserWithEmail = userRepository.findByEmail(newUser.getEmail());
			UserEntity existingUserWithUsername = userRepository.findByUsername(newUser.getUsername());
			

			if (existingUserWithEmail != null) {
				return new ResponseEntity (HttpStatus.CONFLICT);
			}

			if (existingUserWithUsername != null) {
				return new ResponseEntity(HttpStatus.CONFLICT);
			}

			
			AdminEntity newAdmin = new AdminEntity();

			newAdmin.setEmail(newUser.getEmail());
			newAdmin.setUsername(newUser.getUsername());
			newAdmin.setPassword(passwordEncoder.encode(newUser.getPassword()));
			
			if(newUser.getPassword().equals(newUser.getConfirmedPassword())){
				newAdmin.setPassword(newUser.getPassword());
			} else {
				return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
			}
			
			newAdmin.setName(newUser.getName());
			newAdmin.setLastName(newUser.getLastName());
			newAdmin.setRole("ROLE_ADMIN");
			
			userRepository.save(newAdmin);
		
			return new ResponseEntity<UserDTO>(HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> updateAdmin( UserDTO updatedAdmin,Integer id) {
		Optional<AdminEntity> admin = adminRepository.findById(id);
		
		if (admin.isEmpty()) {
			return new ResponseEntity<>("Chef not found in the database", HttpStatus.NOT_FOUND);
		}

		admin.get().setEmail(updatedAdmin.getEmail());
		admin.get().setUsername(updatedAdmin.getUsername());
		admin.get().setPassword(updatedAdmin.getPassword());
		admin.get().setName(updatedAdmin.getName());
		admin.get().setLastName(updatedAdmin.getLastName());

		adminRepository.save(admin.get());
		return new ResponseEntity<>(admin.get(), HttpStatus.OK);
	}
	
}




