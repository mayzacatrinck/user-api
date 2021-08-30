package challenge.api.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true, length = 11)
	private String document;

	@Column(nullable = false)
	private LocalDate birthDate;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Address> addresses = new ArrayList<>();

	public User(String name, String email, String document, LocalDate birthDate) {
		this.name = name;
		this.email = email;
		this.document = document;
		this.birthDate = birthDate;
	}

	private User() {
	}

	public void addAddress(Address address) {
		address.setUser(this);
		addresses.add(address);
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Long getId() {
		return id;
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

	public List<Address> getAddresses() {
		return addresses;
	}
}
