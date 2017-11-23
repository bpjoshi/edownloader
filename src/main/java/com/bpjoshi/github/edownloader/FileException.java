package com.bpjoshi.github.edownloader;
/**
 * @author bpjoshi(Bhagwati Prasad)
 */
public class FileException extends Exception {

	private static final long serialVersionUID = 1L;

	public FileException() {
	}

	public FileException(String message) {
		super(message);
	}

	public FileException(Throwable cause) {
		super(cause);
	}

	public FileException(String message, Throwable cause) {
		super(message, cause);
		
	}

}
