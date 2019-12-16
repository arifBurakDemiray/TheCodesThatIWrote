package Facilities;
// The owner of the facilities
//and it has a wallet that holds its money in it.
public class Owner {
	private String name;
	private Wallet wallet = new Wallet(0);
	
	
	public Owner(String name, int money) {
		this.name = name;
		this.wallet.setMoney(money);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	
	
	
	
	
	
	

}
