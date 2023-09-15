package com.example.demo.services;

import java.util.Optional;

import javax.validation.Valid;

import com.example.demo.dto.PostDTO;
import com.example.demo.entities.Post;
import com.example.demo.exceptions.PostDoesntExistsException;

public interface PostService {
	
	PostDTO create(@Valid PostDTO newPost, String name) throws Exception;
	
	Optional<Post> getById(Integer id) throws PostDoesntExistsException;
	
}
