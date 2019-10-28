
public class Item {
	private String name;
	private String type;
	public Item(String isim,String tip) {
		this.name=isim;
		this.setType(tip);
		
	}
	public String toString() {
		return this.name;
		}
	public boolean equals(Object obj) {
		if(obj==this)
			return true;
		if(obj==null)
			return false;
		else
			return false;
		
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
