package Items;

public class Milk extends Ingredient implements IUncountable{
	public Milk(String name, double amount) {
		super(name,amount,0.25);
	}
}
