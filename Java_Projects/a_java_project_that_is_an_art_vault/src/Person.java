//This class is our super Person class.
public abstract class Person implements IComparable,ISearchable{
	private String name;
	private String birthdate;
	private String deathdate;
	private String country;
	/**
	 * Here super constructor of artist and architecture. Basic constructor of Person class.
	 * @param name
	 * @param birtdate
	 * @param deathdate
	 * @param country
	 */
	public Person(String name,String birtdate,String deathdate,String country) {//here our super constructor.
		this.name =name;
		this.birthdate = birtdate;
		this.deathdate = deathdate;
		this.country = country;}
	
	//getters and setters.
	/**
	 * @returns the name of person
	 */
	public String getName() {
		return name;}
	/**
	 * Changes the name of the person.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;}
	/**
	 * @returns the birth date of person.
	 */
	public String getBirthdate() {
		return birthdate;}
	/**
	 * Changes the birth date of this person.
	 * @param birtdate
	 */
	public void setBirthdate(String birtdate) {
		this.birthdate = birtdate;}
	/**
	 * @returns the death date of this person.
	 */
	public String getDeathdate() {
		return deathdate;}
	/**
	 * Changes the death date of this person.
	 * @param deathdate
	 */
	public void setDeathdate(String deathdate) {
		this.deathdate = deathdate;}
	/**
	 * @returns the nationality of this person.
	 */
	public String getCountry() {
		return country;}
	/**
	 * Changes this person's nationality.
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;}

	// 1 means the first object greater than second object
	// -1 means the first object smaller than second object
	// 0 means they are equal to each other.	
	@Override
	public int compareTo(Object object,Object object1) throws Exception{//Our compare to takes two arguments.
		if(object instanceof String) {//if they are of type string.
			String objectString = (String) object;//cast to string
			String object2String = (String) object1;
			for (int i = 0; i < object2String.length() &&  i < objectString.length(); i++) {// the middle attribute of the for means which one has minimum length.
	        if ((int)object2String.charAt(i) ==  (int)objectString.charAt(i)) {//here looks for the places at the character table. 
	            continue; }  //if they have same place means the letters are same.
	        else { //if they are not same.
	            if((object2String.charAt(i)-objectString.charAt(i))>0)//if second ones placement greater than first 
	            	return -1;                                       // means first one less than second.
	            else
	            	return 1;} } 
	    if (object2String.length() < objectString.length()) {//if one word has smaller length it means it is in front of other word in the dictionary.
	        return -1;}  //so second one will be greater.
	    else if (object2String.length() > objectString.length()) { // same as above.
	        return 1;} //this time first one greater than second.
	    else { // if they have same length than they are same.
	        return 0;} }
		else {//if they are not of type string.
			throw new Exception("Not Supported Type.");}}

	public boolean isSearch(String name) {//Here our search method. it can look for nationality and name at the same time.
		if(name.equals(this.name) || name.equals(this.country))//equals to name or equals to nationality.
			return true;
		else 
			return false;}
}