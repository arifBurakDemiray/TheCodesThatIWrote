import java.io.IOException;
import java.util.Scanner;
/* Hi, we are G79. Arif Burak Demiray and Burak Tutumlu.
 * We did the implementation that it writes ascending and descending orders at the same time and
 * it writes the limited below and above price cases at the same time and maxmimum and minimum prices books
 * and movies at the same time beacuse we wanted to be easy the test cases. It takes price limit and author or director
 * name from the console.
 */
public class IztechMediaMarket {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the IZTECH MEDÝA MARKET!!");
		System.out.println("I am going to give you choices. Select whatever you want.");
		Scanner reader = new Scanner(System.in);
		IFileIO okuyucu = new FileIO();
		ITreeWriter printer = new TreeWriter();
		IBinaryST<IMedia> treeOfMedias = okuyucu.readInventory();
		int flag=0;
		while(flag==0) {
			System.out.println("");
			System.out.println("Press 1 to see the descending and ascending orders of all medias,books and movies.\n"+
							   "Press 2 to see the medias by limited price that you will enter.\n"+
							   "Press 3 to see the specific author's that you are going to enter maximum and minimum priced books.\n"+
							   "Press 4 to see the specific director's that you are going to enter maximum and minimum priced movies.\n"+
							   "Press 0 to exit from the market.\n");
			String selection = reader.next();
			if(selection.equals("1"))
				printer.desasPrinter(treeOfMedias);
			else if(selection.equals("2")) {
				System.out.print("Please write the price :");
				int price = reader.nextInt();
				printer.limitPrice(treeOfMedias, price);
				System.out.println();
			}
			else if(selection.equals("3")) {
				System.out.print("Please write the author's name: ");
				Scanner nameReader = new Scanner(System.in);
				String name = nameReader.nextLine();
				printer.theMostMedias(treeOfMedias, name, "b");
				System.out.println();
				System.out.println("(If you can not see "+name+"'s book, its cause will be not having "+name+"'s books.)"+
								   "(Or you may enter wrong name.)");
				System.out.println();
			}
			else if(selection.equals("4")){
				System.out.print("Please write the director's name: ");
				Scanner nameReader = new Scanner(System.in);
				String name = nameReader.nextLine();
				printer.theMostMedias(treeOfMedias, name, "m");
				System.out.println();
				System.out.println("(If you can not see "+name+"'s movies, its cause will be not having "+name+"'s movies.)"+
				                   "(Or you may enter wrong name)");
				System.out.println();
			}
			else if(selection.equals("0")) {
				System.out.println("Have a nice day!!");
				flag++;}
			else {
				System.out.println("You entered wrong number please try again!!");
			}

	}
		reader.close();
}}
