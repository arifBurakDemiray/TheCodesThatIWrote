package Elements;

import Storage.IInventory;

public class Supplier extends RegularUser {
	private String shopCategory;
	private String shopTitle;
	private String taxNumber;
	private Shop shop;
	public Supplier(String userName, String password, int activeBalance,ContactInfo contanct, String shopCategory, String shopTitle, String taxNumber) {
		super(userName, password, activeBalance,contanct);
		this.shopCategory = shopCategory;
		this.shopTitle = shopTitle;
		this.taxNumber = taxNumber;
		this.shop = new Shop();
		
	}
	public String getShopCategory() {
		return shopCategory;
	}
	public void setShopCategory(String shopCategory) {
		this.shopCategory = shopCategory;
	}
	public String getShopTitle() {
		return shopTitle;
	}
	public void setShopTitle(String shopTitle) {
		this.shopTitle = shopTitle;
	}
	public String getTaxNumber() {
		return taxNumber;
	}
	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public IInventory<Product> getProducts(){
		return this.shop.getProducts();
	}
	public IInventory<Product> getOldProducts(){
		return this.shop.getOldProducts();
	}
}
