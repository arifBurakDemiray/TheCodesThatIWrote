import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class FileIO implements IFileIO {
	public FileIO() {}
	public IBinaryST<IMedia> readInventory() throws IOException  {
		File file = new File("CENG112_HW4_Media.txt");
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String satir = reader.readLine();
        IBinaryST<IMedia> tree = new BinaryST<IMedia>();
        while (satir.length()>1) {
        	IMedia medias=null;
	   		String[] listOfMedias=satir.split(",");
	   		if(listOfMedias[0].equals("Book")) {
	   			medias = new Book(Integer.valueOf(listOfMedias[2]),Integer.valueOf(listOfMedias[3]),listOfMedias[1],listOfMedias[4]);}
	   		else {
	   			if(listOfMedias.length==7) {
	   				medias = new Movie(Integer.valueOf(listOfMedias[2]),Integer.valueOf(listOfMedias[3]),listOfMedias[1],listOfMedias[4],listOfMedias[5],listOfMedias[6]);
	   			}
	   			else {
	   				medias = new Movie(Integer.valueOf(listOfMedias[2]),Integer.valueOf(listOfMedias[3]),listOfMedias[1],listOfMedias[4],listOfMedias[5],null);
	   				
	   			}
	   		}
	   		tree.add(medias);
		   	satir = reader.readLine();}
           
        reader.close();
        return tree;
        }}
        


