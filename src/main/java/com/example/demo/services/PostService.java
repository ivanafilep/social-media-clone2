package com.example.demo.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Post;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class PostService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	
	public ResponseEntity<Post> createPost(@Valid @RequestBody Post newPost,  Authentication authentication){
		
		String email = (String) authentication.getName();
		UserEntity currentUser = userRepository.findByEmail(email);
		
		
		Post post = new Post();
		post.setTitle(newPost.getTitle());
		post.setContent(newPost.getContent());
		post.setDateCreated(newPost.getDateCreated());
		post.setUser(currentUser);
		
		
		postRepository.save(post);
		
		return ResponseEntity.ok(newPost);
	}

}
