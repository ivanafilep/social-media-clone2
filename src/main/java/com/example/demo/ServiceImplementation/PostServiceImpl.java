package com.example.demo.ServiceImplementation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dto.PostDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public PostDTO create(@Valid PostDTO newPost, String name){
		
	
		User currentUser = userRepository.findByEmail(name);
		
		Post post = new Post();
		post.setTitle(newPost.getTitle());
		post.setContent(newPost.getContent());
		post.setDateCreated(newPost.getDateCreated());
		post.setUser(currentUser);
		
		
		postRepository.save(post);
		
		return newPost;
	}

}
