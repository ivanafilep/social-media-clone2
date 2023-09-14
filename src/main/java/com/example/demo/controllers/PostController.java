package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ServiceImplementation.PostServiceImpl;
import com.example.demo.dto.PostDTO;
import com.example.demo.entities.Post;

@RestController
@RequestMapping(path = "project/posts")
public class PostController {
	
	@Autowired
	private PostServiceImpl postService;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody PostDTO newPost,  Authentication authentication){
		return new ResponseEntity<>(postService.create(newPost, authentication.getName()),HttpStatus.OK);
	}
	
	

}
