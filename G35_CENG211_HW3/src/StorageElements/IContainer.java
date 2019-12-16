package StorageElements;
import java.util.Collection;
import java.util.Stack;
public interface IContainer<T> {
	/**
	 * this container implementation is based on stacks.
	 * @param item is gonna be added to that container.
	 */
	public void addItem(T item);
	/**
	 * this method does takes the last added element.
	 */
	public void takeItem();
	/**
	 * 
	 * @returns the capacity of container.
	 */
	public double getCapacity();
	/**
	 * 
	 * @param capacity that is the our new capacity.
	 */
	public void setCapacity(double capacity);
	/**
	 * 
	 * @returns the stack of that container.
	 */
	public Stack<T> getContainer();
	/**
	 * 
	 * @param list that is our new stack.
	 */
	public void setContainer(Collection<? extends T> list);
	/**
	 * this method empty the our collection.
	 */
	public void empty();
	/**
	 * Adding capacity means last item is removed.
	 * @param litre that added to the our capacity.
	 */
	public void addCap(double litre);
	/**
	 * Removing capacity means new item added.
	 * @param litre that removed from our capacity.
	 */
	public void removCap(double litre);
	/**
	 * 
	 * @param lt to look we have enough space.
	 * @returns greater than zero if we have enough space for new item, otherwise we do not have enough space..
	 */
	public double lookFor(double lt);
	/**
	 * 
	 * @returns the place of the container.
	 */
	public String getPlace();
	/**
	 * 
	 * @param place that is our new place.
	 */
	public void setPlace(String place);
}
