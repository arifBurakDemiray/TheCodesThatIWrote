package App;

import Elements.Customer;
import Elements.Product;
import Elements.Supplier;

public class ReturningOperations {
	private Supplier thiSup;
	public ReturningOperations() {}
	private boolean result = false;
	//in here if supplier has this item it returns true. else false.
	private boolean acceptProduct(Product pro,Supplier sup,int adet) {
		sup.getShop().getOldProducts().getDepo().forEach((k,v)->{
			if(pro.equals(k) && adet==v)
				this.result=true;
				
		});
		return this.result;
	}
	//this method makes some changes for taking back.
	public void takeBack(Product pro,Supplier sup,int adet,Customer cus) {
		if(this.acceptProduct(pro, sup, adet)) {
			this.result=false;
			sup.setActiveBalance((int) (sup.getActiveBalance()-pro.getPrice()*adet));
			cus.setActiveBalance((int) (cus.getActiveBalance()+pro.getPrice()*adet));
			cus.getDepo().removeElementFromInventory(pro, adet);
			sup.getOldProducts().removeElementFromInventory(pro, adet);
			sup.getProducts().addElementToInventory(pro, adet);
		}
		else
			System.out.println("We do not have that product in our receipts!!");
	}
	public Supplier getThiSup() {
		return thiSup;
	}
	public void setThiSup(Supplier thiSup) {
		this.thiSup = thiSup;
	}
}
