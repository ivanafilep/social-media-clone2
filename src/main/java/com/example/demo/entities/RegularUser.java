package com.example.demo.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "regUser")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RegularUser extends User{
	
	public RegularUser() {
		super();
	}

	public RegularUser(Integer id,
			@NotNull(message = "Email must be included.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.") String email,
			@NotNull(message = "Username must be specified") @Size(min = 2, max = 30, message = "User name must be between {min} and {max} characters long.") String username,
			@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Password must be at least 8 characters long and contain a lowercase, an upercase letter and a number") @NotNull(message = "Password must be specified") @Size(min = 8, max = 100, message = "Password must be between {min} and {max} characters long.") String password,
			@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Password must be at least 8 characters long and contain a lowercase, an upercase letter and a number") @NotNull(message = "Password must be specified") @Size(min = 8, max = 100, message = "Password must be between {min} and {max} characters long.") String confirmedPassword,
			@NotNull(message = "Name must be included.") @Size(min = 2, max = 30, message = "Name must be between {min} and {max} characters long.") String name,
			@NotNull(message = "Lastname must be included.") @Size(min = 2, max = 30, message = "Lastname must be between {min} and {max} characters long.") String lastName,
			String role, Set<User> following, Set<User> followers, Set<Post> posts, Set<Comment> comments,
			Set<Reaction> reactions) {
		super(id, email, username, password, confirmedPassword, name, lastName, role, following, followers, posts, comments,
				reactions);
	}

	
}
