package com.example.demo.services.implementation;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PostDTO;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.exceptions.PostDoesntExistsException;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	private final UserRepository userRepository;
	
	private final PostRepository postRepository;
	
	public PostServiceImpl(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@Override
	public PostDTO create(@Valid PostDTO newPost, String name) throws Exception{
		User currentUser = userRepository.findByEmail(name);
		
		if(currentUser == null) {
			throw new Exception("");
		}
		
		Post post = new Post();
		post.setTitle(newPost.getTitle());
		post.setContent(newPost.getContent());
		post.setDateCreated(newPost.getDateCreated());
		post.setUser(currentUser);
		
		postRepository.save(post);
		
		return newPost;
	}

	@Override
	public Optional<Post> getById(Integer id) throws PostDoesntExistsException {
		Optional<Post> post = postRepository.findById(id);
		
		if (post.isEmpty()) {
			throw new PostDoesntExistsException ("Post does not found.");
		}
		return post;
	}

	
	
}
