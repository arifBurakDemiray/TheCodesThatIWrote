package Items;
// this interface for doing cost calculations.
public interface ICostable {
	/**
	 * calculates the cost of an item that implements.
	 * @returns the cost of that item.
	 */
	public double calculateCost();
	/**
	 * 
	 * @returns the item's cost per liter.
	 */
	public double getPerLCost();
	/**
	 * 
	 * @param perLCost to change its existing cost.
	 */
	public void setPerLCost(double perLCost);
}
