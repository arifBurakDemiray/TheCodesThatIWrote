package Exceptions;

// if you try to storage an uncountable item in countable item's container
// they can mix.
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
