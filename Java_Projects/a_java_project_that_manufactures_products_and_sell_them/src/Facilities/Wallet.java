package Facilities;

public class Wallet {
	private double money;
	
	public Wallet(double money) {
		this.money = money;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	public boolean giveMoney(double money){
		this.money -= money;
		if(money > 0) {
			return true;
		}
		else {
			this.money += money;
			return false;
		}
		
	}
	public void addMoney(double money) {
		this.money += money;
	}
	

}
