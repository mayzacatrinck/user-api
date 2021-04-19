package challenge.api.integration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressViaCep {

	@JsonProperty(value = "cep")
	private String zipCode;

	@JsonProperty(value = "logradouro")
	private String street;

	@JsonProperty(value = "bairro")
	private String neighborhood;

	@JsonProperty(value = "localidade")
	private String city;

	@JsonProperty(value = "uf")
	private String state;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
