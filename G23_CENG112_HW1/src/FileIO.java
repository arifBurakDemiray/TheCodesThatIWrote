import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class FileIO {
	public FileIO() {}
	public IBag<Item> readInventory() throws IOException {
		File file = new File("CENG112_Homework1_Bags_Inventory.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String satir = reader.readLine();
        InventoryBag<Item> BagOfItems = new InventoryBag<Item>();
        while (satir!=null) {
	   		String[] listOfItems=satir.split(",");
	   		Item stuff = new Item(listOfItems[0],listOfItems[1]);
		   		for(int itemnums=0;itemnums<Integer.valueOf(listOfItems[2]);itemnums++) {
		   			BagOfItems.add(stuff);
		   			
		   		}
	        satir = reader.readLine();
           }
        reader.close();
        return BagOfItems;
        }
}
