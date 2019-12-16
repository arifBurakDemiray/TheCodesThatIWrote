package StorageElements;
import java.util.Stack;

import Exceptions.DifferentUncountableItemsException;
import Exceptions.ItemNotUncountableException;
import Items.ICountable;
import Search.Search;

public class TankContainer<T> extends Container<T>{

	public TankContainer(int capacity) {
		super(capacity);
	}
	public void addItem(T item){// same as OpenTapContainer
		try {
			super.addItem(item);
			if(item instanceof ICountable)
				throw new ItemNotUncountableException();
			if(!new Search<Stack<T>>().searchsForEqualClasses(super.getContainer(), item)) {
				try {
					throw new DifferentUncountableItemsException();
				} catch (DifferentUncountableItemsException duie) {
					super.takeItem();
					System.out.println(duie.getMessage());
				}}
		}
		catch(ItemNotUncountableException ince) {
				super.takeItem();
				System.out.println(ince.getMessage());
				
		}
	}

}
