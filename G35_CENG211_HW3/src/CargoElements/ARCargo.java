package CargoElements;
import Items.Ingredient;
import Items.Product;
import StorageElements.DryStorageContainer;
import StorageElements.IContainer;
import StorageElements.OpenTopContainer;
import StorageElements.TankContainer;
//This class just holds the containers. Why different class for them?
//Because containers must not change and creating just one time is enough for them.
public class ARCargo {
	protected static IContainer<Ingredient> tankIngredient = new TankContainer<Ingredient>(1000);
	protected static  IContainer<Ingredient> openIngredient = new OpenTopContainer<Ingredient>(1000);
	protected static IContainer<Product> productCon = new DryStorageContainer<Product>(1000);

}
