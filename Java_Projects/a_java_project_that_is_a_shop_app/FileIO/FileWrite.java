package FileIO;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Elements.Address;
import Elements.Customer;
import Elements.Supplier;
import Elements.User;
//in there we are writing to the files if we have new register.
public class FileWrite {
	public FileWrite() {}
	public void writeToTheFile(User user) throws IOException {
		File file = new File("users.csv");
		BufferedWriter br = new BufferedWriter(new FileWriter(file,true));
		if(user instanceof Supplier) {
			Supplier temp = (Supplier) user;
			br.append("3,"+temp.getUserName()+","+temp.getPassword()+","+temp.getActiveBalance()+","+temp.getCI().getTelephone()+","+temp.getCI().getEmail()+","+temp.getShopCategory()+","+temp.getShopTitle()+","+temp.getTaxNumber());
			for(Address ad:temp.getAddreses()) {
				br.append(","+ad.getAddressTitle()+","+ad.getCountry()+","+ad.getCity()+","+ad.getDistrict()+","+ad.getStreet()+","+ad.getDoorNumber());
			}
			br.newLine();
		}
		else if(user instanceof Customer) {
			Customer temp = (Customer) user;
			br.append("2,"+temp.getUserName()+","+temp.getPassword()+","+temp.getActiveBalance()+","+temp.getCI().getTelephone()+","+temp.getCI().getEmail()+", , , ");
			for(Address ad:temp.getAddreses()) {
				br.append(","+ad.getAddressTitle()+","+ad.getCountry()+","+ad.getCity()+","+ad.getDistrict()+","+ad.getStreet()+","+ad.getDoorNumber());
			}
			br.newLine();
		}
		br.close();
	}
}
