//Here our buyer class.
public class Buyer {
	private Wallet buyerWallet;           //Buyer's wallet.
	private PrivateCollection buyerCollection;//Buyer's collection.
	//Basic constructor.
	/**
	 * @param wallet the buyers wallet that holds buyer money.
	 * @param collection the buyer's collection that holds buyer's item.
	 */
	public Buyer(Wallet wallet,PrivateCollection collection) {
		this.buyerWallet=wallet;
		this.buyerCollection=collection;}
	// Here getters and setters.
	/**
	 * @returns wallet of buyer.
	 */
	public Wallet getBuyerWallet() {
		return buyerWallet;}
	/**
	 * 
	 * @param buyerWallet to change this buyer's wallet.
	 */
	public void setBuyerWallet(Wallet buyerWallet) {
		this.buyerWallet = buyerWallet;}
	/**
	 * 
	 * @returns collection of this buyer.
	 */
	public PrivateCollection getBuyerCollection() {
		return buyerCollection;}
	/**
	 * 
	 * @param buyerCollection changes this buyer's collection.
	 */
	public void setBuyerCollection(PrivateCollection buyerCollection) {
		this.buyerCollection = buyerCollection;}
}