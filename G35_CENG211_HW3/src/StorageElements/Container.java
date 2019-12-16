package StorageElements;
import java.util.Collection;
import java.util.Stack;
public class Container<T> implements IContainer<T> {
	private double capacity;
	private String place= "Factory";// they are all at the factory at the beginning.
	private Stack<T> container = new Stack<T>();
	public Container(int capacity) {
		this.capacity=capacity;
	}
	public void addItem(T item) {
		container.push(item);
	}
	public void takeItem() {
		container.pop();
	}
	public double getCapacity() {
		double temp = capacity;
		return temp;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	public Stack<T> getContainer(){
		@SuppressWarnings("unchecked")
		Stack<T> temp = (Stack<T>) container.clone();
		return temp;
	}
	public void setContainer(Collection<? extends T> list) {
		this.container.clear();
		this.container.addAll(list);
	}
	public void empty() {
		this.capacity=1000;
		this.container.clear();
	}
	public void addCap(double litre) {
		this.capacity+=litre;
	}
	public void removCap(double litre) {
		this.capacity-=litre;
	}
	public double lookFor(double lt) {
		return this.capacity-lt;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}

}
