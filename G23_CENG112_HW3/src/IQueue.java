
public interface IQueue<T> {
	/**
	 * 
	 * @param newEntry it takes the new entry and adds to the queue
	 */
	public void enqueue(Transaction<T> ekle);
	/**
	 * 
	 * @param <T>
	 * @return it removes first of the queue and returns it
	 */
	public  T dequeue();
	/**
	 * 
	 * @return it returns the first element of queue
	 */
	public Transaction<T> getFront();
	/**
	 * 
	 * @return it look empty or not
	 */
	public boolean isEmpty();
	/**
	 * 
	 * @param T it takes this and inserts to the queue
	 */
	public void insert(Transaction<T> T);
	/**
	 * 
	 * @return it return the date
	 */
	public String getDate() ;
	/**
	 * 
	 * @param date it changes the date to this
	 */
	public void setDate(String date);
	/**
	 * 
	 * @return it converts the queue to string
	 */
	public String toString();
	/**
	 * 
	 * @return it gives us the total waiting time
	 */
	public int getTotalWaitingTime();
	/**
	 * 
	 * @return it return the last element
	 */
	public Transaction<T> getLast();
	/**
	 * 
	 * @return it gives us the queue length
	 */
	public int getQueueLength();
	/**
	 * 
	 * @param totalWaitingTime it converts normal total waiting time to this
	 */
	public void setTotalWaitingTime(int totalWaitingTime);
	/**
	 * 
	 * @return it gives us the head
	 */
	public Transaction<T> getHead();
	/**
	 * 
	 * @param next it changes the next
	 */
	public void setNext(TransactionQueue<T> next);
	/**
	 * 
	 * @return it gives us the next
	 */
	public TransactionQueue<T> getNext();
	/**
	 * 
	 * @param entry it takes and entry for referencing
	 * @return returns the referenced version of this
	 */
	public Transaction<T> getReferenceTo(Transaction<T> entry);
	/**
	 * 
	 * @return it removes the elements
	 * @throws if empty
	 */
	public Transaction<T> remove() throws Exception;
	/**
	 * it clears the queue
	 * @throws Exception 
	 */
	public boolean clear() throws Exception;
	/**
	 * 
	 * @param T it takes this end changes its last element
	 */
	public void setLast(Transaction<T> T);
	/**
	 * 
	 * @param i it changes its length
	 */
	public void setQueueLength(int i);
	/**
	 * 
	 * @param T it changes its head
	 */
	public void setHead(Transaction<T> T);



}
