package Elements;

//This class is admin user class.
public class AdminUser extends User{

	public AdminUser(String userName, String password, int activeBalance) {
		super(userName, password, activeBalance);
	}
	/**
	 * Admin approves a supplier if supplier has a tax number which lenght's  is greater than 17.
	 * @param sup which is going to be approved by admin.
	 * @return true if it has appropriate tax number otherwise returns false.
	 */
	public boolean approveSupplier(Supplier sup) {
		return sup.getTaxNumber().length()>17;
	}

}
