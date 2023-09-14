package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.ServiceImplementation.CommentServiceImpl;
import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PostDTO;

@RestController
@RequestMapping(path = "project/comments")
public class CommentController {
	
	@Autowired
	private CommentServiceImpl commentService;
	
	@PostMapping(path = "/{id}")
	public ResponseEntity<?> create(@RequestBody CommentDTO commentDTO, @PathVariable(name = "id") Integer postId, Authentication authentication) {
		return new ResponseEntity<PostDTO>(commentService.create(commentDTO, postId, authentication.getName()), HttpStatus.CREATED);
	}

}
