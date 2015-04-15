package com.lavalloisir.dao;

public class DAOConfigurationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6867172240961813172L;

	// CONSTRUCTORS
	public DAOConfigurationException (String message) {
		super(message);
	}
	
	public DAOConfigurationException (Throwable cause) {
		super(cause);
	}
	
	public DAOConfigurationException (String message, Throwable cause) {
		super(message, cause);
	}
}
