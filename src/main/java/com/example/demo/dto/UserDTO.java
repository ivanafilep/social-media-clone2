package com.example.demo.dto;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.entities.User;

public class UserDTO {
	

	private Integer id;

	@NotNull(message = "Email must be included.")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email is not valid.")
	private String email;
	
	
	@NotNull(message = "Username must be specified")
	@Size(min = 2, max = 30, message = "User name must be between {min} and {max} characters long.")
	private String username;
	
	
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message =
	"Password must be at least 8 characters long and contain a lowercase, an upercase letter and a number")
    @NotNull(message = "Password must be specified")
    @Size(min = 8, max = 100, message = "Password must be between {min} and {max} characters long.")
	private String password;
	
	
	private String confirmedPassword;
	


	@NotNull(message = "Name must be included.")
	@Size(min = 2, max = 30, message = "Name must be between {min} and {max} characters long.")
	private String name;
	

	
	@NotNull(message = "Lastname must be included.")
	@Size(min = 2, max = 30, message = "Lastname must be between {min} and {max} characters long.")
	private String lastName;
	
	
	private String role;
	

	public UserDTO() {
		
	}

	public UserDTO(User u) {
		super();
		this.id = u.getId();
		this.email = u.getEmail();
		this.username = u.getUsername();
		this.password = u.getPassword();
		this.confirmedPassword = u.getConfirmedPassword();
		this.name = u.getName();
		this.lastName = u.getLastName();
		this.role = u.getRole();
		
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

	
	

}
