//In that class we have architects.
public class Architect extends Person {
	/**
	 * Basic constructor of Architect class.
	 * @param name
	 * @param birtdate
	 * @param deathdate
	 * @param country
	 */
	public Architect(String name,String birtdate,String deathdate,String country) {
		super(name, birtdate, deathdate, country);	//its constructor same as super class
	}                                               //because it does not have any different thing from super.

}
