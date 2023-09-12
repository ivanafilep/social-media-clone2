package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Email;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private EmailServiceImpl emailService;

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	public ResponseEntity<?>updatePassword (@RequestParam String emailAddress){
		
		UserEntity user = userRepository.findByEmail(emailAddress);

        if (user == null) {
            return ResponseEntity.badRequest().body("Korisnik sa zadatim email-om ne postoji.");
        }

        String newPassword = "lozinkica"; //generateNewPassword();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        
        Email email = new Email();
        email.setTo("filepivana95@gmail.com");
        email.setSubject("Nova lozinka");
        email.setText("Vaša nova lozinka je: " + newPassword);
        emailService.sendSimpleMessage(email);
        
        // ne vraca se u servisima responseEntity
        return ResponseEntity.ok("Nova lozinka je poslata na vaš email.");
	}

	
	}
	
	


