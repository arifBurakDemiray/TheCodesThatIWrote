package Program;
import Facilities.DistrubutionCenter;
import Facilities.Factory;
import Facilities.Owner;
import Facilities.Warehouse;
import Items.Ingredient;
import Items.Product;

public interface IOperations {
	public void buyOperation(Warehouse<Ingredient> warehouse, Owner sahip, Ingredient alınan);
	public void sellOperation(DistrubutionCenter<Product> dtCenter, Owner sahip, Product satılan);
	public void produce(Product product, Factory<Ingredient,Product> factory);
}
