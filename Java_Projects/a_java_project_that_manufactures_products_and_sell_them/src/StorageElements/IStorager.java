package StorageElements;
import java.util.Queue;

public interface IStorager<T> {
	public void addQ(T item);
	public void removQ();
	public double getCapacity();
	public void setCapacity(double capacity);
	public Queue<T> getQueue();
	public void setQueue(Queue<T> queue);
	public void addCap(double litre);
	public void removCap(double litre);
	public boolean isEmpty();
	public T seeFirst();
	public double lookFor(double lt);
}
