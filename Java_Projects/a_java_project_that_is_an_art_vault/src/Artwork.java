//Here our artwork class.
public abstract class Artwork implements IComparable,ITradable,ISearchable{//we implemented the requisite interfaces.
	private String name;
	private String style;
	/**
	 * Basic constructor of Artwork. Super constructor of sculpture,painting and architecture.
	 * @param name
	 * @param style
	 */
	public Artwork(String name,String style) {//we determined the getter,setter and constructor.
		this.setName(name);
		this.style = style;}
	/**
	 * Each artwork has different costs for their attributes.
	 * @returns the cost of artwork.
	 */
	public abstract double calculateCost();
	/**
	 * @returns the name of artwork.
	 */
	public String getName() {
		return name;}
	/**
	 * @returns the style of artwork.
	 */
	public String getStyle() {
		return style;}
	/**
	 * Changes the style of this artwork.
	 * @param style
	 */
	public void setStyle(String style) {
		this.style = style;}
	/**
	 * Changes the name of this artwork.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;}
	
	@Override
	// Same as person class. We explained detailed in person class. if zero means equal.
	//if 1 means greater. if -1 means lesser.
	public int compareTo(Object object,Object object1) throws Exception{
		if(object instanceof String) {
			String objectString = (String) object;
			String object2String = (String) object1;
			for (int i = 0; i < object2String.length() &&  i < objectString.length(); i++) { 
	        if ((int)object2String.charAt(i) ==  
	            (int)objectString.charAt(i)) { 
	            continue; }  
	        else { 
	            if((object2String.charAt(i)-objectString.charAt(i))>0)
	            	return -1;
	            else
	            	return 1;}} 
	    if (object2String.length() < objectString.length()) { 
	        return -1;}  
	    else if (object2String.length() > objectString.length()) { 
	        return 1;} 
	    else { 
	        return 0;} }
		else {
			throw new Exception("They have not same type.");}}
	
	@Override
	//Takes seller and buyer.
	public void tradeToBuyer(Seller seller,Buyer buyer) {
		seller.getSellerCollection().getPrivateArray().remove(this);//removes the artwork that is sold.
		buyer.getBuyerCollection().getPrivateArray().add(this);//adds the artwork that is bought.
		buyer.getBuyerWallet().giveMoney((int)this.calculateCost());//gives money from buyer..
		seller.getSellerWallet().addMoney((int)this.calculateCost());}//adds money to the seller.
	
	@Override//We can search by name and by style at the same time.
	public boolean isSearch(String name) {
		if(name.equals(this.name) || name.equals(this.style))//if key equals to one of them returns true.
			return true;
		else
			return false;}}
