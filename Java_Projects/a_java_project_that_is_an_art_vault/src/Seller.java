//Here our seller class.
public class Seller {
	private Wallet sellerWallet;//Here our wallet for seller.
	private PrivateCollection sellerCollection;//Here our private collection for seller.
	
	/**
	 * 
	 * @param wallet
	 * @param collection
	 */
	public Seller(Wallet wallet,PrivateCollection collection) {//basic constructor.
		this.sellerWallet=wallet;
		this.sellerCollection=collection;}
	
    //getters and setters.
	/**
	 * @returns the wallet of seller.
	 */
	public Wallet getSellerWallet() {
		return sellerWallet;}
	/**
	 * Changes the wallet of seller.
	 * @param sellerWallet
	 */
	public void setSellerWallet(Wallet sellerWallet) {
		this.sellerWallet = sellerWallet;}
	/**
	 * @returns the collection of seller.
	 */
	public PrivateCollection getSellerCollection() {
		return sellerCollection;}
	/**
	 * Changes the collection of this seller.
	 * @param sellerCollection
	 */
	public void setSellerCollection(PrivateCollection sellerCollection) {
		this.sellerCollection = sellerCollection;}
}