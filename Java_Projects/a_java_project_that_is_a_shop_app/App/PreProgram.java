package App;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Elements.Address;
import Elements.AdminUser;
import Elements.ContactInfo;
import Elements.Customer;
import Elements.Product;
import Elements.Supplier;
import Elements.User;
import Exceptions.ProductNotFoundException;
import FileIO.FileWrite;
import Storage.Basket;
import Storage.ProductInventory;

public class PreProgram {
	private static Scanner sc = new Scanner(System.in);
	public static String nameQ;//this is the user name of the signed in customer's.
	public static int mainMenu(ArrayList<User> users) throws IOException {//in here we do sign in,up and put.
		FileWrite fw = new FileWrite();
		System.out.println("Welcome to the OHDG35 SHOPP!! \n"
				+ "Which operation do you wanna perform?\n"
				+ "(1) SIGN IN"
				+ "\n(2) SIGN UP\n"
				+ "(3) SIGN OUT");
		String ope = sc.nextLine();
		if(ope.equals("1")) {
			System.out.print("Name: ");
			String name = sc.nextLine();
			nameQ=name;
			System.out.print("Password: ");
			String pass = sc.nextLine();
			if(SignOperations.SignIn(name, pass)) {
				System.out.println("\nYou signed in succesfully!!");
				return 1;}//this mean we can have break in while loop
			else {
				System.out.println("Please try again!!");//this means we cannot
				return 0;}
		}
		else if(ope.equals("2")) {
			System.out.println("Do you want to sign up as a (s)supplier or (c)customer\n Press C or S");
			String choice = sc.nextLine();
			System.out.print("Name: ");
			String name = sc.nextLine();
			System.out.print("Password: ");
			String pass = sc.nextLine();
			System.out.print("Active balance: ");
			int abal=0;
			try {	
				int abal1 = sc.nextInt();
				abal=abal1;
			}
			catch(InputMismatchException e) {//we prevent from string inputs.
				abal=0;
			}
			System.out.print("Telephone: ");
			String tel = sc.nextLine();
			System.out.print("Email: ");
			String email = sc.nextLine();
			ArrayList<Address> adrey = new ArrayList<Address>();
			while(true) {//he/se can add multiple addresses.
			System.out.print("Address Title: ");
			String adTit = sc.nextLine();
			System.out.print("Country: ");
			String coun = sc.nextLine();
			System.out.print("City: ");
			String ci = sc.nextLine();
			System.out.print("District: ");
			String dis = sc.nextLine();
			System.out.print("Street: ");
			String stre = sc.nextLine();
			System.out.print("Door Number: ");
			String doNum = sc.nextLine();
			adrey.add(new Address(adTit,coun,ci,dis,stre,doNum));
			System.out.println("If you do not want to add another addres -> (Press A)");
			String ch = sc.nextLine();
			if(ch.equals("A"))
				break;}
			if(choice.equals("s")) {
				System.out.print("Shop Category: ");
				String cato = sc.nextLine();
				System.out.print("Shop Title: ");
				String tit = sc.nextLine();
				System.out.print("Tax Number: ");
				String tax = sc.nextLine();
				users.add(new Supplier(name,pass,Integer.valueOf(abal),new ContactInfo(tel,email,adrey),cato,tit,tax));
				fw.writeToTheFile(new Supplier(name,pass,Integer.valueOf(abal),new ContactInfo(tel,email,adrey),cato,tit,tax));
				
			}
			else if(choice.equals("c")) {
				users.add(new Customer(name,pass,Integer.valueOf(abal),new ContactInfo(tel,email,adrey)));
				fw.writeToTheFile(new Customer(name,pass,Integer.valueOf(abal),new ContactInfo(tel,email,adrey)));
			}
			return 1; //this means we can move on because we were signed up someone.
		}
		else if(ope.equals("3"))
			System.exit(0);
		return 0;//if he wrote other than 1,2,3 while loop moves on.
	}
	//this method writes all products. by supplier.
	public static void categoriesPro(ArrayList<Supplier> sup) {
		System.out.println("Which shop do you want to look? \n"
				+ "(1) Electronics,(2) Houseware,(3) Cosmetic,(4) Accessories");
		String cho = sc.nextLine();
		for(Supplier supa: sup) {
		supa.getProducts().getDepo().forEach((k,v)->{
			if(cho.equals("1")) {
				if(supa.getShopCategory().equals("electronic")) {
					System.out.println("Name: "+k.getName()+" Quantity: "+v+" Price: "+k.getPrice()+" Weight: "+k.getWeight());
				}
			}
			else if(cho.equals("2")) {
				if(supa.getShopCategory().equals("houseware")) {
					System.out.println("Name: "+k.getName()+" Quantity: "+v+" Price: "+k.getPrice()+" Weight: "+k.getWeight());
				}
			}
			else if(cho.equals("3")) {
				if(supa.getShopCategory().equals("cosmetic")) {
					System.out.println("Name: "+k.getName()+" Quantity: "+v+" Price: "+k.getPrice()+" Weight: "+k.getWeight());
				}
			}
			else if(cho.equals("4")) {
				if(supa.getShopCategory().equals("accessories")) {
					System.out.println("Name: "+k.getName()+" Quantity: "+v+" Price: "+k.getPrice()+" Weight: "+k.getWeight());
				}
			}
			else
				System.out.println("Please write a valid operation!!");
			
		});}
	}
	//this method provides adding to the basket.
	public static void addToBasket(ProductInventory<Product> pi,Basket basket) throws NumberFormatException, ProductNotFoundException {
		System.out.println("Please write the name and quantity of the item that you want to add to the basket!!");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Quantity: ");
		String quan = sc.nextLine(); if(!(Integer.valueOf(quan) instanceof Integer)) quan = "1";//this prevents from string inputs.
		basket.basket.addElementToInventory(pi.findProduct(name), Integer.valueOf(quan));
	}
	public static ArrayList<Supplier> arrayUser(ArrayList<User> in) {//this makes supplier's list.
		ArrayList<Supplier> arr = new ArrayList<Supplier>();
		for(User sup: in){
			if(sup instanceof Supplier) {
 				arr.add((Supplier) sup);}
		}
		return arr;
		
	}
	public static AdminUser admin(ArrayList<User> in) {//we take admin from file.
		for(User sup: in){
			if(sup instanceof AdminUser) {
 				return (AdminUser) sup;}
		}
		return new AdminUser("admin","admin",1000);
		
	}
	@SuppressWarnings("null")
	public static Customer customer(ArrayList<User> in) {//in here we take the signed in customer from our arraylist.
		for(User sup: in){
			if(sup.getUserName().equals(nameQ)) {
 				return (Customer) sup;}
		}
		return new Customer(null,null,(Integer) null,null);
		
	}
	public static Supplier supplier(ArrayList<Supplier> arr, Product pro) throws Exception {//in here we take supplier for returning.
		for(Supplier sup:arr) {
			if(sup.getShopCategory().equals(pro.getCategory()))
				return sup;
		}
		throw new Exception("We do not have that product's supplier!!");
	}
}
