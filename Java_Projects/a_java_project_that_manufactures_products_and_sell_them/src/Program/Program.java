package Program;
import java.util.ArrayList;
import java.util.Scanner;
import CargoElements.ARCargoTrack;
import CargoElements.IARCargoTrack;
import Facilities.*;
import Items.*;

public class Program implements IProgram{
	private Scanner scn = new Scanner(System.in);
	private Warehouse<Ingredient> warehouse = new Warehouse<Ingredient>();
	private Factory<Ingredient,Product> factory = new Factory<Ingredient,Product>();
	private DistrubutionCenter<Product> distCenter = new DistrubutionCenter<Product>();
	private Owner facilityOwner  = new Owner("Facility Owner",10000);
	private IOperations ops = new Operations();
	public Program() {}
	
	public void buyOperationWithUser() {
		System.out.println("You have chosen to buy Ingredients!! "+"\n"
				+ "Firstly, give us the name of the ingredient that you wanna buy.\nThen, give us the amount of "
				+ "this ingredient that you wanna buy.\n"
				+ "(You have to write the ingredient's name correctly. Also, amount is in terms of liters)\n"
				+ "(Available Ingredients: Milk, Yeast, Cream, Cacao)");
		System.out.println("Please give us the name: ");
		String ingredientName = this.scn.nextLine();
		System.out.println("Please give us the amount: ");
		try {
		double amountUser = Double.valueOf(this.scn.nextLine()); 
		if(ingredientName.equals("Milk") || ingredientName.equals("Yeast") || ingredientName.equals("Cacao") || ingredientName.equals("Cream")) {
			Ingredient tempIngd = new Ingredient(ingredientName,amountUser);
			ops.buyOperation(warehouse, facilityOwner, tempIngd);}
		else {
			System.out.println("Please give us valid Ingredient name!!");}}
		catch(NumberFormatException e) {
			System.out.println("Please give us a valid amount!!");}}
	
	public void sellOperationWithUser() {
		System.out.println("You have chosen to sell Products!! "+"\n"
				+ "Firstly, give us the name of the product that you wanna sell.\nThen, give us the numbers of "
				+ "this product that you wanna sell.\n"
				+ "(You have to write the product's name correctly. Also, number is in terms of integer)\n"
				+ "(Available Products: Boxed Milk, Yogurt, Chocolate)");
		System.out.println("Please give us the name: ");
		String productName = this.scn.nextLine();
		System.out.println("Please give us the number(s): ");
		try {
			int numberUser = Integer.valueOf(this.scn.nextLine()); 
			if(productName.equals("Boxed Milk") || productName.equals("Chocolate") || productName.equals("Yogurt")) {
				Product tempPrd = new Product(productName,0,numberUser);
				ops.sellOperation(distCenter, facilityOwner, tempPrd);}
			else {
				System.out.println("Please give us a valid product name!!");}}
		catch(NumberFormatException e) {
			System.out.println("Please give us a valid integer!!");}}
	
	public void produceOperation() {
		System.out.println("You have chosen to produce Products!! "+"\n"
				+ "Firstly, give us the name of the product that you wanna produce.\nThen, give us the numbers of "
				+ "this product that you wanna produce.\n"
				+ "(You have to write the product's name correctly. Also, number is in terms of integer)\n"
				+ "(Available Products that can be produce: Boxed Milk, Yogurt, Chocolate)");
		System.out.println("Please give us the name: ");
		String productName = this.scn.nextLine();
		System.out.println("Please give us the number(s): ");
		try {
			int numberUser = Integer.valueOf(this.scn.nextLine()); 
			if(productName.equals("Boxed Milk") || productName.equals("Chocolate") || productName.equals("Yogurt")) {
				Product tempPrd = new Product(productName,0,numberUser);
				ops.produce(tempPrd, factory);}
			else {
				System.out.println("Please give us a valid product name!!");}}
		catch(NumberFormatException e) {
			System.out.println("Please give us a valid integer!!");}}
	
	public void toTheFactByUser() {
		System.out.println("You have chosen to move Ingredients to the Factory!! "+"\n"
				+ "Firstly, give us the name of the ingredient that you wanna move.\nThen, give us the amount of "
				+ "this ingredient that you wanna move.\n"
				+ "(You have to write the ingredient's name correctly. Also, amount is in terms of liters)\n"
				+ "(Available Ingredients: Milk, Yeast, Cream, Cacao)");
		System.out.println("Please give us the name: ");
		String ingredientName = this.scn.nextLine();
		System.out.println("Please give us the amount: ");
		IARCargoTrack arctk = new ARCargoTrack();
		try {
		double amountUser = Double.valueOf(this.scn.nextLine()); 
		if(ingredientName.equals("Milk") || ingredientName.equals("Yeast") || ingredientName.equals("Cacao") || ingredientName.equals("Cream")) {
			Ingredient tempIng = new Ingredient(ingredientName,amountUser);
			arctk.toTheFactory(warehouse, factory, tempIng, facilityOwner);}
		else {
			System.out.println("Please give us a valid Ingredient name!!");}}
		catch(NumberFormatException e) {
			System.out.println("Please give us a valid amount!!");}}
	
	public void toTheDistCentByUser() {
		System.out.println("You have chosen to move Products to the Distrubution Center!! "+"\n"
				+ "Firstly, give us the name of the product that you wanna move.\nThen, give us the numbers of "
				+ "this product that you wanna move.\n"
				+ "(You have to write the product's name correctly. Also, number is in terms of integer)\n"
				+ "(Available Products: Boxed Milk, Yogurt, Chocolate)");
		ArrayList<Product> ap = new ArrayList<Product>(0);
		IARCargoTrack arctk = new ARCargoTrack();
		while(true) {
		System.out.println("Please give us the name: ");
		String productName = this.scn.nextLine();
		System.out.println("Please give us the number(s): ");
		try {
			int numberUser = Integer.valueOf(this.scn.nextLine()); 
			if(productName.equals("Boxed Milk") || productName.equals("Chocolate") || productName.equals("Yogurt")) {
				Product tempPrd = new Product(productName,0,numberUser);
				ap.add(tempPrd);}
			else {
				System.out.println("Please give us a valid Product name!!");}}
		catch(NumberFormatException e) {
			System.out.println("Please give us a valid integer!!");}
		System.out.println("If you want to continue to move other Products, please press (5)!!");
		String control = this.scn.nextLine();
		if(control.equals("5"))
			continue;
		else
			break;}
		arctk.toTheDistCenter(distCenter, factory, ap.toArray(), facilityOwner);}
	
	public void print() {
		System.out.println("Your Money: "+facilityOwner.getWallet().getMoney()+" TL");
		System.out.println("******************************");
		System.out.println("Warehouse Milk Deposit: "+(10000-warehouse.getMilk().getCapacity())+" lt");
		System.out.println("Warehouse Yeast Deposit: "+(10000-warehouse.getYeast().getCapacity())+" lt");
		System.out.println("Warehouse Cream Deposit: "+(10000-warehouse.getCream().getCapacity())+" lt");
		System.out.println("Warehouse Cacao Deposit: "+(10000-warehouse.getCacao().getCapacity())+" lt");
		System.out.println("******************************");
		System.out.println("Factory Milk Deposit: "+(10000-factory.getMilk().getCapacity())+" lt");
		System.out.println("Factory Yeast Deposit: "+(10000-factory.getYeast().getCapacity())+" lt");
		System.out.println("Factory Cream Deposit: "+(10000-factory.getCream().getCapacity())+" lt");
		System.out.println("Factory Cacao Deposit: "+(10000-factory.getCacao().getCapacity())+" lt");
		System.out.println("Factory Boxed Milk Deposit: "+(10000-factory.getBoxedMilk().getCapacity())+" lt");
		System.out.println("Factory Yogurt Deposit: "+(10000-factory.getYogurt().getCapacity())+" lt");
		System.out.println("Factory Chocolate Deposit: "+(10000-factory.getChocolate().getCapacity())+" lt");
		System.out.println("******************************");
		System.out.println("Distrubution Center Boxed Milk Deposit: "+(10000-distCenter.getBoxedMilk().getCapacity())+" lt");
		System.out.println("Distrubution Center Yogurt Deposit: "+(10000-distCenter.getYogurt().getCapacity())+" lt");
		System.out.println("Distrubution Center Chocolate Deposit: "+(10000-distCenter.getChocolate().getCapacity())+" lt");
		System.out.println();}
	}


