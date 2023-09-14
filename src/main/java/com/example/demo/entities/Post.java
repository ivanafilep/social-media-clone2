package com.example.demo.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "posts")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Size(min = 2, max = 100, message = "Title must be between {min} and {max} characters long.")
	private String title;
	
	
	private String content;
	
	
	private Date dateCreated;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private User user;
	
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = {
			CascadeType.REFRESH })
			private Set<Comment> comments = new HashSet<Comment>();

	
	public Post() {
		
	}
	

	public Post(Integer id,
			@Size(min = 2, max = 100, message = "Title must be between {min} and {max} characters long.") String title,
			String content, Date dateCreated, User user, Set<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.dateCreated = dateCreated;
		this.user = user;
		this.comments = comments;
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
