package Elements;

import Storage.IInventory;
import Storage.ProductInventory;

//our customer class.
public class Customer extends RegularUser {
	private IInventory<Product> depo;
	public Customer(String userName, String password, int activeBalance,ContactInfo contanct) {
		super(userName, password, activeBalance,contanct);
		setDepo(new ProductInventory<Product>());
	}
	public IInventory<Product> getDepo() {
		return depo;
	}
	public void setDepo(IInventory<Product> depo) {
		this.depo = depo;
	}
	

}
