package Exceptions;
public class ProductNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ProductNotFoundException(){
		super("ProductNotFoundException, ''product is not existing in the inventory'' ");
	}
	public String getMessage() {
		return super.getMessage();
	}

}
