package challenge.api.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import challenge.api.exception.ZipCodeException;
import challenge.api.exception.ZipCodeNotFoundException;
import challenge.api.integration.AddressViaCep;
import challenge.api.integration.ViaCepClient;

@Service
public class ZipCodeService {

	@Autowired
	private ViaCepClient viaCepClient;

	public AddressViaCep findZipeCode(String zipCode) {
		try {

			AddressViaCep addressViaCep = viaCepClient.findZipCode(zipCode);

			if (StringUtils.isAnyBlank(addressViaCep.getZipCode(), addressViaCep.getStreet())) {
				throw new ZipCodeNotFoundException("Zip code not found.");
			}

			return addressViaCep;

		} catch (ZipCodeNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new ZipCodeException("Zip code exception");
		}
	}

}
