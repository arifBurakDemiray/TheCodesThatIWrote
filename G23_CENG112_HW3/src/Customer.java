
public class Customer implements ICustomer{
	private int priority;
	private String type;
	public Customer() {
		this.priority=0;
		this.type=null;
	}
	public Customer(int onem,String tip) {
		this.priority=onem;
		this.type=tip;
	}
	public String getType() {
		return this.type;
	}
	public int getPriority() {
		return this.priority;
	}
	public String toString() {
		 return getClass().getName()+"@"+Integer.toHexString(hashCode());
	}
}
