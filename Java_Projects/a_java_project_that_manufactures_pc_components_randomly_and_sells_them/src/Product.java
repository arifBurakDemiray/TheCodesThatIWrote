
public class Product implements IProduct{
	private String name;
	private int productNum=0;
	public Product(String isim) {
		this.name=isim;
		productNum++;
	}
	public int getNum() {
		return this.productNum;
	}
	public String getName() {
		return this.name;
	}
	@Override
	public boolean isManufactured() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isStored() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isSold() {
		// TODO Auto-generated method stub
		return false;
	}
}
