public interface IQueue<T> {
	/**
	 * 
	 * @param newEntry it takes the new entry and adds to the queue
	 */
	public void enqueue(T newEntry);
	/**
	 * 
	 * @return it removes first of the queue and returns it
	 */
	public T dequeue();
	/**
	 * 
	 * @return it returns the first element of queue
	 */
	public T getFront();
	/**
	 * 
	 * @return it look empty or not
	 */
	public boolean isEmpty();
	/**
	 * it clears the queue
	 */
	public void clear();

}
