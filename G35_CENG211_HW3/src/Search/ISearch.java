package Search;

public interface ISearch<T> {
	/**
	 * this method does search for same child classes in a list. 
	 * @param list that is going to iterate through.
	 * @param object that is searched.
	 * @returns true if there are same classes. false if we have not same classes.
	 */
	public boolean searchsForEqualClasses(T list,Object object);
}
