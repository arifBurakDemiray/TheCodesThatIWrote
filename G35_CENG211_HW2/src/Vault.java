import java.util.ArrayList;
//Here our vault class.
public class Vault {
	private ArrayList<Object> listOfArtworks;//Artwork list
	private ArrayList<Object> listOfPerson;//Person list
	//why we are holding of type object because of casting problems and working better with other classes.
	
	/**
	 * Basic contructor of vault class.
	 * @param loa list of Artworks
	 * @param lop list of Persons
	 */
	public Vault(ArrayList<Object> loa,ArrayList<Object> lop) {//basic constructor.
		this.listOfArtworks=loa;
		this.listOfPerson=lop;}
	
	//getters of lists.
	/**
	 * @returns the list of Artwork.
	 */
	public ArrayList<Object> getListOfArtworks() {
		return listOfArtworks;}
	/**
	 * @returns the list of Person.
	 */
	public ArrayList<Object> getListOfPerson() {
		return listOfPerson;}
	
	//this method returns the type Object artwork list to Artwork artwork list.
	/**
	 * @return the list that's type is object change to the type of artwork list.
	 */
	public ArrayList<Artwork> getListArtworkSpecifically() {
		ArrayList<Artwork> temp = new ArrayList<Artwork>();
		for(Object objects: listOfArtworks) {
			if(objects instanceof Artwork) {
				temp.add((Artwork)objects);}}
		return temp;}
	//this method has same logic as above. changes from Object person list to Person person list.
	/**
	 * @returns the list that's type is object change to the type of person list.
	 */
	public ArrayList<Person> getListPersonSpecifically() {
		ArrayList<Person> temp = new ArrayList<Person>();
		for(Object objects: listOfPerson) {
			if(objects instanceof Person) {
				temp.add((Person)objects);}}
		return temp;}
}