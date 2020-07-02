package Storage;
import java.util.Set;

import Elements.Product;
import Exceptions.ProductNotFoundException;

public class ProductInventory<T extends Product> extends Inventory<T>{
	public ProductInventory() {
		super();
	}
	//if we have in key set returns true.
	public Product findProduct(String name) throws ProductNotFoundException {
		Set<T> keySet = this.depo.keySet();
		for(T item: keySet) {
			if(item.getName().equals(name))
				return item;
		}
		throw new ProductNotFoundException();
	}
}
