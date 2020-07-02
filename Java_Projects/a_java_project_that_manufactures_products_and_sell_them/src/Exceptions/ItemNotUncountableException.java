package Exceptions;
public class ItemNotUncountableException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ItemNotUncountableException() {
		super("The Container that you are using is not compatible for Countable items.");
	}
	public ItemNotUncountableException(String message) {
		super(message);
	}
}
