package StorageElements;
import java.util.Queue;

public interface IStorager<T> {
	/**
	 * 
	 * @param item that is going to add to the queue
	 */
	public void addQ(T item);
	/**
	 * removing the head.
	 */
	public void removQ();
	/**
	 * 
	 * @returns the capacity of that storager.
	 */
	public double getCapacity();
	/**
	 * 
	 * @param capacity that our new capacity.
	 */
	public void setCapacity(double capacity);
	/**
	 * 
	 * @returns the queue of that.
	 */
	public Queue<T> getQueue();
	/**
	 * 
	 * @param queue that is our new queue.
	 */
	public void setQueue(Queue<T> queue);
	/**
	 * 
	 * @param litre that will be added to the our capacity.
	 */
	public void addCap(double litre);
	/**
	 * 
	 * @param litre that is removed from our capacity.
	 */
	public void removCap(double litre);
	/**
	 * 
	 * @returns true if empty else false.
	 */
	public boolean isEmpty();
	/**
	 * 
	 * @returns the head of the queue.
	 */
	public T seeFirst();
	/**
	 * 
	 * @param lt that is we have that amount capacity.
	 * @returns true if we have enough else false.
	 */
	public double lookFor(double lt);
}
