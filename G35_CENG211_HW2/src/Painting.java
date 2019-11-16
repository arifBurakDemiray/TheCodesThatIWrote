//here our painting class.
public  class Painting extends Artwork{
	private Artist artist;
	private int dimension1;
	private int dimension2;
	/**
	 * Basic constructor of Painting.
	 * @param name
	 * @param style
	 * @param artist
	 * @param dimension1
	 * @param dimension2
	 */
	public Painting(String name,String style,Artist artist,int dimension1,int dimension2) {
		super(name, style);  //super constructor extra artist and dimensions. 
		this.artist = artist;
		this.dimension1 = dimension1;
		this.dimension2 = dimension2;}
	
	//like other artwork subclasses it has calculate cost.
	//same logic it has different costs for 2 styles.
	@Override
	public double calculateCost() {
		double price = 0;
		if (this.getStyle().equals("Renaissance")) {
			price = (7*dimension1*dimension2);}
		else if(this.getStyle().equals("Baroque")) {
			price = (5.5*dimension1*dimension2);}
		else {
			price = (4.5*dimension1*dimension2);}
		return price;}
	
	//getters and setters.
	/**
	 * @returns dimension 2
	 */
	public int getDimension2() {
		int temp = new Integer(dimension2);
		return temp;}
	/**
	 * @returns dimension 1
	 */
	public int getDimension1() {
		int temp = new Integer(dimension1);
		return temp;}
	/** 
	 * @returns Artist of this Painting.
	 */
	public Artist getArtist() {
		return artist;}
	/**
	 * changes this' class artist.
	 * @param artist
	 */
	public void setArtist(Artist artist) {
		this.artist = artist;}
	
	//like other artwork subclasses we have non tradable Style for painting class.
	@Override
	public boolean isTradable(){
		if(this.getStyle().equals("Gothic"))
			return false;
		else
			return true;}
}