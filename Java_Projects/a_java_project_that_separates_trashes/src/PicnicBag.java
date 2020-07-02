
public class PicnicBag<T> implements IBag<T>{
	private T[] bagPicnic;
	private int enteredValues;
	public PicnicBag(int size) {
		@SuppressWarnings("unchecked")
		T[] tempBag=(T[]) new Object[size];
		bagPicnic=tempBag;
		enteredValues=0;}
	public boolean add(T newItem) {
		boolean result=true;
		if(isFull()) {
			result=false;}
		else {
			bagPicnic[enteredValues]=newItem;
			enteredValues++;}
		return result;}
	public boolean isEmpty() {
		return enteredValues==0;}
	public boolean isFull() {
		return enteredValues>=bagPicnic.length;}
	public T removeByIndex(int index) {
		T result=null;
		if(!isEmpty() && index>=0) {
			result=bagPicnic[index];
			bagPicnic[index]=bagPicnic[enteredValues-1];
			bagPicnic[enteredValues-1]=null;
			enteredValues--;}
		return result;}
	public T remove() {
		T result=null;
		if(enteredValues>0) {
			result=bagPicnic[enteredValues-1];
			bagPicnic[enteredValues-1]=null;
			enteredValues--;}
		return result;}
	public T remove(T item) {
		int index = getIndexOf(item);
		T result=removeByIndex(index);
		return result;}
	public int getItemCount(T item) {
		int itemCounter=0;
		for (int index=0;index<enteredValues;index++) {
			if(item.equals(bagPicnic[index])) {
				itemCounter++;}}
		return itemCounter;}
	public int getIndexOf(T item) {
		int placeOfItem=-1;
		boolean place=false;
		int index=0;
		while(!place && (index < enteredValues))
			{
			if (item.equals(bagPicnic[index])){
				place=true;
				placeOfItem=index;}
			index++;}
		return placeOfItem;}
	public boolean contains(T item) {
		return getIndexOf(item)>-1;}
	public void displayItems() {
		int i=0;
		while(bagPicnic[i]!=null) {
			if(!bagPicnic[i].equals(bagPicnic[i+1]))
				System.out.println(bagPicnic[i]);
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
			return true;
		}
		else {
			return false;
		}
		
	}
	public boolean consume(T item) {
		if(this.contains(item)) {
			this.remove(item);
			return true;
		}
		else
			return false;
	}


}
