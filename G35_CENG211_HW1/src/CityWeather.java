//Here our cityWeather class. It kinds of city class. Has attributes like you wrote and getters, setters of them.
// and has constructor.
import java.util.Date;

public class CityWeather {
	private City city;
	private Weather weather;
	private Date date;
	
	
	public CityWeather(City city,Weather weather,Date date) {
		this.city = city;
		this.weather = weather;
		this.date = date;
	}
	public Date getDate() {
		
		return date;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
