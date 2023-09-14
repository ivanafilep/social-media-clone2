package com.example.demo.dto;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Size;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;

public class PostDTO {
	
	private Integer id;

	@Size(min = 2, max = 100, message = "Title must be between {min} and {max} characters long.")
	private String title;
	
	
	private String content;
	
	
	private Date dateCreated;

	private User user;
	
	private Set<Comment> comments;

	public PostDTO() {
		
	}
		
	public PostDTO(Post p) {
		super();
		this.id = p.getId();
		this.title = p.getTitle();
		this.content  = p.getContent();
		this.dateCreated = p.getDateCreated();
		this.user = p.getUser();
		this.comments= p.getComments();
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

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	
	
	
	
	
}
