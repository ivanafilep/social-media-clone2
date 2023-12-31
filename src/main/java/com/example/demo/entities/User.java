package com.example.demo.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	
	@NotNull(message = "Email must be included.")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.")
	private String email;

	
	@NotNull(message = "Username must be specified")
	@Size(min = 2, max = 30, message = "User name must be between {min} and {max} characters long.")
	private String username;

	
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Password must be at least 8 characters long and contain a lowercase, an upercase letter and a number")
	@NotNull(message = "Password must be specified")
	@Size(min = 8, max = 100, message = "Password must be between {min} and {max} characters long.")
	private String password;

	
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Password must be at least 8 characters long and contain a lowercase, an upercase letter and a number")
	@NotNull(message = "Password must be specified")
	@Size(min = 8, max = 100, message = "Password must be between {min} and {max} characters long.")
	private String confirmedPassword;

	
	@NotNull(message = "Name must be included.")
	@Size(min = 2, max = 30, message = "Name must be between {min} and {max} characters long.")
	private String name;

	
	@NotNull(message = "Lastname must be included.")
	@Size(min = 2, max = 30, message = "Lastname must be between {min} and {max} characters long.")
	private String lastName;

	
	private String role;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_following_followers", joinColumns = @JoinColumn(name = "follower_id"), 
	inverseJoinColumns = @JoinColumn(name = "following_id"))
	private Set<User> following = new HashSet<>();
	
	@ManyToMany(mappedBy = "following", fetch = FetchType.LAZY)
	private Set<User> followers = new HashSet<>();
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {
			CascadeType.REFRESH })
			private Set<Post> posts = new HashSet<Post>();
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {
			CascadeType.REFRESH })
			private Set<Comment> comments = new HashSet<Comment>();
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {
			CascadeType.REFRESH })
			private Set<Reaction> reactions = new HashSet<Reaction>();
	


	public User() {

	}

	public User(Integer id,
			@NotNull(message = "Email must be included.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
			@NotNull(message = "Username must be specified") @Size(min = 2, max = 30, message = "User name must be between {min} and {max} characters long.") String username,
			@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Password must be at least 8 characters long and contain a lowercase, an upercase letter and a number") @NotNull(message = "Password must be specified") @Size(min = 8, max = 100, message = "Password must be between {min} and {max} characters long.") String password,
			@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Password must be at least 8 characters long and contain a lowercase, an upercase letter and a number") @NotNull(message = "Password must be specified") @Size(min = 8, max = 100, message = "Password must be between {min} and {max} characters long.") String confirmedPassword,
			@NotNull(message = "Name must be included.") @Size(min = 2, max = 30, message = "Name must be between {min} and {max} characters long.") String name,
			@NotNull(message = "Lastname must be included.") @Size(min = 2, max = 30, message = "Lastname must be between {min} and {max} characters long.") String lastName,
			String role, Set<User> following, Set<User> followers, Set<Post> posts, Set<Comment> comments,
			Set<Reaction> reactions) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.confirmedPassword = confirmedPassword;
		this.name = name;
		this.lastName = lastName;
		this.role = role;
		this.following = following;
		this.followers = followers;
		this.posts = posts;
		this.comments = comments;
		this.reactions = reactions;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmedPassword() {
		return confirmedPassword;
	}


	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	public Set<User> getFollowing() {
		return following;
	}


	public void setFollowing(Set<User> following) {
		this.following = following;
	}


	public Set<User> getFollowers() {
		return followers;
	}


	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(Set<Reaction> reactions) {
		this.reactions = reactions;
	}
	
}
