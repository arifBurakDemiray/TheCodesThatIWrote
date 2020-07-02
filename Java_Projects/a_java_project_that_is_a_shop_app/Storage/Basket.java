package Storage;

import java.util.ArrayList;

import Elements.AdminUser;
import Elements.Customer;
import Elements.Product;
import Elements.Supplier;
//Here our basket.
public class Basket {
	public ProductInventory<Product> basket;//it has a product inventory.
	public Basket() {
		basket = new ProductInventory<Product>();
	}
	//here some attribýutes for total cost operations.
	private double cargo =0;
	private double totalPrice = 0;
	public void totalCost() {
		this.cargo=0;
		this.totalPrice=0;
		basket.getDepo().forEach((k,v) -> {
			System.out.println("Cargo Price: "+k.cargoPrice()*v+"  Price: "+k.getPrice()*v);
			this.cargo+=k.cargoPrice()*v;
			this.totalPrice+=k.getPrice()*v;
		});
		System.out.println("Total Payment :"+(this.cargo+this.totalPrice));
	}
	public void buy(Customer customer,ArrayList<Supplier> sup,AdminUser admin) {
		if(this.totalPrice>2000)//if greater then 2000 cargo price is 0.
			this.cargo=0;
		customer.setActiveBalance((int)(customer.getActiveBalance()-(this.cargo+this.totalPrice)));//in there we are changing their money.
		admin.setActiveBalance((int) (admin.getActiveBalance()+(2/100*(this.cargo+this.totalPrice))));
		basket.getDepo().forEach((k,v) -> {
			customer.getDepo().addElementToInventory(k, v);//adds bought product.
			for(Supplier sp : sup) {
				if(admin.approveSupplier(sp)) {
				switch(sp.getShopCategory()) {//here looks for same category to do money and remove operations for supplier
				case "houseware":
					sp.setActiveBalance((int)(sp.getActiveBalance()+(98/100*(this.totalPrice+this.cargo))));
					sp.getOldProducts().addElementToInventory(k, v);
					int temp=sp.getProducts().getItem(k);
					sp.getProducts().removeElementFromInventory(k, v);
					sp.getProducts().addElementToInventory(k, temp-v);
					break;
				case "electronics":
					sp.setActiveBalance((int)(sp.getActiveBalance()+(98/100*(this.totalPrice+this.cargo))));
					sp.getOldProducts().addElementToInventory(k, v);
					int temp1=sp.getProducts().getItem(k);
					sp.getProducts().removeElementFromInventory(k, v);
					sp.getProducts().addElementToInventory(k, temp1-v);
					break;
				case "accessories":
					sp.setActiveBalance((int)(sp.getActiveBalance()+(98/100*(this.totalPrice+this.cargo))));
					sp.getOldProducts().addElementToInventory(k, v);
					int temp2=sp.getProducts().getItem(k);
					sp.getProducts().removeElementFromInventory(k, v);
					sp.getProducts().addElementToInventory(k, temp2-v);
					break;
				case "cosmetic":
					sp.setActiveBalance((int)(sp.getActiveBalance()+(98/100*(this.totalPrice+this.cargo))));
					sp.getOldProducts().addElementToInventory(k, v);
					int temp3=sp.getProducts().getItem(k);
					sp.getProducts().removeElementFromInventory(k, v);
					sp.getProducts().addElementToInventory(k, temp3-v);
					break;
				default:
					System.out.println("We do not have that kind of product!!");}
			}
				else
					System.out.println("This supplier is not approved to sell products!!");}
		});
	}
	//this operation removes from basket an item.
	public void removeFromBasket(String name) {
		
		this.basket.getDepo().forEach((k,v) ->{
			
			if(k.getName().equals(name))
				this.basket.removeElementFromInventory(k, v);
			
		});
		
	}
	
}
