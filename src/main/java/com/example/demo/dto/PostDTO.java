package com.example.demo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Size;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.Reaction;
import com.example.demo.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PostDTO {
	
	private Integer id;

	@Size(min = 2, max = 100, message = "Title must be between {min} and {max} characters long.")
	private String title;
	
	private String content;
	
	private Date dateCreated;

	private User user;
	
//	private Set<CommentDTO> comments = new HashSet<CommentDTO>();
	
	private Set<ReactionDTO> reactions = new HashSet<ReactionDTO>();

	public PostDTO() {
		
	}
		
	public PostDTO(Post p) {
		super();
		this.id = p.getId();
		this.title = p.getTitle();
		this.content  = p.getContent();
		this.dateCreated = p.getDateCreated();
		this.user = p.getUser();
		this.reactions= p.getReactions().stream().map(reactions -> new ReactionDTO(reactions)).collect(Collectors.toSet());
		//this.comments= p.getComments().stream().map(comment -> new CommentDTO(comment)).collect(Collectors.toSet());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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
