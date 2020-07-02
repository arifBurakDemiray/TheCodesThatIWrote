import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class FileIO<T>{
	private IQueue<T> queue;
	private ILinkedList<T> listem=new TransactionQueueList<T>();;
	public FileIO() {}
	public ILinkedList<T> readTrans() throws Exception {
		File file = new File("CENG112_HW3_Transactions.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String satir = reader.readLine();
        int flag=0;
        while (satir!=null) {
        	String[] listOfItems=satir.split(",");
        	ICustomer musteri;
        	if(listOfItems[2].equals("NON-REGISTERED")) 
        		musteri=new Customer(3,listOfItems[2]);
        	else if(listOfItems[2].equals("INDIVIDUAL"))
        		musteri=new Customer(2,listOfItems[2]);
        	else
        		musteri=new Customer(1,listOfItems[2]);
		   	Transaction<T> item =  new Transaction<T>(Integer.valueOf(listOfItems[1]),musteri,Integer.valueOf(listOfItems[3]),0);
		   	if(flag==0) {
		   		queue=new TransactionQueue<T>(listOfItems[0]);
		   		queue.enqueue(item);
		   		flag++;

		   	}
		   	else {
		   		if(queue.getDate().equals(listOfItems[0])) {
			   		queue.insert(item);
		   		}
		   		else{	
		   				IQueue<T> ver=new TransactionQueue<T>(listOfItems[0]);
		   				ver.setTotalWaitingTime(queue.getTotalWaitingTime());
		   				ver.setHead(queue.getHead());
		   				ver.setLast(queue.getLast());
		   				ver.setQueueLength(queue.getQueueLength());
			   			listem.addTQ((TransactionQueue<T>) ver);
			   			queue.clear();
			   			//queue=new TransactionQueue<T>(listOfItems[0]);
			   			queue.setDate(listOfItems[0]);
			   			queue.enqueue(item);
				   		} }	
	        satir = reader.readLine();   
           }
        listem.addTQ((TransactionQueue<T>) queue);
        reader.close();
		return listem;

	}

}
	

