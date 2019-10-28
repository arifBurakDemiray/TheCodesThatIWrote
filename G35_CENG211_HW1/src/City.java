//Here our city class,it has attributes like you wrote on the HW pdf.
//It has getters and setters of that attributes. And it has constructor.
public class City {
	private int plate;
	private int altitude;
	private Region region;
	private String name;
	public City(int plate,int altitude,Region region,String name) {
		this.plate=plate;
		this.altitude=altitude;
		this.region=region;
		this.name=name;
	}
	public int getPlate() {
		return plate;
	}
	public void setPlate(int plate) {
		this.plate = plate;
	}
	public int getAltitude() {
		return altitude;
	}
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	};
}
