//Here our wallet class.
public class Wallet {
	private double moneyThatIHave;//Our money.
	/**
	 * Basic constructor of Wallet class.
	 * @param money that is our money.
	 */
	public Wallet(double money) {//constructor takes an double. this is our money.
		this.moneyThatIHave=money;}
	
	//getter and setter of that money.
	/**
	 * @returns the our money in our wallet.
	 */
	public double getMoneyThatIHave() {
		return moneyThatIHave;}
	/**
	 * Changes our money in wallet.
	 * @param moneyThatIHave
	 */
	public void setMoneyThatIHave(double moneyThatIHave) {
		this.moneyThatIHave = moneyThatIHave;}
	
	//For adding money to the our wallet.
	/**
	 * Adds money to our wallet.
	 * @param money that is going to be added to our wallet.
	 */
	public void addMoney(double money) {
		this.moneyThatIHave += money;}
	//For giving money from our wallet.
	/**
	 * Gives money from our wallet.
	 * @param money that is going to be given from our wallet.
	 */
	public void giveMoney(double money) {
		this.moneyThatIHave -= money;}
}