package challenge.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import challenge.api.model.User;

public class UserDto {

	@NotBlank(message = "{name.notblank}")
	private String name;

	@NotBlank(message = "{email.notblank}")
	@Email(message = "{email.notvalid}")
	private String email;

	@NotBlank(message = "{document.notblank}")
	@CPF(message = "{document.notvalid}")
	private String document;

	@NotNull(message = "{birthDate.notempty}")
	private LocalDate birthDate;

	public User toUser() {
		User user = new User();
		user.setName(this.getName());
		user.setEmail(this.getEmail());
		user.setDocument(this.getDocument());
		user.setBirthDate(this.getBirthDate());
		return user;
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
