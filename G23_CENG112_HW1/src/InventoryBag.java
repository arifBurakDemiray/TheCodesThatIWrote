public class InventoryBag<T> implements IBag<T>{
	private T[] bagInventory;
	private int size=500;
	private int enteredValues;
	public InventoryBag() {
		@SuppressWarnings("unchecked")
		T[] tempBag=(T[]) new Object[size];
		bagInventory=tempBag;
		enteredValues=0;}
	public boolean add(T newItem) {
		boolean result=true;
		if(isFull()) {
			result=false;}
		else {
			bagInventory[enteredValues]=newItem;
			enteredValues++;}
		return result;}
	public boolean isEmpty() {
		return enteredValues==0;}
	public boolean isFull() {
		return enteredValues>=bagInventory.length;}
	public T removeByIndex(int index) {
		T result=null;
		if(!isEmpty() && index>=0) {
			result=bagInventory[index];
			bagInventory[index]=bagInventory[enteredValues-1];
			bagInventory[enteredValues-1]=null;
			enteredValues--;}
		return result;}
	public T remove() {
		T result=null;
		if(enteredValues>0) {
			result=bagInventory[enteredValues-1];
			bagInventory[enteredValues-1]=null;
			enteredValues--;}
		return result;}
	public T remove(T item) {
		int index = getIndexOf(item);
		T result=removeByIndex(index);
		return result;}
	public int getItemCount(T item) {
		int itemCounter=0;
		for (int index=0;index<enteredValues;index++) {
			if(item.equals(bagInventory[index])) {
				itemCounter++;}}
		return itemCounter;}
	public int getIndexOf(T item) {
		int placeOfItem=-1;
		boolean place=false;
		int index=0;
		while(!place && (index < enteredValues))
			{
			if (item.equals(bagInventory[index])){
				place=true;
				placeOfItem=index;}
			index++;}
		return placeOfItem;}
	public boolean contains(T item) {
		return getIndexOf(item)>-1;}
	public void displayItems() {
		int i=0;
		while(bagInventory[i]!=null) {
			if(!bagInventory[i].equals(bagInventory[i+1]))
				System.out.println(bagInventory[i]);
			i++;
		}}
	public void dump() {
		while(!isEmpty()) {
			remove();
		}
	}
	public boolean transferTo(IBag<T> targetBag, T item) {
		if(this.contains(item)) {
			targetBag.add(item);
			this.remove(item);
			return true;
		}
		else {
			return false;
		}
		
	}
}
