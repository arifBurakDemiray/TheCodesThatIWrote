package Elements;
/*
 * This class our adress class. It has adress informations of one user.
 */
public class Address {
	private String addressTitle;
	private String country;
	private String city;
	private String district;
	private String street;
	private String doorNumber;
	public Address(String addressTitle, String country, String city,String district, String street, String doorNumber) {
	
		this.addressTitle = addressTitle;
		this.country = country;
		this.city = city;
		this.street = street;
		this.doorNumber = doorNumber;
		this.district = district;
	}
	public String getAddressTitle() {
		return addressTitle;
	}
	public void setAddressTitle(String addressTitle) {
		this.addressTitle = addressTitle;
	}
	public String getCountry() {
		return country;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getDoorNumber() {
		return doorNumber;
	}
	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}

	

}
