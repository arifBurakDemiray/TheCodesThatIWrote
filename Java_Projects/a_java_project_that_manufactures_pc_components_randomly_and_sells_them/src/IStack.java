public interface IStack<T> {
	/**
	 * 
	 * @param entry it takes entry and adds to the stack
	 */
	public void push(T entry);
	/**
	 * 
	 * @return it returns the top entry
	 */
	public T peek();
	/**
	 * 
	 * @return it removes the top entry and returns it
	 */
	public T pop();
	/**
	 * 
	 * @return if it is empty it returns true else false
	 */
	public boolean isEmpty();
	/**
	 * it clears the stack
	 */
	public void clear();
	
	

}
