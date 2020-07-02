package src;

import App.IProgram;
import App.Program;
import Exceptions.ProductNotFoundException;

/**
 * 
 * @author Arif Burak Demiray 
 */
public class ShopApp {
	public static void main(String[] args) throws Exception,ProductNotFoundException {
		IProgram program = new Program();
		program.program();
	}

}
