package com.example.demo.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.userTokenDTO;
import com.example.demo.entities.User;
import com.example.demo.exceptions.UserWithEmailExistsException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.util.Encryption;

import io.jsonwebtoken.Jwts;

@RestController
public class UserController {
	
	private final UserRepository userRepository;
	
	private final SecretKey secretKey;
	
	private final UserService userService;
	
	@Value("${spring.security.token-duration}")
	private Integer tokenDuration;
	
	
	public UserController(UserRepository userRepository, SecretKey secretKey, UserService userService) {
		this.userRepository = userRepository;
		this.secretKey = secretKey;
		this.userService = userService;
	}

	private String getJWTToken(User userEntity) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(userEntity.getRole());
		String token = Jwts.builder().setId("softtekJWT").setSubject(userEntity.getEmail())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + this.tokenDuration)).signWith(this.secretKey)
				.compact();
		return "Bearer " + token;
	}
	
	@PostMapping(path = "/social-media/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> userLogger) {
		String email = userLogger.get("email");
		String password = userLogger.get("password");
		User user = userRepository.findByEmail(email);
		if (user != null && Encryption.validatePassword(password, user.getPassword())) {
			String token = getJWTToken(user);
			userTokenDTO userLogin = new userTokenDTO(user.getId(), email, token);
			
			return new ResponseEntity<>(userLogin, HttpStatus.OK);
		}
		return new ResponseEntity<>("Wrong credentials", HttpStatus.UNAUTHORIZED);
		
	}
	
	
	@PutMapping(path = "/forgot-password")
	public ResponseEntity<?>updatePassword (@RequestParam String emailAddress) throws UserWithEmailExistsException{
		return new ResponseEntity<>(userService.updatePassword(emailAddress), HttpStatus.OK);
       
    }
		
	
	@GetMapping("/home-pages")
	public ResponseEntity<?>getHomepage(Authentication authentication) {
		return new ResponseEntity<>(userService.getHomepage(authentication.getName()), HttpStatus.OK);
		
	}

	
	
	

}
