package Storage;
import java.util.HashMap;
import java.util.Map;
//we implemented our inventory with Hash map.
public abstract class Inventory<T> implements IInventory<T> {
	protected Map<T,Integer> depo;
	public Inventory() {
		this.depo=new HashMap<T,Integer>();
	}
	public void addElementToInventory(T item,int value) {
		this.depo.put(item,value);
	}
	public boolean removeElementFromInventory(T item,int value) {
		return this.depo.remove(item,value);
		
	}
	public  Map<T, Integer> getDepo() {
		return this.depo;
	}
	public void setDepo(Map<T, Integer> depo) {
		this.depo = depo;
	}
	public int getItem(T item) {
		return this.depo.get(item);
	}
	public boolean isEmpty() {
		return this.depo.isEmpty();
	}
	public void clear() {
		this.depo.clear();
	}
	public boolean contains(T item) {
		return this.depo.containsKey(item);
	}
	public int size() {
		return this.depo.size();
	}
	public String toString() {
		return this.depo.toString();
	}
}
