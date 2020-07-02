//Here our ITradable.
public interface ITradable {
	/** 
	 * @return if the artwork does not have banned style, returns true.
	 * if it has banned style returns false.
	 */
	public boolean isTradable();
	/**
	 * @param seller for taking the item from seller and give to the buyer
	 * and add buyer's money to the seller.
	 * @param buyer for taking the item and giving the money that he/she has.
	 */
	public void tradeToBuyer(Seller seller,Buyer buyer);
}