package de.mag.resttest.app;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -3939161689628095685L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
