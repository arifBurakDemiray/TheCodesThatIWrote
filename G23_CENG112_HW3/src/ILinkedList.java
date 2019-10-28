
public interface ILinkedList<T> {
	/**
	 * 
	 * @param date it takes this and returns the TQ of this date
	 * @return the TQ of this date
	 */
	public TransactionQueue<T> getTQ(String date);
	/**
	 * 
	 * @param TQ it adds this TQ
	 */
	public void addTQ(TransactionQueue<T> TQ);
	/**
	 * 
	 * @param date it takes this date and changes the date of TQ
	 * @param TQ the tq that be changed its date
	 * @return it is done or not
	 */
	public boolean updateTQ(String date, TransactionQueue<T> TQ);
	/**
	 * 
	 * @param TQ it inserts and takes this TQ
	 */
	public void insertTo(TransactionQueue<T> TQ);
	/**
	 * 
	 * @param date it takes this date and finds its TQ
	 * @return returns the removed TQ
	 * @throws if empty it throws
	 */
	public TransactionQueue<T> removeTQ(String date) throws Exception;
	/**
	 * 
	 * @return empty or not
	 */
	public boolean isEmpty();
	/**
	 * 
	 * @return it clears the list
	 */
	public boolean clear();
	/**
	 * 
	 * @return it converts to the string
	 */
	public String toString();
	/**
	 * 
	 * @return it returns the length of list
	 */
	public int getLength();
	/**
	 * this method prints the list
	 */
	public void readQueue();

}
