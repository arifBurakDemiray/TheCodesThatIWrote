//Again its our weather class and like you said.
// has getters setters and constructor.
public class Weather {
	private int wind;
	private int temp;
	private int fltemp;
	private int humidity;
	private int precip;
	private String visib;
	
	public Weather(int wind,int temp,int fltemp,int humidity,int precip,String visib) {
		this.wind = wind;
		this.temp = temp;
		this.fltemp = fltemp;
		this.humidity = humidity;
		this.precip = precip;
		this.visib = visib;
	}

	public int getWind() {
		return wind;
	}

	public void setWind(int wind) {
		this.wind = wind;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getFltemp() {
		return fltemp;
	}

	public void setFltemp(int fltemp) {
		this.fltemp = fltemp;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getPrecip() {
		return precip;
	}

	public void setPrecip(int precip) {
		this.precip = precip;
	}

	public String getVisib() {
		return visib;
	}

	public void setVisib(String visib) {
		this.visib = visib;
	}
	
	
	
	
	
	
	
	
	
	
}

