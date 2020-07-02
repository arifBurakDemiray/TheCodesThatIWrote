import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class ArtVaultAppMenu {
	
	/**
	 * @param loa list of artwork.
	 * takes param and does trade between buyers and seller.
	 * trade operation is been doing randomly.
	 */
	//static because same reason like the other static methods that we have.
	public static void menu3(ArrayList<Artwork> loa) {
		Random random = new Random();//random class for taking random artwork from seller collection.
		Buyer[] lob = new Buyer[4];//list of buyers.
		Seller seller = new Seller(new Wallet(0),new PrivateCollection());//creating seller.
		seller.getSellerCollection().setPrivateArray(loa);//adding artworks to the seller collection.
		System.out.println("Seller's money: "+seller.getSellerWallet().getMoneyThatIHave()+" TL");
		for(int i=0;i<4;i++) {
			Buyer buyer = new Buyer(new Wallet(5000000),new PrivateCollection());//creating buyers.
			lob[i]=buyer;
			System.out.println("Buyer "+(i+1)+"'s money: "+buyer.getBuyerWallet().getMoneyThatIHave()+" TL");}
		System.out.println("Trade started:");
		System.out.println();
		int a = 0;
		while(true) {
		int randomNumber = random.nextInt(seller.getSellerCollection().getPrivateArray().size());//creating random number between zero and
		if(randomNumber!=0)                                                                        //buyer's collection size.
			randomNumber--;//random operation was giving error. So we did add this if block to avoid from giving number that equals to size.
		if(seller.getSellerCollection().getPrivateArray().get(randomNumber).isTradable()) {//if random artwork is tradable.
		seller.getSellerCollection().getPrivateArray().get(randomNumber).tradeToBuyer(seller, lob[a]);//doing trade operations.
		System.out.println("Buyer "+(a+1)+" bought:");//print operations.
		System.out.println();
		String temp1 = seller.getSellerCollection().getPrivateArray().get(randomNumber).getClass().toString();//for printing.
		String[] temp = temp1.split(" ");
		System.out.println(temp[1]+":");
		System.out.println("        Name: "+seller.getSellerCollection().getPrivateArray().get(randomNumber).getName());
		System.out.println("        Price: "+seller.getSellerCollection().getPrivateArray().get(randomNumber).calculateCost());
		System.out.println();
		if(a==3) {//a==3 means we have finished the trade operations with 4 buyers.
			if(lob[a-3].getBuyerWallet().getMoneyThatIHave()>=0 && lob[a-2].getBuyerWallet().getMoneyThatIHave()>=0 && lob[a-1].getBuyerWallet().getMoneyThatIHave()>=0 && lob[a].getBuyerWallet().getMoneyThatIHave()>=0 )
			{   //if one of the buyer money below zero means it can not be.
				System.out.println("Trade completed:");
				System.out.println();
				System.out.println("Seller's money: "+seller.getSellerWallet().getMoneyThatIHave()+" TL");
				System.out.println("Buyer "+(a-2)+"'s money: "+lob[a-3].getBuyerWallet().getMoneyThatIHave()+" TL");
				System.out.println("Buyer "+(a-1)+"'s money: "+lob[a-2].getBuyerWallet().getMoneyThatIHave()+" TL");
				System.out.println("Buyer "+(a)+"'s money: "+lob[a-1].getBuyerWallet().getMoneyThatIHave()+" TL");
				System.out.println("Buyer "+(a+1)+"'s money: "+lob[a].getBuyerWallet().getMoneyThatIHave()+" TL");
				System.out.println();
				System.out.println("----------------------------------------------------");
				break;}
			else{
				System.out.println("Some buyers do not have enough money for trade!!");
			}}//this block ends while loop.
		a++;}}//end while loop
		System.out.println();}//end menu3
	
	//Between 50-220 lines, we used 'getter' methods to print their sorting according to the class and sorting option chosen by the user.
	/**
	 * that is sort operations method.
	 * @throws IOException for file reading.
	 * @throws Exception for compare to because class equality.
	 */
	@SuppressWarnings({ "unchecked", "resource" })//for CSVReader class.
	//Static like other static methods.
	public static void menu1() throws IOException, Exception {
		Vault vault = new Vault((ArrayList<Object>)CSVReader.readTheCsvFile()[1],(ArrayList<Object>)CSVReader.readTheCsvFile()[0]);
		boolean flag=true;//this is controller for while loop.
		while(flag==true) {
			System.out.println();
			System.out.println("Please choose the list");
			System.out.println("1) Print the list of artist");
			System.out.println("2) Print the list of architects");
			System.out.println("3) Print the list of paintings");
			System.out.println("4) Print the list of sculptures");
			System.out.println("5) Print the list of architectures");
			System.out.println("0) Exit");
			ArrayList<Object> deneme = new ArrayList<Object>();//sorted items go here.
			Scanner s1 = new Scanner(System.in);
			String chooseNumber = s1.nextLine();
			if(chooseNumber.equals("0")) {
				flag=false;//means end while loop.
				continue;}
			else if(chooseNumber.equals("1")) {////Artist
				System.out.println("Please choose the sorting method:");
				System.out.println("1) Sort by name");
				System.out.println("2) Sort by birthday");
				System.out.println("3) Sort by nationalty");
				System.out.println("0) Exit");
				String chooseNumber1 = s1.nextLine();
					if(chooseNumber1.equals("0")) {
						continue;}
					else if(chooseNumber1.equals("1")) {
						deneme = ArrayListSorter.sort(vault.getListOfPerson(), 0);}
					else if(chooseNumber1.equals("2")) {
						deneme = ArrayListSorter.sort(vault.getListOfPerson(), 1);}
					else if(chooseNumber1.equals("3")) {
						deneme = ArrayListSorter.sort(vault.getListOfPerson(), 2);}
					else {
						System.out.println("You should enter an integer !");
						System.out.println();}
					for(Object name:deneme) {
						Person person1 = (Person) name;
						if(person1 instanceof Artist) {
							System.out.println("Artist:");
							System.out.println("		Name:"+ person1.getName());
							System.out.println("		Birthdate: "+person1.getBirthdate());
							System.out.println("		Deathdate: "+person1.getDeathdate());
							System.out.println("		Nationalty: "+person1.getCountry());}}}
			else if(chooseNumber.equals("2")) {//Architect
				System.out.println("Please choose the sorting method:");
				System.out.println("1) Sort by name");
				System.out.println("2) Sort by birthday");
				System.out.println("3) Sort by nationalty");
				System.out.println("0) Exit");
				String chooseNumber2 = s1.nextLine();
				if(chooseNumber2.equals("0")) {
					continue;}
				else if(chooseNumber2.equals("1")) {
					deneme = ArrayListSorter.sort(vault.getListOfPerson(), 0);}
				else if(chooseNumber2.equals("2")) {
					deneme = ArrayListSorter.sort(vault.getListOfPerson(), 1);}
				else if(chooseNumber2.equals("3")) {
					deneme = ArrayListSorter.sort(vault.getListOfPerson(), 2);}
				else {
					System.out.println("You should enter an integer !");
					System.out.println();}
					for(Object name:deneme) {
						Person person1 = (Person) name;
						if(person1 instanceof Architect) {
							System.out.println("Architect:");
							System.out.println("		Name:"+ person1.getName());
							System.out.println("		Birthdate: "+person1.getBirthdate());
							System.out.println("		Deathdate: "+person1.getDeathdate());
							System.out.println("		Nationalty: "+person1.getCountry());}}}
			else if(chooseNumber.equals("3")) {//Painting
				System.out.println("Please choose the sorting method:");
				System.out.println("1) Sort by name");
				System.out.println("2) Sort by style");
				System.out.println("3) Sort by artist");
				System.out.println("0) Exit");
				String chooseNumber3 = s1.nextLine();
				if(chooseNumber3.equals("0")) {
					continue;}
				else if(chooseNumber3.equals("1")) {
					deneme = ArrayListSorter.sort(vault.getListOfArtworks(), 0);}
				else if(chooseNumber3.equals("2")) {
					deneme = ArrayListSorter.sort(vault.getListOfArtworks(), 1);}
				else if(chooseNumber3.equals("3")) {
					ArrayList<Object> temp12 = new ArrayList<Object>();
					for(Object temp21: vault.getListOfArtworks()) {//in here we are sending only painting list for our sort method.
						if(temp21 instanceof Painting) {
							temp12.add(temp21);}}
					deneme = ArrayListSorter.sort(temp12, 2);}
				else {
					System.out.println("You should enter an integer !");
					System.out.println();}
					for(Object name:deneme) {
						Artwork artwork1 = (Artwork) name;
						if(artwork1 instanceof Painting) {
							System.out.println("Painting:");
							System.out.println("		Name: "+artwork1.getName());
							System.out.println("		Artist: "+((Painting) artwork1).getArtist().getName());
							System.out.println("		Style: "+artwork1.getStyle());
							System.out.println("		Dimension: "+((Painting)artwork1).getDimension1()+"cm x "+ ((Painting)artwork1).getDimension2()+"cm");}}}
			else if(chooseNumber.equals("4")) {//Sculpture
				System.out.println("Please choose the sorting method:");
				System.out.println("1) Sort by name");
				System.out.println("2) Sort by style");
				System.out.println("3) Sort by artist");
				System.out.println("4) Sort by material");
				System.out.println("0) Exit");
				String chooseNumber4 = s1.nextLine();
				if(chooseNumber4.equals("0")) {
					continue;}
				else if(chooseNumber4.equals("1")) {
					deneme = ArrayListSorter.sort(vault.getListOfArtworks(), 0);}
				else if(chooseNumber4.equals("2")) {
					deneme = ArrayListSorter.sort(vault.getListOfArtworks(), 1);}
				else if(chooseNumber4.equals("3")) {
					ArrayList<Object> temp12 = new ArrayList<Object>();
					for(Object temp21: vault.getListOfArtworks()) {//in here we are sending only sculpture list for our sort method.
						if(temp21 instanceof Sculpture) {
							temp12.add(temp21);}}
					deneme = ArrayListSorter.sort(temp12, 4);}
				else if(chooseNumber4.equals("4")) {
					ArrayList<Object> temp12 = new ArrayList<Object>();
					for(Object temp21: vault.getListOfArtworks()) {//in here we are sending only sculpture list for our sort method.
						if(temp21 instanceof Sculpture) {
							temp12.add(temp21);}}
					deneme = ArrayListSorter.sort(temp12, 3);}
				else {
					System.out.println("You should enter an integer !");
					System.out.println();}
					for(Object name:deneme) {
						Artwork artwork1 = (Artwork) name;
						if(artwork1 instanceof Sculpture) {
							System.out.println("Sculpture:");
							System.out.println("		Name: "+artwork1.getName());
							System.out.println("		Artist: "+((Sculpture) artwork1).getArtist().getName());
							System.out.println("		Material: "+((Sculpture) artwork1).getMaterial());
							System.out.println("		Weight: "+((Sculpture)artwork1).getWeight());
							System.out.println("		Style: "+artwork1.getStyle());}}}
			else if(chooseNumber.equals("5")) {//architectures
				System.out.println("Please choose the sorting method:");
				System.out.println("1) Sort by name");
				System.out.println("2) Sort by style");
				System.out.println("0) Exit");
				String chooseNumber5 = s1.nextLine();
				if(chooseNumber5.equals("0")) {
					continue;}
				else if(chooseNumber5.equals("1")) {
					deneme = ArrayListSorter.sort(vault.getListOfArtworks(), 0);}
				else if(chooseNumber5.equals("2")) {
					deneme = ArrayListSorter.sort(vault.getListOfArtworks(), 1);}
				else {
					System.out.println("You should enter an integer !");
					System.out.println();}
					for(Object name:deneme) {
						Artwork artwork1 = (Artwork) name;
						if(artwork1 instanceof Architecture) {
							System.out.println("Architecture:");
							System.out.println("		Name: "+artwork1.getName());
							System.out.print("		Architect(s): ");
							for(Object architect : ((Architecture) artwork1).getArchitect()) {
								if(architect.equals(((Architecture) artwork1).getArchitect().get(((Architecture) artwork1).getArchitect().size()-1))) {
									System.out.print(((Architect) architect).getName());
									break;}
							System.out.print(((Architect) architect).getName()+",");}
							System.out.println();
							System.out.println("		Style: "+ artwork1.getStyle());
							System.out.println("		Dimensions: "+((Architecture) artwork1).getDimension1()+"cm x "+((Architecture) artwork1).getDimension2()+"cm x "+((Architecture) artwork1).getDimension3()+"cm");
						}}}	
			else {
				System.out.println("You should enter an integer !");
				System.out.println();}}}//end menu 1
						
	/**
	 * Search through vault by a keyword that is given by user.
	 * @param loa list of artworks.
	 * @param lop list of persons.
	 * @throws Exception for compare to because class equality.
	 */
	@SuppressWarnings("resource")//for scanner.
	//for being static it has same reason.
	public static void menu2(ArrayList<Object> loa,ArrayList<Object> lop) throws Exception {
			ArrayList<Object> temploa = ArrayListSorter.sort(loa, 0);//sort by name
			ArrayList<Object> templop = ArrayListSorter.sort(lop, 0);
			Scanner sc1 = new Scanner(System.in);
			System.out.println("Enter the keyword:");
			System.out.println();
			String keyword = sc1.nextLine();
			System.out.println();
			int controller = 0;//for looking do we have a thing that contains keyword.
			for(Object tempO: templop) {
				if(tempO instanceof Artist) {//artist searchs
					Artist temp = (Artist) tempO;
					if(temp.isSearch(keyword)) {
						System.out.println("Artist:");
						System.out.println("        Name: "+temp.getName());
						System.out.println("        Born: "+temp.getBirthdate());
						System.out.println("        Died: "+temp.getDeathdate());
						System.out.println("        Nationality: "+temp.getCountry());
						System.out.print("        Periods: ");
						controller++;
						for(String periods:temp.getPeriods()) {//artists have more than one period.
							if(periods.equals(temp.getPeriods().get(temp.getPeriods().size()-1))) {
								System.out.print(periods);
								break;}
							System.out.print(periods+",");}
						System.out.println();}}
				else {//Architect searchs
					Architect temp = (Architect) tempO;
					if(temp.isSearch(keyword)) {
						System.out.println("Architect:");
						System.out.println("        Name: "+temp.getName());
						System.out.println("        Born: "+temp.getBirthdate());
						System.out.println("        Died: "+temp.getDeathdate());
						System.out.println("        Nationality: "+temp.getCountry());
						controller++;}}}
			//why do we have 2 search loop for 2 different list
			//because firstly it should write persons than artworks.
			for(Object objectA: temploa) {
				Artwork temp = (Artwork) objectA;
						if(temp instanceof Painting) {//painting search
							if(((Painting) temp).getArtist().isSearch(keyword) || ((Painting)temp).isSearch(keyword)) {
								controller++;//looking for artwork's artist because they must have same name for writing. if keyword a person name.
								System.out.println("Painting:");
								System.out.println("        Name: "+temp.getName());
								System.out.println("        Artist: "+((Painting) temp).getArtist().getName());
								System.out.println("        Style: "+temp.getStyle());
								System.out.println("        Dimensions: "+((Painting)temp).getDimension1()+"cm x "+((Painting)temp).getDimension2()+"cm");}}
						else if(temp instanceof Sculpture) {//sculpture search
							if(((Sculpture) temp).getArtist().isSearch(keyword) || ((Sculpture)temp).isSearch(keyword)) {
								controller++;//these blocks means we find same string equals to keyword so we have things to write.
								System.out.println("Sculpture:");
								System.out.println("        Name: "+temp.getName());
								System.out.println("        Artist: "+((Sculpture) temp).getArtist().getName());
								System.out.println("        Style: "+temp.getStyle());
								System.out.println("        Material: "+((Sculpture) temp).getMaterial());
								System.out.println("        Weight: "+((Sculpture) temp).getWeight());			}}
						else if(temp instanceof Architecture) {
							if(((Architecture)temp).isSearch(keyword)){//this is for if keyword is an architecture name.
								controller++;
								System.out.println("Architecture:");
								System.out.println("        Name: "+temp.getName());
								System.out.print("        Architect(s): ");
								for(Architect arch: ((Architecture)temp).getArchitect()) {
									if(arch.equals(((Architecture) temp).getArchitect().get(((Architecture)temp).getArchitect().size()-1))){
										System.out.print(arch.getName());}
									else {
										System.out.print(arch.getName()+",");}}
								controller++;
								System.out.println();
								System.out.println("        Style: "+temp.getStyle());
								System.out.println("        Dimensions: "+((Architecture)temp).getDimension1()+"cm x "+((Architecture)temp).getDimension2()+"cm x "+((Architecture)temp).getDimension3()+"cm");}
							else {
							for(Architect arch: ((Architecture)temp).getArchitect()) {
								if(arch.isSearch(keyword)) {// this is for if keyword is an architect name.
									controller++;
									System.out.println("Architecture:");
									System.out.println("        Name: "+temp.getName());
									System.out.print("        Architect(s): ");
									for(Architect arch1: ((Architecture)temp).getArchitect()) {//Architectures have more than one architect.
										if(arch1.equals(((Architecture) temp).getArchitect().get(((Architecture)temp).getArchitect().size()-1))){
											System.out.print(arch1.getName());}//this if for we do not need any comma at the end.
										else {
											System.out.print(arch1.getName()+",");}}
									controller++;
									System.out.println();
									System.out.println("        Style: "+temp.getStyle());
									System.out.println("        Dimensions: "+((Architecture)temp).getDimension1()+"cm x "+((Architecture)temp).getDimension2()+"cm x "+((Architecture)temp).getDimension3()+"cm");}}}}}
					if(controller==0)//means we could not find anything about keyword.
						System.out.println("We can not find the thing that you are looking for!!");
					System.out.println();
					System.out.println("----------------------------------------------------");
					System.out.println();}//end menu2
}// end class