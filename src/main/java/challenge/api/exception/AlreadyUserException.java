package challenge.api.exception;

import org.springframework.http.HttpStatus;

public class AlreadyUserException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public AlreadyUserException(String message) {
		super(message, HttpStatus.CONFLICT);
	}

}
