import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
//in here we have our FileIO class
//we are reading two files at he same time
public class FileIO {
	public FileIO() {}
	@SuppressWarnings("deprecation")
	public Liste[] readCSV() throws IOException {// these parts are for reading file
		File file = new File("CENG211_HW1_WeeklyForecast.csv");
		File file1 = new File("CENG211_HW1_Cities.csv");
        BufferedReader reader = null;
        BufferedReader reader1=null;
        reader = new BufferedReader(new FileReader(file));
        reader1 = new BufferedReader(new FileReader(file1));
        String satir = reader.readLine();
        String line = reader1.readLine();
        Liste regionList= new Liste();//this liste for regions that we are gonna create
        Liste weatherCityList = new Liste();//and this list for weatherCity objects.
        boolean found=false;//this is for creating new region
        while (line!=null) {
        	String[] listOfItems=line.split(",");//first file
        		for(int i=0;i<regionList.getListe().length;i++) {//every time it searches for the newcomes regionID and our regionIDs
        			Region tempo = (Region) regionList.getListe()[i];//our regionIDs
        			if(tempo!=null) {//for avoiding extra works
        				if(Integer.valueOf(listOfItems[2]).equals(tempo.getID())) {//if they have same regionID
        					City newCity = new City(Integer.valueOf(listOfItems[0]),Integer.valueOf(listOfItems[4]),((Region) regionList.getListe()[i]),listOfItems[1]);
        					((Region) regionList.getListe()[i]).getCities().add(newCity);//it creates new city and adds it to the our region
        					for(int a=0;a<7;a++) {//this is for the city that processing know creates  cityWeather for it.
        						String[] listOfWe = satir.split(",");//second file,creates weather below
        			        	Weather temp= new Weather(Integer.valueOf(listOfWe[2]),Integer.valueOf(listOfWe[3]),Integer.valueOf(listOfWe[4]),Integer.valueOf(listOfWe[5]),Integer.valueOf(listOfWe[6]),listOfWe[7]);
        			        	Date tempDate = new Date();//creates date for this
        			        	String[] dateZones =String.valueOf(listOfWe[1]).split("\\.");//splitting day month year
        			        	tempDate.setDate(Integer.valueOf(dateZones[0]));
        			        	tempDate.setMonth(Integer.valueOf(dateZones[1]));
        			        	tempDate.setYear(Integer.valueOf(dateZones[2]));
        			        	CityWeather tempCityW = new CityWeather(newCity,temp,tempDate);//finally creates the city weather and adds it
        						weatherCityList.add(tempCityW);
        						satir=reader.readLine();//for the next day for this city
        						}
        					found=true;//means we found our region for this city
        					break;//for not going on the other regions
        					}
        				else//couldnt found our region
        					found=false;}
        			else {
        				break;}}//if region null finish the for loop.
        		if(found==false) {//if we couldnt find our region
            		Region newRegion = new Region(Integer.valueOf(listOfItems[2]),listOfItems[3]);//creates new region,creates new city
            		City newCity = new City(Integer.valueOf(listOfItems[0]),Integer.valueOf(listOfItems[4]),newRegion,listOfItems[1]);
    				newRegion.setCities(new Liste());//creates liste for its city list because city list attribute is null.
    				newRegion.getCities().add(newCity);//adds the city for that
    				regionList.add(newRegion);//adds the our region to the region list.
    				for(int a=0;a<7;a++) {//this part same as above part.
    					String[] listOfWe = satir.split(",");
    		        	Weather temp= new Weather(Integer.valueOf(listOfWe[2]),Integer.valueOf(listOfWe[3]),Integer.valueOf(listOfWe[4]),Integer.valueOf(listOfWe[5]),Integer.valueOf(listOfWe[6]),listOfWe[7]);
    		        	Date tempDate = new Date();//these blocks for date
    		        	String[] dateZones =String.valueOf(listOfWe[1]).split("\\.");
    		        	tempDate.setDate(Integer.valueOf(dateZones[0]));
    		        	tempDate.setMonth(Integer.valueOf(dateZones[1]));
    		        	tempDate.setYear(Integer.valueOf(dateZones[2]));
    		        	CityWeather tempCityW = new CityWeather(newCity,temp,tempDate);//finally we create our city and add them up
						weatherCityList.add(tempCityW);
    					satir=reader.readLine();
    					}}
        		line = reader1.readLine();
		   		}

	        reader.close();
	        reader1.close();
	        Liste[] listsOfListes = new Liste[2];//these list for returning two lists at the same time
	        listsOfListes[0]=regionList;
	        listsOfListes[1]=weatherCityList;
	        return listsOfListes;
	        
	}}


