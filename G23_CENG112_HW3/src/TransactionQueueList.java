
public class TransactionQueueList<T> implements ILinkedList<T>{
	private TransactionQueue<T> head;
	private int listLength;
	public TransactionQueueList() {
		this.head=null;
		this.listLength=0;
	}
	public TransactionQueue<T> getTQ(String date) {
		TransactionQueue<T> var=head;
		for(int i=0;i<listLength;i++) {
			if(var.getDate()==date)
				return var;
			else
				var=var.getNext();
		}
		return head;}
	public void addTQ(TransactionQueue<T> TQ) {
		if(isEmpty())
			head=TQ;
		else {
			TransactionQueue<T> last=null;
			TransactionQueue<T> now=head;
			for(int i=0;i<listLength;i++) {
				if(i==listLength-1)
					last=now;
				else {
					now=now.getNext();}
			}
			if(!TQ.equals(null))
				last.setNext(TQ);
			
		}
		listLength++;
			
	}
	public boolean updateTQ(String date, TransactionQueue<T> TQ) {
		if(isEmpty())
			return false;
		
		else{
			TransactionQueue<T> now=head;
			for(int i=0;i<listLength;i++) {
				if(now.getLast().getID()==TQ.getLast().getID())
					now.setDate(date);
				else {
					now=now.getNext();}}
			return true;}}
	public void insertTo(TransactionQueue<T> TQ) {
		if(isEmpty())
			head=TQ;
		else {
			TransactionQueue<T> last=null;
			TransactionQueue<T> now=head;
			for(int i=0;i<listLength;i++) {
				if(i==listLength-1)
					last=now;
				else {
					now=now.getNext();}
			}
			last.setNext(TQ);
		}
		listLength++;}
	
	public TransactionQueue<T> removeTQ(String date) throws Exception {
		TransactionQueue<T> var=null;
		if(!isEmpty()) {
			if(getTQ(date)==head) {
				var=head;
				head=head.getNext();
			}
			else {
				TransactionQueue<T> before=null;
				TransactionQueue<T> now=head;
				for(int i=0;i<listLength;i++) {
					if(now.getDate()==date)
						continue;
					else {
						before=now;
						now=now.getNext();}
				}
				var=getTQ(date);
				before.setNext(var.getNext());
			}
			listLength--;
			return var;
		}
		else
			throw new Exception("No date exist");
}
	public boolean isEmpty() {
		return listLength<1;}
	public boolean clear() {
		head=null;
		listLength=0;
		return true;}
	public String toString() {
		return getClass().getName()+"@"+Integer.toHexString(hashCode());}
	public int getLength() {
		return this.listLength;
	}
	public void readQueue() {
		TransactionQueue<T> tempQ=this.head;
		Transaction<T> tempT=tempQ.getHead();
		for(int i=0;i<listLength;i++) {
			System.out.println(tempQ.getDate()+" COUNTER <- "+tempT.getID()+"|"+tempT.getCus().getType()+"|"+tempT.getOccupation()+"|"+tempT.getWaiting());
			for(int a=0;a<tempQ.getQueueLength();a++) {
			System.out.print(" <- "+tempT.getID()+"|"+tempT.getCus().getType()+"|"+tempT.getOccupation()+"|"+tempT.getWaiting());
			tempT=tempT.getNextT();}
			tempQ=tempQ.getNext();}
	}
}
