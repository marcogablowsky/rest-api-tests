package de.mag.resttest.app.users;

import de.mag.resttest.app.ServiceException;

public class NoSuchUserException extends ServiceException {

	private static final long serialVersionUID = 3630959928475349724L;

	public NoSuchUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchUserException(String message) {
		super(message);
	}

	public NoSuchUserException(Throwable cause) {
		super(cause);
	}

}
