package Program;
import java.util.Scanner;

public class PreProgram implements IPreProgram{
	private IProgram program = new Program();
	public PreProgram() {}
	public void program() {
		@SuppressWarnings("resource")
		Scanner scn = new Scanner(System.in);
		System.out.println("Welcome to the our Facilities.\n"
				+ "You can buy items in Warehouse to produce products in Factory.\n"
				+ "You can sell that produced items in Distrubution Center.\n"
				+ "You can ship items between facilities.\n"
				+ "For buying Ingredients press (1).\n"
				+ "For shipping Ingredients to the Factory press (2).\n"
				+ "For producing Products press (3).\n"
				+ "For shipping Products to the Distrubution Center press (4).\n"
				+ "For selling products press (5).\n"
				+ "For seeing the items that you have in all facilities and see your money press (6).\n"
				+ "You can exit from program with pressing (0).");
		while(true) {
			String chosen = scn.nextLine();
			System.out.println("-------------------------------------------------------");
			if(chosen.equals("1")) {
				program.buyOperationWithUser();
				System.out.println("(Program is running you can choose operations above.)");
				System.out.println("-------------------------------------------------------");}
			else if(chosen.equals("2")) {
				program.toTheFactByUser();
				System.out.println("(Program is running you can choose operations above.)");
				System.out.println("-------------------------------------------------------");}
			else if(chosen.equals("3")) {
				program.produceOperation();
				System.out.println("(Program is running you can choose operations above.)");
				System.out.println("-------------------------------------------------------");}
			else if(chosen.equals("4")) {
				program.toTheDistCentByUser();
				System.out.println("(Program is running you can choose operations above.)");
				System.out.println("-------------------------------------------------------");}
			else if(chosen.equals("5")) {
				program.sellOperationWithUser();
				System.out.println("(Program is running you can choose operations above.)");
				System.out.println("-------------------------------------------------------");}
			else if(chosen.equals("6")) {
				program.print();
				System.out.println("(Program is running you can choose operations above.)");
				System.out.println("-------------------------------------------------------");
			}
			else if(chosen.equals("0")) {
				System.out.println("Goodbye :)");
				break;}
			else {
				System.out.println("You give invalid operation!!");
				System.out.println("(Program is running you can choose operations above.)");
				System.out.println("-------------------------------------------------------");}
				
		}
	}
}
