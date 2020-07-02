package Elements;

import Storage.IInventory;
import Storage.ProductInventory;

public class Shop {
	private IInventory<Product> products;  
	private IInventory<Product> oldProducts;
	public Shop() {
		this.setProducts(new ProductInventory<Product>());
		this.setOldProducts(new ProductInventory<Product>());
	}
	public IInventory<Product> getProducts() {
		return products;
	}
	public void setProducts(IInventory<Product> products) {
		this.products = products;
	}
	public IInventory<Product> getOldProducts() {
		return oldProducts;
	}
	public void setOldProducts(IInventory<Product> oldProducts) {
		this.oldProducts = oldProducts;
	}
}
