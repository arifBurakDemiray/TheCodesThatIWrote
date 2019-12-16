package Program;
import Facilities.DistrubutionCenter;
import Facilities.Factory;
import Facilities.Owner;
import Facilities.Warehouse;
import Items.Ingredient;
import Items.Product;

public interface IOperations {
	/**
	 * this method buy items for owner.
	 * @param warehouse that will be stored bought items.
	 * @param sahip that is going to buy.
	 * @param alınan that is wanted item.
	 */
	public void buyOperation(Warehouse<Ingredient> warehouse, Owner sahip, Ingredient alınan);
	/**
	 * this method sell items for owner.
	 * @param dtCenter that products are stored in there.
	 * @param sahip that is gonna sell.
	 * @param satılan that is wanted to sell item.
	 */
	public void sellOperation(DistrubutionCenter<Product> dtCenter, Owner sahip, Product satılan);
	/**
	 * this method produces items for owner.
	 * @param product is wanted to produce.
	 * @param factory that produces product in it.
	 */
	public void produce(Product product, Factory<Ingredient,Product> factory);
}
