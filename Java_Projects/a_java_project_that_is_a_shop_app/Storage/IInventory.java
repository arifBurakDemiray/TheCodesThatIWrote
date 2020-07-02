package Storage;
import java.util.Map;

public interface IInventory<T> {
	/**
	 * adds element to the our inventory.
	 * @param item key
	 * @param value value
	 */
	public void addElementToInventory(T item,int value);
	/**
	 * 
	 * @param item key
	 * @param value
	 * @return s true if we able to remove.
	 */
	public boolean removeElementFromInventory(T item,int value);
	/**
	 * 
	 * @return s our depo
	 */
	public  Map<T, Integer> getDepo();
	/**
	 * 
	 * @param depo new depo
	 */
	public void setDepo(Map<T, Integer> depo) ;
	/**
	 * 
	 * @param item the key
	 * @return s it value
	 */
	public int getItem(T item);
	/**
	 * 
	 * @return true if empty
	 */
	public boolean isEmpty();
	/**
	 * it clears the our inventory
	 */
	public void clear();
	/**
	 * 
	 * @param item key
	 * @return s true if we have
	 */
	public boolean contains(T item);
	/**
	 * 
	 * @return s the size of the inventory
	 */
	public int size();
	/**
	 * 
	 * @return s the string of inventory
	 */
	public String toString();
	
}
