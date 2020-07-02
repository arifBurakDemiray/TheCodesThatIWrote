import java.util.Scanner;
import java.io.*;

public class PicnicBagApp {


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) throws IOException{
		@SuppressWarnings("resource")
		Scanner choos = new Scanner(System.in);
		System.out.println("Hi! I am Euxinos. I can create a picnic bag for you");
		System.out.println("At the end do not forget to take your trashes");
		System.out.println("Please choose a picnic bag size (5,10,15)");
		int choosen;
		choosen = choos.nextInt();

		@SuppressWarnings("unused")
		PicnicBag<Item> bagOfPicnic = new PicnicBag(choosen);
		@SuppressWarnings("unused")
		OrganicTrashBag<Item> bagOrganic = new OrganicTrashBag(choosen);
		@SuppressWarnings("unused")
		PaperTrashBag<Item> bagPaper = new PaperTrashBag(choosen);
		@SuppressWarnings("unused")
		PlasticTrashBag bagPlastic = new PlasticTrashBag(choosen);
		FileIO read = new FileIO();
		
		try {
			read.readInventory().displayItems();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Now you should choose what items are you gonna take");
		//for(int i=0;i<choosen;i++) {
			//String chost = choos.next();
			//Item yemek = new Item(chost,yemek.getType());
			//System.out.println(read.readInventory().transferTo(bagOfPicnic, yemek));	
			//;}
	
	}
}
