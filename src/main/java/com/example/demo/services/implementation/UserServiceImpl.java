package com.example.demo.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Email;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.UserWithEmailExistsException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.EmailService;
import com.example.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private final EmailService emailService;

	private final UserRepository userRepository;
	
    private final PasswordEncoder passwordEncoder;
    
    public UserServiceImpl(EmailService emailService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.emailService = emailService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
    @Override
	public String updatePassword (String emailAddress) throws UserWithEmailExistsException{
		
		User user = userRepository.findByEmail(emailAddress);

        if (user == null) {
             throw new UserWithEmailExistsException ("Korisnik sa zadatim email-om ne postoji.");
        }

        String newPassword = "lozinkica"; 
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        
        Email email = new Email();
        email.setTo("filepivana95@gmail.com");
        email.setSubject("Nova lozinka");
        email.setText("Vaša nova lozinka je: " + newPassword);
        emailService.sendSimpleMessage(email);
        
        return "Nova lozinka je poslata na vaš email.";
	}
    
	//dodaj PostDTO
	@Override
	public List<Post> getHomepage(String name) {
		User currentUser = userRepository.findByEmail(name);

		Set<User> following = currentUser.getFollowing();
		List<Post> homepagePosts = new ArrayList<>();

		for (User user : following) {
			homepagePosts.addAll(user.getPosts());
		}

		return homepagePosts;
	}
	
	
	
	
}
	
	


