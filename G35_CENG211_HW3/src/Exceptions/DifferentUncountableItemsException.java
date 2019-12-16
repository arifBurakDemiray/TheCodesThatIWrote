package Exceptions;
// If you try to mix to uncountable items they will mix because they are uncountable. They must not mix.

public class DifferentUncountableItemsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DifferentUncountableItemsException() {
		super("You can not store different Uncountable items because they can mix.");
	}
	public DifferentUncountableItemsException(String message) {
		super(message);
	}

}
