import java.io.IOException;

public class WeeklyForecast {
	private CityWeather[][] weeklyForecast;//this holds for example [35][1] means the first day of izmir
	public WeeklyForecast() {}
	public CityWeather[][] weeklyForecast() throws IOException {
		FileIO reader = new FileIO();//for getting all lists from fileIO
		Liste weeklyList = reader.readCSV()[1];//this is the weather lists,and its lenght is 567.
		int theLength = (Integer) weeklyList.getIndex()/7;//this is for not repeating the right side codes.
		CityWeather[][] tempWeekly=new CityWeather[theLength+1][8];//82 8 because using the 35 1 the first day of izmir
		int day = 1;//these variables for using just one for loop.
		int city = 1;//if we do not use them we have to create an inner for loop
		for(int i=0;i<(theLength*7);i++) {
			if(i==0) {//for first entry. If we do not have to compare it is not gonna do its work.
				tempWeekly[city][day]=(CityWeather) weeklyList.getListe()[i];
				day++;
			}
			else {
				CityWeather tempW1 = (CityWeather)weeklyList.getListe()[i-1];//for comparing
				CityWeather tempW2 = (CityWeather)weeklyList.getListe()[i];
			if(tempW1.getCity().getName().equals(tempW2.getCity().getName())) {
				tempWeekly[city][day]=(CityWeather) weeklyList.getListe()[i];
				day++;
			}
			else {//if it enters here means the a city days finished we have to move on next city 
				day=1;
				city++;
				tempWeekly[city][day]=(CityWeather) weeklyList.getListe()[i];
				day++;
			}}
		}
		this.setWeeklyForecast(tempWeekly);
		return tempWeekly;
		
}
	public CityWeather[][] getWeeklyForecast() {
		return weeklyForecast;
	}
	public void setWeeklyForecast(CityWeather[][] weeklyForecast) {
		this.weeklyForecast = weeklyForecast;
	}

	
	}


