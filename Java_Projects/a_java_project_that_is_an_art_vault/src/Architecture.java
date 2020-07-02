import java.util.ArrayList;
//Here our architecture class.
public class Architecture extends Artwork {
	private double dimension2;
	private double dimension3;
	private ArrayList<Architect> architect;// it holds ArrayList because some Architectures have 
	private double dimension1;              //more than one architect.
	/**
	 * Basic constructor of Architecture class.
	 * @param name
	 * @param style
	 * @param dimension1
	 * @param dimension2
	 * @param dimension3
	 * @param architect(s)
	 */
	public Architecture(String name,String style,double dimension1,double dimension2,double dimension3,ArrayList<Architect> architect) {
		super(name,style);//its constructor same as its super class
		this.dimension1 = dimension1;//except it has 4 extra attribute contrast to its super.
		this.dimension2 = dimension2;
		this.dimension3 = dimension3;
		this.architect = architect;}
	
	//Here its getters and setters.
	//We only need getters of dimensions.
	//So we did not call their setters for privacy.
	/**
	 * @returns dimension 1
	 */
	public double getDimension1() {
		return dimension1;}
	/**
	 * @returns dimension 2
	 */
	public double getDimension2() {
		return dimension2;}
	/**
	 * @returns dimension 3
	 */
	public double getDimension3() {
		return dimension3;}
	/**
	 * @returns architect(s)
	 */
	public ArrayList<Architect> getArchitect() {
		return architect;}
	/**
	 * changes this' architect list.
	 * @param architect
	 */
	public void setArchitect(ArrayList<Architect> architect) {
		this.architect = architect;
	}
	//Here its special calculate cost method.
	//extends from Artwork. it is abstract method.
	@Override
	public double calculateCost() {                         //We have different prices for other two styles.
			double price = 0;
			if (this.getStyle().equals("Gothic")) {
				price = (1*dimension1*dimension2*dimension3);
			}
			else if(this.getStyle().equals("Baroque")) {
				price = (0.8*dimension1*dimension2*dimension3);
			}
			else {
				price = (0.6*dimension1*dimension2*dimension3);
			}
			return price;
		
	}
	//Here isTradable method from ITradable interface. Comes from its super.
	@Override
	public boolean isTradable(){                  //in architecture we can not trade the Renaissance style.
		if(this.getStyle().equals("Renaissance"))
			return false;
		else
			return true;}}
