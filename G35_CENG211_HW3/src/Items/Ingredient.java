package Items;
public class Ingredient implements ICostable{
	private String name;
	private double amount;
	private double perLCost;
	public Ingredient(String name) {
		this(name,0);
	}
	public Ingredient(String name ,double amount) {
		this(name,amount,0);
	}
	public Ingredient(String name ,double amount, double perLCost) {
		this.name=name;
		this.amount=amount;
		this.perLCost=perLCost;
	}
	public double getAmount() {
		return amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean setAmount(double amount) {// amount can not be zero and below zero.
		double temp = this.amount;
		this.amount = amount;
		if(this.amount<=0) {
			this.amount=temp;
			return false;
			}
		return true;
	}
	public double getPerLCost() {
		double temp = this.perLCost;
		return temp;}
	public void setPerLCost(double perLCost) {
		this.perLCost=perLCost;
	}
	public double calculateCost() {
		double cost = this.getAmount()*this.getPerLCost();
		return cost; }
	public void increaseAmount(double amount) {// for adding items.
		this.amount+=amount;
	}
}
