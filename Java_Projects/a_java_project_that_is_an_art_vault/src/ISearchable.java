//Here our ISearchable.
public interface ISearchable {
	/**
	 * @param name takes String that can be artist name,nationality,style,period, all kind of strings.
	 * @returns true,if they are same. if not, returns false
	 */
	public boolean isSearch(String name);
}