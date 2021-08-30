package challenge.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.br.CPF;

import challenge.api.model.User;

public class UserRequest {

	@NotBlank
	private String name;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@CPF
	private String document;

	@NotNull
	@Past
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	public UserRequest(String name, String email, String document, LocalDate birthDate) {
		this.name = name;
		this.email = email;
		this.document = document;
		this.birthDate = birthDate;
	}

	public User toUser() {
		return new User(name, email, document, birthDate);
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getDocument() {
		return document;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}
}
