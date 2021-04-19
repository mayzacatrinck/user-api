package challenge.api.exception;

import org.springframework.http.HttpStatus;

public class ZipCodeNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public ZipCodeNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}

}
