package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Post;
import com.example.demo.exceptions.UserWithEmailExistsException;

public interface UserService {
	
	String updatePassword (String emailAddress) throws UserWithEmailExistsException;
	
	List<Post> getHomepage(String name);

}
