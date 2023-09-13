package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Post;
import com.example.demo.services.PostService;

@RestController
@RequestMapping(path = "project/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createPost(@Valid @RequestBody Post newPost,  Authentication authentication){
		return new ResponseEntity<>(postService.createPost(newPost, authentication),HttpStatus.OK);
	}
	
	

}
