package com.example.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.ReactionService;

@RestController
@RequestMapping(path = "project/reactions")
public class ReactionController {
	
	private final ReactionService reactionService;
	
	public ReactionController(ReactionService reactionService) {
		this.reactionService = reactionService;
	}

	@PostMapping("/posts/{postId}")
    public ResponseEntity<?> createPostReaction(@PathVariable Integer postId, @RequestParam String reactionType, Authentication authentication ) throws Exception {
		return new ResponseEntity<>(reactionService.createPostReaction(postId, reactionType, authentication.getName()),HttpStatus.OK);
    }
	
	@PostMapping("/comments/{commentId}")
	public ResponseEntity<?> createCommentReaction(@PathVariable Integer commentId, @RequestParam String reactionType, Authentication authentication ) throws Exception {
			return new ResponseEntity<>(reactionService.createCommentReaction(commentId, reactionType, authentication.getName()),HttpStatus.OK);
	    }
	
	
}


