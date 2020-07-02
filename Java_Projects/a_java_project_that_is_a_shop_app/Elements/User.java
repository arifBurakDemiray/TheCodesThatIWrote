package Elements;
public abstract class User {
	private String userName;
	private String password;
	private int activeBalance = 0;
	
	public User(String userName, String password, int activeBalance) {
		
		this.userName = userName;
		this.password = password;
		this.activeBalance = activeBalance;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		String temp = this.password;
		return temp;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getActiveBalance() {
		return activeBalance;
	}
	public void setActiveBalance(int activeBalance) {
		this.activeBalance = activeBalance;
	}
	

}
