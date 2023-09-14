package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entities.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer>{

	ResponseEntity<?> save(CommentDTO comment);

}
