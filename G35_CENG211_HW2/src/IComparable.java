//Here our IComparable.
public interface IComparable {
	/**
	 * it take an object and then compares with this object
	 * @return if this greater than object than returns 1 else -1, equality 0.
	 * @throws Exception if they are not same type.
	 */
	public int compareTo(Object object,Object object1) throws Exception;
}