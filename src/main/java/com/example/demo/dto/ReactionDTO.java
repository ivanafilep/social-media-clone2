package com.example.demo.dto;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.Reaction;
import com.example.demo.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReactionDTO {

	private Integer id;

	private boolean isLiked;

	private boolean isDisliked;
	
	private User user; 
	
	private Comment comment; 
	
	
	private Post post;
	
	public ReactionDTO() {
		
	}
	

	public ReactionDTO(Reaction r) {
		super();
		this.id = r.getId();
		this.isLiked = r.isLike();
		this.isDisliked = r.isDislike();
		this.user = r.getUser();
		this.comment = r.getComment();
		this.post = r.getPost();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public boolean isLiked() {
		return isLiked;
	}


	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}


	public boolean isDisliked() {
		return isDisliked;
	}


	public void setDisliked(boolean isDisliked) {
		this.isDisliked = isDisliked;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Comment getComment() {
		return comment;
	}


	public void setComment(Comment comment) {
		this.comment = comment;
	}


	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
	}
	
	
	
	

}
