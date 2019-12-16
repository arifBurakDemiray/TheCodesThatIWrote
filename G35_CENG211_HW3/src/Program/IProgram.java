package Program;

public interface IProgram {
	/**
	 * this method for PreProgram. Buys ingredients with given inputs.
	 */
	public void buyOperationWithUser();
	/**
	 * this method for PreProgram. Sells products with given inputs.
	 */
	public void sellOperationWithUser();
	/**
	 * this method for PreProgram. Produce products with given inputs.
	 */
	public void produceOperation();
	/**
	 * ship tp the factory by given inputs.
	 */
	public void toTheFactByUser();
	/**
	 * ship to the distrubution center by given inputs.
	 */
	public void toTheDistCentByUser();
	/**
	 * prints the money and capacities of all things.
	 */
	public void print();
}
