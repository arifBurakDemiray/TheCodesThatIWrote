package CargoElements;
import Facilities.DistrubutionCenter;
import Facilities.Factory;
import Facilities.Owner;
import Facilities.Warehouse;
import Items.Ingredient;
import Items.Product;

public interface IARCargoTrack {
	/**
	 * This method takes the attributes below. Its mission is move the wanted ingredient from warehouse to factory.
	 * The method increases factory this ingredient's storage and does reversely at the warehouse.
	 * If it can not find the wanted things, it gives a printed error.
	 * There can not be more than one ingredient.
	 * @param warehouse is the warehouse where items are taken of the facilities.
	 * @param factory is the factory where items are going.
	 * @param ingd that is the wanted ingredient.
	 * @param sahip that is the owner of the facilities.
	 */
	public void toTheFactory(Warehouse<Ingredient> warehouse, Factory<Ingredient,Product> factory, Ingredient ingd, Owner sahip);
	/**
	 * This method takes the attributes below. Its mission is move the wanted product from factory to distrubution center.
	 * The method increases distrubution center this product's storage and does reversely at the factory.
	 * If it can not find the wanted things, it gives a printed error.
	 * There can be more than one product.
	 * @param distcen where items are going to ship.
	 * @param factory that is products are taken from there
	 * @param prd that is the wanted product
	 * @param sahip that is the owner of the facilities.
	 */
	public void toTheDistCenter(DistrubutionCenter<Product> distcen, Factory<Ingredient,Product> factory, Object[] prd, Owner sahip);
}
