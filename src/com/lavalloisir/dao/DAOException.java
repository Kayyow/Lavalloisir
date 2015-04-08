package com.lavalloisir.dao;

public class DAOException extends RuntimeException {
	// CONSTRUCTORS
	public DAOException (String message) {
		super(message);
	}
	
	public DAOException (Throwable cause) {
		super(cause);
	}
	
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
