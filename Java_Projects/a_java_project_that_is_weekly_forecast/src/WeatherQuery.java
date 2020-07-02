import java.io.IOException;
import java.util.Scanner;
//here our the longest class
public class WeatherQuery {
	public WeatherQuery() {
		
	}
	public String lowestFeelLikeTemp() throws IOException {//because fileIO weeklyForecast has it
		WeeklyForecast weekF = new WeeklyForecast();//for getting the list of cityWeathers
		CityWeather[][] weeklyF = weekF.weeklyForecast();
		int fltOrt = 0;//this is average of flts
		int ortOfFlt = 0;//this holds before average flt
		String name = "";//this holds the city's that has lowest feel like temp name 
		int c=1;// for reducing for loops.
		for(int i=1;i<82;i++) {//it first looks for a city days then it looks for all cities
			for(int a=1;a<8;a++) {
				fltOrt += weeklyF[i][a].getWeather().getFltemp();}//adds the days' flts
			fltOrt=(Integer)fltOrt/7;//takes average
			if(i==1) {//for first entry because at first we have nothing to compare with
				ortOfFlt=fltOrt;
				name=weeklyF[i][c].getCity().getName();}
			else {
				if(fltOrt<ortOfFlt){//for finding the lowest flt cities
					ortOfFlt=fltOrt;
					name=weeklyF[i][c].getCity().getName();}
				else if(fltOrt==ortOfFlt) {//if we have same lowest flt
					name+=","+weeklyF[i][c].getCity().getName();}}
			fltOrt=0;}//if we do not clear it it is gonna rise
		return "1) "+name;}
	
	@SuppressWarnings("deprecation")
	public void suitableForFlyingDrones() throws IOException{//because fileIO
		FileIO reader = new FileIO();
		Liste listOfWeather = reader.readCSV()[1];//the list of city weathers it has 567 elemnts.
		boolean flag = true;//this is for printing out correctly
		Scanner alıcı = new Scanner(System.in);//gets a city name. Turkish characters supported.
		System.out.println("6) Enter a city name to view flightworthy days:");
		String cityName = alıcı.nextLine();
		for(int i=0;i<listOfWeather.getListe().length;i++) {
			CityWeather cities = (CityWeather)listOfWeather.getListe()[i];//the if block searchs for given city and searchs for conditions that you gave
			if(cities!=null && cityName.equals(cities.getCity().getName()) && (!cities.getWeather().getVisib().equals("low")) && (cities.getWeather().getWind()<40)) {
				if(flag==false) {//false means we printed first entry
					System.out.print(","+cities.getDate().getDate()+"."+cities.getDate().getMonth()+"."+cities.getDate().getYear());}
				else {//true means we printing first
					System.out.print(cities.getDate().getDate()+"."+cities.getDate().getMonth()+"."+cities.getDate().getYear());
					flag=false;}}}
		alıcı.close();}
	
	@SuppressWarnings("deprecation")
	public void rainyInTwoDays() throws IOException {//because fileIO
		FileIO reader = new FileIO();
		Liste listOfWeather = reader.readCSV()[1];//again takes the city weather list
		CityWeather temp=null;//tomorrow
		CityWeather temp1=null;//dayaftertomorrow
		boolean flag = true;// again same reasen as above printing out correctly.
		String cityName="";
		for(int i=0;i<listOfWeather.getListe().length;i++) {
			CityWeather cities = (CityWeather)listOfWeather.getListe()[i];//today
			if(i!=0 && i!=1 && cities!=null && String.valueOf(temp1.getDate().getDate()).equals("14") && (temp1.getCity().getName().equals(cities.getCity().getName())) && (temp1.getCity().getName().equals(temp.getCity().getName()))){
				//i=0,1 means temp and temp1 must not be null. today is 14.10.2019. and looks for same city to look rainy days.
				if((temp.getWeather().getPrecip()>=80) && (cities.getWeather().getPrecip()>=80)) {//if rainy in two next days
					if(!temp1.getCity().getName().equals(cityName)) {//for not repeating same city name
						if(flag==false) {
							System.out.print(","+temp1.getCity().getName());
							}
						else {
							System.out.print("5) "+temp1.getCity().getName());
							flag=false;}}
					cityName=temp1.getCity().getName();}}//this is for controlling not repeating same city name
			temp1=temp;//changes to the next city weather
			temp=cities;}}//changes to the next city weather
	
	public void meanTemperatureHAndLCities() throws IOException {//because fileIO
		FileIO reader = new FileIO();
		Liste listOfRegion = reader.readCSV()[0];//regions list
		Liste tempCities = new Liste();
	    City temp = new City(0, 0, null, null);//this is for highest altitude
	    City temp1 = new City(0, 1000000000, null, null);//this is for lowest altitude
		for(int i=0;i<listOfRegion.getIndex()-1;i++) {//this is for searching through regions.
			Region tempR = (Region) listOfRegion.getListe()[i];
			for(int b=0;b<tempR.getCities().getIndex()-1;b++) {//this is for searching through cities.
				City tempC = (City)tempR.getCities().getListe()[b];//for not repeating right side's codes and holds processing city
				if(tempC.getAltitude()>temp.getAltitude()) {//for highest altitude
					temp.setAltitude(tempC.getAltitude());//for comparing others
					tempCities.getListe()[0]=tempC;//takes the city to print in index 0 we held highest
				}
				else if(tempC.getAltitude()==temp.getAltitude()) {//if we have same altitude 
					temp.setAltitude(tempC.getAltitude());
					tempCities.add(tempC);//if we have same adds it to the liste
				}
				if(tempC.getAltitude()<temp1.getAltitude()) {//for lowest altitude
					temp1.setAltitude(tempC.getAltitude());
					tempCities.getListe()[1]=tempC;}//in the index 1 we held the lowest
				else if(tempC.getAltitude()==temp1.getAltitude()) {//if we have same altitude
					temp1.setAltitude(tempC.getAltitude());
					tempCities.add(tempC);}}}
		float highest=0;
		float lowest = 0;
		City tempC1 = (City) tempCities.getListe()[0];//highest
		City tempC2 = (City) tempCities.getListe()[1];//lowest
		Liste listOfWeather = reader.readCSV()[1];//cityweather list
		for(int a=0;a<listOfWeather.getIndex()-1;a++) {
			CityWeather tempCW = (CityWeather)listOfWeather.getListe()[a];
			if(tempCW!=null && tempCW.getCity().getName().equals(tempC1.getName())) {//searchs for the highest city
				highest +=tempCW.getWeather().getTemp();//adds its temperatue
			}
			if(tempCW!=null && tempCW.getCity().getName().equals(tempC2.getName())) {//searchs for the lowest city
				lowest +=tempCW.getWeather().getTemp();}}
		System.out.println("4) "+(highest/7)+","+(lowest/7));}//gets average and prints out
	
	public void highestAverageHumidity() throws IOException {//because fileIO that weeklyforecast has
		WeeklyForecast week = new WeeklyForecast();
		CityWeather[][] listOfCities= week.weeklyForecast();
		double avHumidity = 0;//holds a regions total humidity
		int index = 0;//for going through regionNames list.
		String[] regionNames = new String[7];// this list holds average humidity and its region name.
		for(int i=1;i<listOfCities.length;i++) {
			if(listOfCities[i][1]!=null) {//because of the algorithm that we wrote we are gonna have null object.
				Liste tempCL =  listOfCities[i][1].getCity().getRegion().getCities();//gets the the cities region then from there gets cities list.
			for(int a=0;a<tempCL.getIndex()-1;a++) {//through city list of that region
				if(tempCL.getListe()[a]!=null) {//we have null objects at the ends
					for(int b=1;b<8;b++) {//through days
						if(listOfCities[((City) tempCL.getListe()[a]).getPlate()][b]!=null) {//in that part it goes through city list and adds its days' humidity to the total
						avHumidity += listOfCities[((City) tempCL.getListe()[a]).getPlate()][b].getWeather().getHumidity();
						listOfCities[((City) tempCL.getListe()[a]).getPlate()][b]=null;}}}}// when we finished all days of that city it deletes this city to not process this city again.
			String namesOR = (""+avHumidity/tempCL.getIndex()+","+((City) tempCL.getListe()[1]).getRegion().getName());//takes average according to this region city number.
			regionNames[index]=namesOR;//adds to the our region list.
			index++;//for next region
			avHumidity = 0;//if we do not clear that it will gonna rise and we cannot have proper results.
			if(regionNames[6]!=null)//means we have all regions so we do not have to work anymore.
				break;}}
		String temp="";
		double tempHum =  0;
		for(int d=0;d<regionNames.length;d++) {//this loop for searching the highest humidity.
			String[] splitted = regionNames[d].split(",");
			if(Double.valueOf(splitted[0])>tempHum) {
				temp = splitted[1];
				tempHum = Double.valueOf(splitted[0]);}}
	System.out.println("3) "+temp);}//finally printed out the highest.
	
	public void highestTemperatureVariation() throws IOException {//because we have weekly forecast and it have file ıo and it has readers.
		WeeklyForecast tempWF = new WeeklyForecast();
		CityWeather[][] cw = tempWF.weeklyForecast();
		double artOrt = 0;//the average temperature of one city
		double square = 0;//the square of the difference from artOrt of each day.
		Liste nameOfVari = new Liste();//standard deviations and city names.
		for(int i=1;i<cw.length;i++) {//this for loop is for finding artOrt.
			for(int a=1;a<8;a++) {
				artOrt += cw[i][a].getWeather().getTemp();
			}
			artOrt = artOrt/7;
			for(int b=1;b<8;b++) {//this for loop is for finding difference and getting square.
				square += (cw[i][b].getWeather().getTemp()-artOrt)*(cw[i][b].getWeather().getTemp()-artOrt);
			}
			square = square/6;//this is for standard deviation.
			double temp =0;//this do while loop is for getting squareRoot of square.
			double squareRoot = square/2;
			do {
				temp=squareRoot;
				squareRoot=(temp+(square/temp))/2;
			}
			while((temp-squareRoot) != 0);
			String tempVar = ""+squareRoot+","+cw[i][1].getCity().getName();//finally we have all standard deviations of each city.
			nameOfVari.add(tempVar);
			artOrt=0;//for next 
			square=0;
		}
		boolean sorted = false;//that while loop for sorting the nameOfVari.
		String temp;//bubble sort.
		while(!sorted) {
			sorted=true;
			for(int x=0;x<nameOfVari.getListe().length-1;x++) {
				if(nameOfVari.getListe()[x+1]==null)
					break;
				String[] temp10 = ((String) nameOfVari.getListe()[x]).split(",");
				String[] temp20 = ((String) nameOfVari.getListe()[x+1]).split(",");
				if(Double.valueOf(temp10[0])>Double.valueOf(temp20[0])) {
					temp = (String) nameOfVari.getListe()[x];
					nameOfVari.getListe()[x]=nameOfVari.getListe()[x+1];
					nameOfVari.getListe()[x+1]=temp;
					sorted=false;}}}//finally we have sorted list.
		System.out.print("2) ");
		int degisken = 0;//this is for printing out top 3 cities
		int len = nameOfVari.getIndex()-1;//the top elements at the end so we had to start looking for from the end.
		for(int m=0;m<nameOfVari.getIndex();m++) {
			String[] splitted = ((String) nameOfVari.getListe()[len-m]).split(",");
			String[] splitted1 = ((String) nameOfVari.getListe()[len-m-1]).split(",");
			if(splitted[0].equals(splitted1[0])) {//if they have same variation.
				System.out.print(splitted[1]+","+splitted1[1]);
				degisken++;}
			else if(degisken==3)//if we printed out the top 3.
				break;
			else {
				System.out.print(splitted[1]+",");
				degisken++;}}}
	}

