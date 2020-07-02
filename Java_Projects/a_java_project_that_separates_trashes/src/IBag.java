public interface IBag<T> {
	/**
	 * @param newItem it takes the item and adds to the bag
	 * @return s boolean
	 */
	 public boolean add(T newItem);
	 /**
	  * @return boolean to see empty or not
	  */
	 public boolean isEmpty();
	 /**
	  * @return boolean to see full or not
	  */
	 public boolean isFull();
	 /**
	  * @param index it takes index of the item and removes
	  * @return s the removed item
	  */
	 public T removeByIndex(int index);
	 /*** 
	  * @return it returns random removed item
	  */
	 public T remove();
	 /**
	  * @param item it takes items and removes
	  * @return s removed item
	  */
	 public T remove(T item);
	 /**
	  * @param item it takes the item and measures how many we have
	  * @return s how many we have
	  */
	 public int getItemCount(T item);
	 /**
	  * @param item it finds this item's index
	  * @return it returns its index
	  */
	 public int getIndexOf(T item);
	 /**
	  * @param item it takes and look we have or not
	  * @return if we have true if not false
	  */
	 public boolean contains(T item);
	 /**
	  * it prints the items we have
	  */
	 public void displayItems();
	 /**
	  * it clears the bags
	  */
	 public void dump(); 
	 /**
	  * @param targetBag this is bag that we are sending the items
	  * @param item which item we sent
	  * @return if we couldn't do false else true
	  */
	 public boolean transferTo(IBag<T> targetBag, T item);
}
