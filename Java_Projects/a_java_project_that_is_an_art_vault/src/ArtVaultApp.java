/**
 * @author Arif Burak Demiray 
*/
import java.util.ArrayList;
import java.util.Scanner;
public class ArtVaultApp {
		public static void main(String[] args) throws Exception {
			@SuppressWarnings("unchecked")
			Vault vault = new Vault((ArrayList<Object>)CSVReader.readTheCsvFile()[1], (ArrayList<Object>)CSVReader.readTheCsvFile()[0]);
			// we created the vault object.
			Scanner scanner = new Scanner(System.in);
			while(true) { //we created a while loop because we wanted the loop to continue if users do not want to press 0.
				System.out.println("Please enter the number of operation you want to perform:");
				System.out.println();		
				System.out.println("1) Print the list");
				System.out.println("2) Search the vault");
				System.out.println("3) Trade");
				System.out.println("0) Exit");
				System.out.println();
				String input = scanner.nextLine();//We take input to make people do what they want.
				if(input.equals("0")) {//if zero means exit.
					scanner.close();
					System.exit(0);
					}
				else if(input.equals("1")) {
					ArtVaultAppMenu.menu1();
				}//people enter the desired entry for the process they want and the necessary methods are called accordingly. 
				//If an invalid input is entered, warning message will be sent.
				else if(input.equals("2")) {
					ArtVaultAppMenu.menu2(vault.getListOfArtworks(),vault.getListOfPerson());
				}
				else if(input.equals("3")) {
					ArtVaultAppMenu.menu3(vault.getListArtworkSpecifically());
				}
				else {
					System.out.println("You should enter an integer !");
					System.out.println();}}}
}