package FileIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Elements.Address;
import Elements.AdminUser;
import Elements.ContactInfo;
import Elements.Customer;
import Elements.FastConsumptionProduct;
import Elements.FragileProduct;
import Elements.Product;
import Elements.RegularUser;
import Elements.Supplier;
import Elements.User;
import Storage.IInventory;
import Storage.ProductInventory;
//in there we are reading the files. and creating the related items.
public class FileRead {
	public FileRead() {}
	public IInventory<Product> readProductCSV() throws IOException {
		IInventory<Product> inventory = new ProductInventory<Product>();
		File csv = new File("products.csv");
		BufferedReader bf = new BufferedReader(new FileReader(csv));
		String line = bf.readLine();
		line=bf.readLine();
		while(line!=null) {
			String[] items = line.split(",");
			if(items[0].equals("houseware") || items[0].equals("electronics")) {
				Product fraPro = new FragileProduct(items[0],items[1],Double.valueOf(items[2]),Double.valueOf(items[3]));
				inventory.addElementToInventory(fraPro, Integer.valueOf(items[4]));
			}
			else if(items[0].equals("cosmetic")) {
				Product factCon = new FastConsumptionProduct(items[0],items[1],Double.valueOf(items[2]),Double.valueOf(items[3]));
				inventory.addElementToInventory(factCon, Integer.valueOf(items[4]));
			}
			else {
				Product normalProduct = new Product(items[0],items[1],Double.valueOf(items[2]),Double.valueOf(items[3]));
				inventory.addElementToInventory(normalProduct, Integer.valueOf(items[4]));
			}
			line=bf.readLine();
		}
		bf.close();
		return inventory;
	}
	public ArrayList<User> readUsersCSV() throws IOException {
		ArrayList<User> listUsers = new ArrayList<User>();
		File csv = new File("users.csv");
		BufferedReader bf = new BufferedReader(new FileReader(csv));
		String line = bf.readLine();
		line=bf.readLine();
		while(line!=null) {
			String[] items = line.split(",");
			if(items[0].equals("1")) {
				User admin = new AdminUser(items[1],items[2],Integer.valueOf(items[3]));
				listUsers.add(admin);
			}
			else {
				ArrayList<Address> adres = new ArrayList<Address>();
					for(int i = 9;i<items.length ;i++) {
						if(items[i+5]!= null) {
							Address adres1 = new Address(items[i],items[i+1],items[i+2],items[i+3],items[i+4],items[i+5]);
							adres.add(adres1);
							i +=5; }
					}
				ContactInfo ci = new ContactInfo(items[4],items[5],adres);
				if(items[0].equals("2")) {
					RegularUser customer = new Customer(items[1],items[2],Integer.valueOf(items[3]),ci);
					listUsers.add(customer);
				}
				else if(items[0].equals("3")) {
					RegularUser supp = new Supplier(items[1],items[2],Integer.valueOf(items[3]),ci,items[6],items[7],items[8]);
					listUsers.add(supp);
				}}
			line=bf.readLine();
		}
		bf.close();
		return listUsers;
		
	}
}
