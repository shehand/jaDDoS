package com.jaddos.exception;

/**
 * @author SonoD
 *
 */
public class InvalidArgumentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidArgumentException() {
		super("Reformat the console arguments.");
	}
	
	public InvalidArgumentException(String exception) {
		super(exception);
	}
}
