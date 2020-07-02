package StorageElements;
import Exceptions.ItemNotCountableException;
import Items.IUncountable;

public class DryStorageContainer<T> extends Container<T>{

	public DryStorageContainer(int capacity) {
		super(capacity);
	}
	@Override
	public void addItem(T item){
		try {
			super.addItem(item);
			throw new ItemNotCountableException();
		}
		catch(ItemNotCountableException ince) {
			if(item instanceof IUncountable) {
				super.takeItem();
				System.out.println(ince.getMessage());}
				
		}
	}


}
