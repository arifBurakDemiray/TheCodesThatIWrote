package Elements;

//here our fast consumption product.
public class FastConsumptionProduct extends Product {
	public FastConsumptionProduct(String category, String name, double price, double weight) {
		super(category, name, price, weight);
		}
	@Override
	public double cargoPrice() {
		return super.cargoPrice()*3.5;
	}
	
	
	
	
	
	
	
	
	
	
}
