import java.util.ArrayList;
//Here our private collection class.
public class PrivateCollection {
	private ArrayList<Artwork> privateArray;//Private collection is of type Artwork.
	/**
	 * Basic constructor of private collection.
	 */
	public PrivateCollection() {//basic constructor.
		this.privateArray = new ArrayList<Artwork>();}
	
	//getter and setter.
	/**
	 * @returns the artworks of this collection.
	 */
	public ArrayList<Artwork> getPrivateArray() {
		return privateArray;}
	/**
	 * Changes the collection.
	 * @param privateArray
	 */
	public void setPrivateArray(ArrayList<Artwork> privateArray) {
		this.privateArray = privateArray;}
}