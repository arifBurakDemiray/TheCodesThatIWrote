import java.util.ArrayList;
//Here our artist class.
public class Artist extends Person{
	private ArrayList<String> periods;//Some artists have more than one style so we had to create list.
	/**
	 * Basic constructor of Artist
	 * @param name
	 * @param birtdate
	 * @param deathdate
	 * @param country
	 * @param periods
	 */
	public Artist(String name,String birtdate,String deathdate,String country,ArrayList<String> periods) {
		super(name,birtdate,deathdate,country);//super class constructor extra periods list.
		this.periods = periods;}
	/** 
	 * @returns periods of this artist.
	 */
	public ArrayList<String> getPeriods() {
		return periods;//getter
		}
	/**
	 * changes this' periods
	 * @param periods
	 */
	public void setPeriods(ArrayList<String> periods) {
		this.periods = periods;//setter
	}
	@Override//Here we have different isSearch from Person because it had to search for periods.
	public boolean isSearch(String name) {//Takes any string.
		int looker = 0;//looking for do we have same period.
		if(periods!=null && !name.equals(super.getName()) && !name.equals(super.getCountry())) {
			//for looking only period name, period name must not same with name and nationality because in super.isSearch()
			// it is already looks for these attributes.
		for(String string:periods) {//Here for loop for searching through period list.
			if(string.equals(name))
				looker++;//if we have same period looker rises.
		}
		if(looker>0)//if looker greater than one we can think we have same period and we can print this Artist.
			return true;
		else//if looker = 0 means we do not have same period name.
			return false;}
		return super.isSearch(name);//this is for basic search operations for person class.
		}
}
