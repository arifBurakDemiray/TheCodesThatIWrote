

public class TransactionQueue<T> implements IQueue<T> {

	private Transaction<T> head;
	private Transaction<T> last;
	private int queueLength;
	private int totalWaitingTime;
	private String date;
	private TransactionQueue<T> next;
	public TransactionQueue(String date) {
		this.head=null;
		this.last=null;
		this.setNext(null);
		this.queueLength=0;
		this.setTotalWaitingTime(0);
		this.date=date;
	}
	public void insert(Transaction<T> T) {
			boolean done =false;
			Transaction<T> tempTrans1 = null;
			Transaction<T> tempTrans2;
			tempTrans2=getReferenceTo(head);
			while(!done) {
				if(tempTrans2==null) {
					enqueue(T);
					done=true;}
				else if(tempTrans2.getCus().getPriority()>T.getCus().getPriority()) {
					if(tempTrans1==null) {
						T.setNextT(head.getNextT());
						this.head=T;
						this.queueLength++;
						this.totalWaitingTime+=T.getOccupation();
						done=true;
					}
					else {
						T.setWaiting(this.totalWaitingTime);
						T.setNextT(tempTrans2);
						tempTrans1.setNextT(T);
						this.queueLength++;
						this.totalWaitingTime+=T.getOccupation();
						done=true;
					}
				}
				else if(tempTrans2.getCus().getPriority()==T.getCus().getPriority()) {
					tempTrans1=getReferenceTo(tempTrans2);
					tempTrans2=getReferenceTo(tempTrans2.getNextT());
				}
				else{
					tempTrans1=getReferenceTo(tempTrans2);
					tempTrans2=getReferenceTo(tempTrans2.getNextT());
				}
			}
		
	}
	public Transaction<T> getReferenceTo(Transaction<T> entry){
		boolean found=false;
		Transaction<T> current = head;
		while(!found && (current!=null)) {
			if(entry==current)
				found=true;
			else
				current=current.getNextT();
		}
		return current;
	}
	public Transaction<T> remove() throws Exception {
		if(!isEmpty()) {
			queueLength--;
			totalWaitingTime-=head.getOccupation();
			Transaction<T> front = getFront();
			head.setCus(null);
			head.setID(0);
			head.setOccupation(0);
			head.setWaiting(0);
			head=head.getNextT();
			if(head==null)
				last=null;
			return front;}
		else
			throw new Exception("No transaction exist");}
	
	public boolean isEmpty() {
		return this.head==this.last;}
	public boolean clear() throws Exception{
		this.head=null;
		this.last=null;
		this.queueLength=0;
		this.totalWaitingTime=0;
		this.next=null;
		this.date=null;
		return true;}
		
	
	public String toString() {
		return getClass().getName()+"@"+Integer.toHexString(hashCode());
}
	public void enqueue(Transaction<T> ekle) {
		Transaction<T> newTrans = new Transaction<T>(ekle.getID(),ekle.getCus(),ekle.getOccupation(),ekle.getWaiting(),null);
		if(isEmpty())
			this.head=newTrans;
		else
			this.last.setNextT(newTrans);
		this.last=newTrans;
		this.queueLength++;
		this.totalWaitingTime+=ekle.getOccupation();
	}
	@SuppressWarnings("unchecked")
	public T dequeue() {
		if(isEmpty())
			return null;
		else {
			queueLength--;
			totalWaitingTime-=head.getOccupation();
			Transaction<T> front = getFront();
			head.setCus(null);
			head.setID(0);
			head.setOccupation(0);
			head.setWaiting(0);
			head=this.head.getNextT();
			if(head==null)
				last=null;
			return (T) front;}
	}

	public Transaction<T> getFront() {
		if(isEmpty())
			return null;
		else
			return head;
	}
	public void setLast(Transaction<T> T) {
		this.last=T;
	}
	public void setQueueLength(int i) {
		this.queueLength=i;
	}
	public void setHead(Transaction<T> T) {
		this.head=T;
	}
	public Transaction<T> getLast() {
		return this.last;
	}
	public int getQueueLength() {
		return this.queueLength;
	}
	public int getTotalWaitingTime() {
		return this.totalWaitingTime;
	}
	public void setTotalWaitingTime(int totalWaitingTime) {
		this.totalWaitingTime = totalWaitingTime;
	}
	public TransactionQueue<T> getNext() {
		return this.next;
	}
	public void setNext(TransactionQueue<T> next) {
		this.next = next;
	}
	public String getDate() {
		return this.date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Transaction<T> getHead() {
		return this.head;
	}

}
