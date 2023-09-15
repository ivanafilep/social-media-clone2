package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.services.implementation.CommentServiceImpl;

@RestController
@RequestMapping(path = "project/comments")
public class CommentController {
	
	private final CommentServiceImpl commentService;
	
	public CommentController(CommentServiceImpl commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping(path = "/{id}")
	public ResponseEntity<?> create(@RequestBody CommentDTO commentDTO, @PathVariable(name = "id") Integer postId, Authentication authentication) throws Exception {
		return new ResponseEntity<PostDTO>(commentService.create(commentDTO, postId, authentication.getName()), HttpStatus.CREATED);
	}
	
	//getById

}
