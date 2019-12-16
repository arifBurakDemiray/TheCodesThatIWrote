package Items;

public class Yogurt extends Product implements ICountable{
	public Yogurt(String name, double amount) {
		super(name,amount,0,2);	
	}

}
