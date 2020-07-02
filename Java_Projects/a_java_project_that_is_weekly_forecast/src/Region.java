//This class our region class like you wrote on to the pdf again.
//it has getters setters and constructor.
public class Region{
	private int ID;
	private String name;
	private Liste cities;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Liste getCities() {
		return cities;
	}
	public void setCities(Liste cities) {
		this.cities = cities;
	}
	public Region(int ID,String name) {
		this.ID=ID;
		this.name=name;
	}

}
