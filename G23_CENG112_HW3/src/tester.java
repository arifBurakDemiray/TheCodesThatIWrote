

public class tester {

	public static void main(String[] args) throws NullPointerException {
		FileIO<ICustomer> okuyucu = new FileIO<ICustomer>();
		ILinkedList<ICustomer> liste= new TransactionQueueList<ICustomer>();
		try {
			liste=okuyucu.readTrans();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<liste.getTQ("01.05.2019").getQueueLength();i++) {
		liste.readQueue();}


}}
