package com.example.demo.services;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PostDTO;

public interface ReactionService {

	PostDTO createPostReaction(Integer postId, String reactionType, String name) throws Exception;
	
	CommentDTO createCommentReaction(Integer commentId, String reactionType, String name) throws Exception;
	
}
