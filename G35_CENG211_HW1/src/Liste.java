import java.util.Arrays;
// Here our arraylist class that we wrote. It does not have all methods that ArrayList class has.
public class Liste {
	private Object[] liste;//this list holds the elements of this class.
	private int index=0;//this index for knowing the inputs to the Object[] liste.
	private static int kapasite = 50;
	//if user does not inputs any capacity it starts with the capacity we give to the liste.
	public Liste () {
		this(kapasite);
	}
	// if user gives us a capacity integer the class uses this constructor.
	public Liste(int initialCapacity) {
		liste  = (Object[]) new Object[initialCapacity];
	}
	//here our add method. it takes an object and adds to the liste. before adding items 
	// it looks first if capacity full or not if full it increases the capacity of liste.
	public void add(Object newElement) {
		ensureCapacity();
		liste[index]=newElement;
		index++;
		
	}
	//here the capacity increasetor(mean smthng increases smthng) method.
	// for increasing capacity it multiplies current capacity with 2.
	// and it uses the method from Arrays.
	private void ensureCapacity() {
		int capacity=liste.length-1;
		if(index >= capacity) {
			int newCapacity = 2*capacity;
			liste=Arrays.copyOf(liste, newCapacity+1);
		}
	}
	//basic method of the Arrays.
	//it looks for the object that users looking for in here.
	public boolean contains(Object anEntry) {
		boolean found = false;
		int indeks=1;
		while(!found && (indeks <= index)) {//index is size of the list
			if(anEntry.equals(liste[indeks]))// it looks one by one with these loop.
				found = true;
			indeks++;
		}
		return found;
	}
	// here our remove method. Searchs for that elements and nulls it.
	public Object remove(int givenPosition) {
		if((givenPosition >= 1)&&(givenPosition <= index)) {
			assert !isEmpty();
			Object result = liste[givenPosition];
			if(givenPosition < index)
				removeGap(givenPosition);//below we explained that function.
			index--;
			return result;
		}
		else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
		
	}
	//this method removes gaps after the remove method removes one element the removed
	//elements place going to be null so it removes this gap means its slides left the list by that point.
	private void removeGap(int givenPositon) {
		assert (givenPositon >= 1) && (givenPositon < index);
		int removedIndex=givenPositon;
		int lastIndex=index;
		for(int indeks=removedIndex;indeks<lastIndex;indeks++) {
			liste[indeks]=liste[indeks+1];
		}
	}
	//if index==0 means there is no element in the list.
	public boolean isEmpty() {
		return index==0;
	}
	public Object[] getListe() {
		return liste;
	}
	public void setListe(Object[] liste) {
		this.liste = liste;
	}
	//here the clear method. nulling the elements one by one.
	public void clear() {
		for (int  i=0;i<index;i++) {
			liste[i]=null;
			
		}
		index=0;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;}
	//it does bubble sort.
	public void sort() {
		boolean sorted = false;
		double temp;
		while(!sorted) {
			sorted=true;
			for(int i=0;i<liste.length-1;i++) {
				if(liste[i+1]!=null && ((double)liste[i] > (double)liste[i+1])) {
					temp=(double) liste[i];
					liste[i]=liste[i+1];
					liste[i+1]=temp;
					sorted=false;}}}}
}
