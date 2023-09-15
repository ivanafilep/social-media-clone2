package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostDTO;
import com.example.demo.exceptions.PostDoesntExistsException;
import com.example.demo.services.PostService;

@RestController
@RequestMapping(path = "project/posts")
public class PostController {
	
	private final PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody PostDTO newPost,  Authentication authentication) throws Exception{
		return new ResponseEntity<>(postService.create(newPost, authentication.getName()),HttpStatus.OK);
	}
	
	//@Secured("ROLE_REGULAR_USER")
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) throws PostDoesntExistsException {
		return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
	}
	

}
