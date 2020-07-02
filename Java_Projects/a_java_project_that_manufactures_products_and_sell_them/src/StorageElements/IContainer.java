package StorageElements;
import java.util.Collection;
import java.util.Stack;
public interface IContainer<T> {
	public void addItem(T item);
	public void takeItem();
	public double getCapacity();
	public void setCapacity(double capacity);
	public Stack<T> getContainer();
	public void setContainer(Collection<? extends T> list);
	public void empty();
	public void addCap(double litre);
	public void removCap(double litre);
	public double lookFor(double lt);
	public String getPlace();
	public void setPlace(String place);
}
