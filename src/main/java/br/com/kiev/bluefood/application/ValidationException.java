package br.com.kiev.bluefood.application;

public class ValidationException extends Exception{

	private static final long serialVersionUID = 1L;

	public ValidationException(String message) {
		super(message);
	}
}
