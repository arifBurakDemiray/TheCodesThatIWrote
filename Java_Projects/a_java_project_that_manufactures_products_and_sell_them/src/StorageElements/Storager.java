package StorageElements;
import java.util.LinkedList;
import java.util.Queue;

public class Storager<T> implements IStorager<T>{
	private double capacity;
	private Queue<T> queue;
	public Storager() {
		this(50);
	}
	public Storager(int capacity) {
		this.setCapacity(capacity);
		setQueue(new LinkedList<T>());
	}
	public void addQ(T item) {
		queue.add(item);
	}
	public void removQ() {
		queue.poll();
	}
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	public Queue<T> getQueue() {
		return queue;
	}
	public void setQueue(Queue<T> queue) {
		this.queue = queue;
	}
	public void addCap(double litre) {
		this.capacity+=litre;
	}
	public void removCap(double litre) {
		this.capacity-=litre;
	}
	public boolean isEmpty() {
		return 	this.queue.isEmpty();
	}
	public T seeFirst() {
		return queue.peek();
	}
	public double lookFor(double lt) {
		return this.capacity-lt;
	}
}
