package com.example.demo.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;

public class CommentDTO {

	private Integer id;

	private String comment;

	private Post post;
	
	private User user;
	
	private Set<ReactionDTO> reactions = new HashSet<ReactionDTO>();
	
	public CommentDTO() {

	}

	public CommentDTO(Comment c) {
		super();
		this.id = c.getId();
		this.comment = c.getComment();
		this.post = c.getPost();
		this.user = c.getUser();
		this.reactions= c.getReactions().stream().map(reactions -> new ReactionDTO(reactions)).collect(Collectors.toSet());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<ReactionDTO> getReactions() {
		return reactions;
	}

	public void setReactions(Set<ReactionDTO> reactions) {
		this.reactions = reactions;
	}
	
	
}
