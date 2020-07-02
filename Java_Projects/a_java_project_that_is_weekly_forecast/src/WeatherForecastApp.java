import java.io.IOException;

public class WeatherForecastApp {
	/**
	 * @author Arif Burak Demiray
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("This program gives you the weather forecast of this week in Turkey ");
		System.out.println("We have 6 conditions to give you knowledge about Turkey's weather forecast!");
		System.out.println("First condition gives the city(s) that has the lowest feels-like temperature!");
		System.out.println("Second condition gives the top 3 cities that have the highest temperature variation!");
		System.out.println("Third condition gives the region that has the highest average humidity!");
		System.out.println("Fourth condition gives the cities that have the highest and the lowest altitude cities' mean temperatures!");
		System.out.println("Fifth condition gives the cities that are rainy in the next two days!");
		System.out.println("Last condition takes a city name from you and prints out the day(s) that a drone can fligt!");
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("If you are ready here the results!!");
		System.out.println("-------------------------------------------------------------------------------------------");
		WeatherQuery query = new WeatherQuery();
		System.out.println( query.lowestFeelLikeTemp());
		query.highestTemperatureVariation();
		System.out.println();
		query.highestAverageHumidity();
		query.meanTemperatureHAndLCities();
		query.rainyInTwoDays();
		System.out.println("");
		query.suitableForFlyingDrones();
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------");
		System.out.println("Have a nice day :)");
}}
