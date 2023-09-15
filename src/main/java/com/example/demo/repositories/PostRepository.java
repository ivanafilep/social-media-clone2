package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Post;

public interface PostRepository extends CrudRepository<Post, Integer>{

	//void save(Optional<Post> post);

}
