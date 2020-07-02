//Here our sculpture class. Extends Artwork.
public class Sculpture extends Artwork {
	private Artist artist;
	private String material;
	private int weight;
	
	/**
	 * Basic constructor of Sculpture class.
	 * @param name
	 * @param style
	 * @param artist
	 * @param material
	 * @param weight
	 */
	public Sculpture(String name,String style,Artist artist,String material,int weight) {
		super(name, style);//super constructor and extra attributes.
		this.artist = artist;
		this.material = material;
		this.weight = weight;}
	
	//Here specific calculate cost method for sculpture.
	@Override
	public double calculateCost() {
		double price = 0;
		if (this.getMaterial().equals("Marble")) {
			price = (this.getWeight()*15);}
		else if(this.getMaterial().equals("Bronze")) {
			price = (180*this.getWeight());}
		return price;}
	
	//getters and setters
	/**
	 * @returns the artist of this.
	 */
	public Artist getArtist() {
		return artist;}
	/**
	 * Changes the artist of this.
	 * @param artist
	 */
	public void setArtist(Artist artist) {
		this.artist = artist;}
	/**
	 * @returns the material of this Sculpture.
	 */
	public String getMaterial() {
		return material;}
	/**
	 * Changes the material of this Sculpture.
	 * @param material
	 */
	public void setMaterial(String material) {
		this.material = material;}
	/**
	 * @returns the weight of this sculpture.
	 */
	public int getWeight() {
		return weight;}
	/**
	 * Changes the weight of this.
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;}
	
	//here our tradable method
	//in sculpture we can not trade the sculptures that of style Baroque.
	@Override
	public boolean isTradable(){
		if(this.getStyle().equals("Baroque"))//if equal to baroque returns false.
			return false;
		else
			return true;}
}