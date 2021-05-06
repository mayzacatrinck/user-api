package challenge.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddressDto {

	@NotBlank(message = "{number.notblank}")
	private String number;

	@NotBlank(message = "{complement.notblank}")
	private String complement;

	@NotBlank(message = "{zipCode.notblank}")
	@Size(min = 8, max = 8, message = "{zipCode.notvalid}")
	private String zipCode;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "AddressDto [number=" + number + ", complement=" + complement + ", zipCode=" + zipCode + "]";
	}

}
