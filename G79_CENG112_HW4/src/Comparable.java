
public interface Comparable<T> {
	/**
	 * @param other that is going to be compared.
	 * @return 's integer if the object greater than other it returns integer that is greater than 0,
	 * it return 0 if they are equal and it return below zero if other is greater than object.
	 */
	public int compareTo(T other);
}
