package Elements;
import java.util.ArrayList;

public abstract class RegularUser extends User {
	
	private ContactInfo CI;
	public RegularUser(String userName, String password, int activeBalance, ContactInfo cI) {
		super(userName, password, activeBalance);
		this.CI = cI;
	}

	public ContactInfo getCI() {
		return this.CI;
	}

	public void setCI(ContactInfo cI) {
		this.CI = cI;
	}
	public ArrayList<Address> getAddreses(){
		return this.CI.getAdress();
	}
	
	

}
