package net.codejava.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {

	@NotBlank
//	@Size(min = 1, max = 12)
//	use RegEx to check valid firstname in AuthController
	private String firstName;

	@NotBlank
//	@Size(min = 3, max = 20)
//	use RegEx to check valid username in AuthController
	private String username;

	@NotBlank
//	use RegEx to check valid surname in AuthController
	private String surname;

	@NotBlank
//	@Size(max = 50)
//	@Email 
//	use RegEx to check valid email in AuthController
	private String email;

	private Set<String> role;

	@NotBlank
//	@Size(min = 8, max = 40)
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRole() {
		return this.role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}
}