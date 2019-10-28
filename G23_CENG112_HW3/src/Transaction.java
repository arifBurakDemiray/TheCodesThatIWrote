
public class Transaction<T> {
	private int id; // unique id in [1,1000]
	private ICustomer customer; // the request owner
	private int occupation; // needed time for transaction
	private int waiting; // needed time for previous transactions
	private Transaction<T> next;
	public Transaction(int ID,ICustomer alýcý, int surec,int beklenen) {
		this(ID,alýcý,surec,beklenen,null);
	}
	public Transaction(int ID,ICustomer alýcý, int surec,int beklenen, Transaction<T> post) {
		this.id=ID;
		this.customer=alýcý;
		this.next=post;
		this.occupation=surec;
		this.waiting=beklenen;
	}
	public void setNextT(Transaction<T> post) {
		this.next=post;
	}
	public Transaction<T> getNextT(){
		return this.next;
	}
	public int getID() {
		return this.id;
	}
	public void setID(int ID) {
		this.id=ID;
	}
	public ICustomer getCus() {
		return this.customer;
	}
	public void setCus(ICustomer alýcý) {
		this.customer=alýcý;
	}
	public int getOccupation() {
		return occupation;
	}
	public void setOccupation(int occupation) {
		this.occupation = occupation;
	}
	public int getWaiting() {
		return waiting;
	}
	public void setWaiting(int waiting) {
		this.waiting = waiting;
	}
	
}
