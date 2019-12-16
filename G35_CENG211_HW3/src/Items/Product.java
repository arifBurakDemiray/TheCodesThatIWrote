package Items;
public class Product implements ICostable{
	private String name;
	private double amount;
	private int number;// box number of products.
	private double perLCost;
	public Product(String name) {
		this(name,0);
	}
	public Product(String name, double amount) {
		this(name,0,0);
	}
	public Product(String name, double amount, int number) {
		this(name,amount,number,0);
	}
	public Product(String name, double amount, int number, double perLCost) {
		this.name=name;
		this.amount=amount;
		this.number=number;
		this.perLCost=perLCost;
	}
	public void increaseNumber(int num) {
		this.number+=num;//adding boxes.
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
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
}
