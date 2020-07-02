package Elements;

public class FragileProduct extends Product {

	public FragileProduct(String category, String name, double price, double weight) {
		super(category, name, price, weight);
		
	}
	@Override
	public double cargoPrice() {
		return super.cargoPrice()*4.5;
	}
	

}
