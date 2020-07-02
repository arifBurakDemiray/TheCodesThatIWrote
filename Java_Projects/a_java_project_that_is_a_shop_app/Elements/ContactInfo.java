
package Elements;
import java.util.ArrayList;

public class ContactInfo {
	private String telephone;
	private String email;
	private ArrayList<Address> adress;
	
	
	//here our contact and its attributes and getters setters.
	
	public ContactInfo(String telephone, String email, ArrayList<Address> adress) {
		this.telephone = telephone;
		this.email = email;
		this.adress = adress;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Address> getAdress() {
		return adress;
	}
	public void setAdress(ArrayList<Address> adress) {
		this.adress = adress;
	}

}
