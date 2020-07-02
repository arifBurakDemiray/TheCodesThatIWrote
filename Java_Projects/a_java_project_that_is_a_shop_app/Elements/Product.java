package Elements;
public class Product {
	private String category;
	private String name;
	private double price;
	private double weight;

	public Product(String category, String name, double price, double weight) {
		this.category = category;
		this.name = name;
		this.price = price;
		this.weight = weight;

	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double cargoPrice() {
		return weight*2;
	}
	

}
