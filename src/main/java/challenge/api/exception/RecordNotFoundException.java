package challenge.api.exception;

import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}

}
