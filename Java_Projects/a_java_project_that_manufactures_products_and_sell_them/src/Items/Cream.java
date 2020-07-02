package Items;
public class Cream extends Ingredient implements IUncountable{
	public Cream(String name, double amount) {
		super(name,amount,0.30);
	}
}
