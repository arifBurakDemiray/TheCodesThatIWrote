package Exceptions;


public class ItemNotCountableException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotCountableException() {
		super("The Container that you are using is not compatible for Uncountable items.");
	}
	public ItemNotCountableException(String message) {
		super(message);
	}
}
