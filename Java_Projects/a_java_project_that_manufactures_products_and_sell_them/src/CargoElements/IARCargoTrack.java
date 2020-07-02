package CargoElements;
import Facilities.DistrubutionCenter;
import Facilities.Factory;
import Facilities.Owner;
import Facilities.Warehouse;
import Items.Ingredient;
import Items.Product;

public interface IARCargoTrack {
	public void toTheFactory(Warehouse<Ingredient> warehouse, Factory<Ingredient,Product> factory, Ingredient ingd, Owner sahip);
	public void toTheDistCenter(DistrubutionCenter<Product> distcen, Factory<Ingredient,Product> factory, Object[] prd, Owner sahip);
}
