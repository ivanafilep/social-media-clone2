package com.example.demo.services;

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PostDTO;

public interface CommentService {
	
	PostDTO create(CommentDTO commentDTO, Integer postId, String name) throws Exception;

}
