package com.example.demo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Reaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private boolean isLiked;
	
	private boolean isDisliked;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user; 
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "comment")
    private Comment comment; 
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "post")
    private Post post;
	
	public Reaction() {
		
	}
	
	public Reaction(Integer id, boolean like, boolean dislike, User user, Comment comment, Post post) {
		super();
		this.id = id;
		this.isLiked = like;
		this.isDisliked = dislike;
		this.user = user;
		this.comment = comment;
		this.post = post;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isLike() {
		return isLiked;
	}

	public void setLike(boolean like) {
		this.isLiked = like;
	}

	public boolean isDislike() {
		return isDisliked;
	}

	public void setDislike(boolean dislike) {
		this.isDisliked = dislike;
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
