package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.Reaction;

public interface ReactionRepository extends CrudRepository<Reaction, Integer>{

	void save(Post post);

	void save(Comment comment);
	

}
