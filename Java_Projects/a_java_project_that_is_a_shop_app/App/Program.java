package App;
import java.util.ArrayList;
import java.util.Scanner;

import Elements.AdminUser;
import Elements.Product;
import Elements.User;
import Exceptions.ProductNotFoundException;
import FileIO.FileRead;
import Storage.Basket;
import Storage.ProductInventory;

public class Program implements IProgram {
	private static Scanner sc = new Scanner(System.in);
	public Program() {}
	//this method is our whole program.
	public  void program() throws Exception,ProductNotFoundException {
		while(true) {
			int a = PreProgram.mainMenu(new FileRead().readUsersCSV());
			if(a==1) {
				FileRead fr = new FileRead();
				Basket basket = new Basket();
				ProductInventory<Product> inventory = (ProductInventory<Product>) fr.readProductCSV();
				ArrayList<User> users = fr.readUsersCSV();
				AdminUser admin = PreProgram.admin(users);
				Operations.giveItems(inventory,users);
				int control = 0;
				while(control==0) {
					System.out.println("If you want to exit from adding to the basket press (S)!!"
							+ "\n if you want to remove from basket press (R)"
							+ "\n if you want to see total cost press (T)"
							+ "\n to add to the basket press (enter)");
					String exit = sc.nextLine();
					if(exit.equals("T"))
						basket.totalCost();
					else if(exit.equals("S"))
						break;
					else if(exit.equals("R")) {
						System.out.println("Please write the product's name!!");
						String name = sc.nextLine();
						basket.removeFromBasket(name);
						}
					else{
						PreProgram.categoriesPro(PreProgram.arrayUser(users));
					try {PreProgram.addToBasket(inventory, basket);}//user can input String so we prevent this error in there.
					catch(NumberFormatException e) {
						continue;
					}}
				System.out.println("Do you want to make payment? (0)");
				String s = sc.nextLine();
				if(s.equals("0")) {
					try{basket.buy(PreProgram.customer(users), PreProgram.arrayUser(users), admin);}
					catch(NullPointerException e) {
						System.out.println("You have to buy some items to return!!");
					}}
				
				System.out.println("if you want to return product press (K)");
				String look = sc.nextLine();
				if(look.equals("K")) {
					System.out.println("Please write the item's name and quantity that you want to return!!");
					System.out.print("Name: ");
					String name1 = sc.nextLine();
					System.out.println("Quantity: ");
					String quan = sc.nextLine();
					ReturningOperations ro = new ReturningOperations();
					ro.takeBack(basket.basket.findProduct(name1),PreProgram.supplier(PreProgram.arrayUser(users),basket.basket.findProduct(name1)) , Integer.valueOf(quan), PreProgram.customer(users));
				}
				System.out.println("If you want to sign out press (9)");
				String go = sc.nextLine();
				if(go.equals("9"))
					control++;
				}
				
			}
			else
				continue;
		}
	}}
