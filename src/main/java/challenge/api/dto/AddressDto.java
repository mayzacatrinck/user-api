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

	public String getComplement() {
		return complement;
	}

	public String getZipCode() {
		return zipCode;
	}

}
