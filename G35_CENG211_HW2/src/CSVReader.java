import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader  {
	//We used static method to call it quickly.
	/**
	 * Reads the csv file of art vault.
	 * @returns an array that has 2 elements which are artwork list and person list.
	 * @throws IOException for file reading.
	 */
	//Static method for again reaching fast without constructor.
	public static Object[] readTheCsvFile() throws IOException {
		File csv = new File("CENG211_HW2_ArtVaultData.csv");//Here the file.
		BufferedReader br1 = new BufferedReader(new FileReader(csv));
		String line = br1.readLine();//For reading line by line.
		Object[] allLists = new Object[2];//Return list.
		ArrayList<Person> arraylist1 = new ArrayList<Person>();//we have determined the array list, where the Person class holds the objects.
		ArrayList<Artwork> arraylist2 = new ArrayList<Artwork>();//we have determined the array list, where the Artwork class holds the objects.
		while(line != null) {//if == null means we ended the csv file.
			/*From the information we read in the range of 20-60 rows, 
			 we created objects according to 5 different classes and added them to the array lists of these classes.
			 */
			String[] liste = line.split(","); 
				switch (liste[0]) {//liste[0] is the key for what object are we gonna create.
				case "1"://Paintings.
					Artist artistimiz = new Artist(liste[3], null, null, null, null);
					Painting obPainting = new Painting(liste[1], liste[2], artistimiz, Integer.valueOf(liste[4]), Integer.valueOf(liste[5]));
					arraylist2.add(obPainting);
					
					break;
				case "2"://Sculptures.
					Artist artistimiz1 = new Artist(liste[3], null, null, null, null);
					Sculpture obSculpture = new Sculpture(liste[1], liste[2], artistimiz1, liste[4], Integer.valueOf(liste[5]));
					arraylist2.add(obSculpture);
					break;
				case "3"://Architectures.
					ArrayList<Architect> archlist = new ArrayList<Architect>();//Architectures have more than one architect.
					for(int i = 6 ; i < liste.length;i++) {
						Architect architect1 = new Architect(liste[i], null, null, null);
						archlist.add(architect1);
					}
					Architecture obArchitecture = new Architecture(liste[1], liste[2], Double.valueOf(liste[3]), Double.valueOf(liste[4]), Double.valueOf(liste[5]), archlist);
					arraylist2.add(obArchitecture);
					break;
				case "4"://Artists.
					ArrayList<String> periodList = new ArrayList<String>();//Artists have more than one period.
					for(int a = 5; a < liste.length;a++) {
						periodList.add(liste[a]);
					}
					Artist artistimiz2 = new Artist(liste[1], liste[2], liste[3], liste[4], periodList);
					arraylist1.add(artistimiz2);
					break;
				case "5"://Architects.
					Architect architect2 = new Architect(liste[1], liste[2], liste[3], liste[4]);
					arraylist1.add(architect2);
					break;

				default:
					break;
				}
				
			line = br1.readLine();
			}
			br1.close();
			allLists[0] = arraylist1;
			allLists[1] = arraylist2;
		//We returned the array that has 2 elements which are artwork list and person list.
		return allLists;}
}