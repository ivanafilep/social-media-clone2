package entities;

import javax.persistence.Entity;

@Entity
public class UserEntity {

	private Integer id;
	private String email;
	private String userName;
	private String password;
	private String confirmedPassword;
	private String name;
	private String lastName;
	private String role;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserEntity() {

	}

	public UserEntity(Integer id, String email, String userName, String password, String confirmedPassword, String name,
			String lastName, String role) {
		super();
		this.id = id;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.confirmedPassword = confirmedPassword;
		this.name = name;
		this.lastName = lastName;
		this.role = role;
	}

	

}
