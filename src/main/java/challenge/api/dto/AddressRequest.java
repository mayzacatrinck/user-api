package challenge.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddressRequest {

	@NotBlank
	private String number;

	@NotBlank
	private String complement;

	@NotBlank
	@Size(min = 8, max = 8)
	private String zipCode;

	public AddressRequest(String number, String complement, String zipCode) {
		this.number = number;
		this.complement = complement;
		this.zipCode = zipCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getComplement() {
		return complement;
	}

	public String getNumber() {
		return number;
	}
}
