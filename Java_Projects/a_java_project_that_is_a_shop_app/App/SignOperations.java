package App;
import java.io.IOException;
import java.util.ArrayList;

import Elements.ContactInfo;
import Elements.Customer;
import Elements.RegularUser;
import Elements.Supplier;
import Elements.User;
import FileIO.FileRead;

public class SignOperations {
	
	//it looks for same name and password over user list.
	public static boolean SignIn(String name,String password) throws IOException {
		ArrayList<User> users = new FileRead().readUsersCSV();
		for(User user : users) {
			if(user.getUserName().equals(name) & user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;}	
	//we have different sign up because their attributes are different.
	public static User SignUpCustomer(String name,String password,int activeBalance,ContactInfo ci) {
		RegularUser customer = new Customer(name,password,activeBalance,ci);
		return customer;}
	public static User SignUpSupplier(String userName, String password, int activeBalance,ContactInfo contact, String shopCategory, String shopTitle, String taxNumber) {
		RegularUser supplier = new Supplier(userName,password,activeBalance,contact,shopCategory,shopTitle,taxNumber);
		return supplier;
	}
}
