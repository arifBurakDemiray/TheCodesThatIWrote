package App;
import java.io.IOException;
import java.util.ArrayList;

import Elements.Product;
import Elements.Supplier;
import Elements.User;
import Storage.IInventory;

public class Operations {
	//This operation gives products to related suppliers.
	public static void giveItems(IInventory<Product> prInven, ArrayList<User> users ) throws IOException {
		prInven.getDepo().forEach((k,v)->{
			for(User user:users) {
				if(user instanceof Supplier) {
					if(k.getCategory().equals(((Supplier) user).getShopCategory()))
						((Supplier) user).getProducts().addElementToInventory(k, v);
				}
			}
		});
		
	}
	
}
