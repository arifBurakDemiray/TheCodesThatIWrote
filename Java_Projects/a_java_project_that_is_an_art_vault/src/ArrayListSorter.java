import java.util.ArrayList;
//Here our array list sorter class.
public class ArrayListSorter {
	/**
	 * @param liste is of type object. it is going to be sorted.
	 * @param durum is tell us what kind of sort are we going to do.
	 * @return 's sorted list.
	 * @throws Exception if the objects that are going to compared have not same type.
	 */
	//durum = 0 means sorting by names.
	//durum = 1 means sort Artwork by styles and sort Person by birth date.
	//durum = 2 means sort Painting by artist names and sort Person by nationalities.
	//durum = 3 means sort Sculpture by material.
	//durum = 4 means sort Sculpture by artist names.
	//we used static method to call it without class' constructor.
	public static ArrayList<Object> sort(ArrayList<Object> liste, int durum) throws Exception{//Here takes the list and the durum.
		ArrayList<Object> tempL = new ArrayList<Object>();//we are using temporary list for sorting 
		for(Object bla:liste) {                          // because we do not want to change the original list.
			tempL.add(bla);}//here we created the temporary list.
		int length = tempL.size();//length of the original list.
		for(int i = 0; i< length-1;i++) {//we wrote a bubble sort. This 2 for loop sort by name.
			for(int j = i+1; j< length;j++) {
				if(tempL.get(0) instanceof Artwork) {//if the object artwork. We look for get(0) is artwork why because the original does not have mixed types.
				Artwork temp = (Artwork) tempL.get(j);//only have one type. we create again clones of 
				Artwork temp1 = (Artwork) tempL.get(i);// this and this+1.
				if(temp.compareTo(temp.getName(), temp1.getName())<0) {//Here the compareTo that we wrote works
					tempL.set(j,temp1);//if this less than this+1 than here swap.
					tempL.set(i,temp);}}
				else if(tempL.get(0) instanceof Person){//this is for Person class
				Person temp3 = (Person) tempL.get(j);//the operations are same as above.
				Person temp4 = (Person) tempL.get(i);
				if(temp3.compareTo(temp3.getName(), temp4.getName())<0) {
					tempL.set(j,temp4);
					tempL.set(i,temp3);}}}}
		//first bubble sort does this by name.
		//with doing 2 bubble sort we guarantee that all durums will be sorted by name.
		if(durum==1 || durum==2 || durum==3 || durum==4) {//Here the other durums.
			for(int i = 0; i< length-1;i++) {// Again a bubble sort.
				for(int j = i+1; j< length;j++) {
					if(tempL.get(0) instanceof Artwork) {//again looking for artwork
						if(durum==1) {//durum = 1 Styles for Artwork class.
							Artwork temp = (Artwork) tempL.get(j);//again cloning.
							Artwork temp1 = (Artwork) tempL.get(i);
							if(temp.compareTo(temp.getStyle(), temp1.getStyle())<0) {//this time compares by Styles.
								tempL.set(j,temp1);//swap operation.
								tempL.set(i,temp);}}
						else if(durum==2 ) {//durum = 2 Artist names for Painting class.
							if(tempL.get(0) instanceof Painting) {
							Painting temp = (Painting) tempL.get(j);
							Painting temp1 = (Painting) tempL.get(i);
							if(temp.compareTo(temp.getArtist().getName(), temp1.getArtist().getName())<0) {//compares by artist names.
								tempL.set(j,temp1);//swap
								tempL.set(i,temp);}}}
							else{// durum = 3,4 Material and Artist names sorting for sculpture.
								if(tempL.get(0) instanceof Sculpture) {
								Sculpture temp = (Sculpture) tempL.get(j);// create copies.
								Sculpture temp1 = (Sculpture) tempL.get(i);
								if(durum==4) {
								if(temp.compareTo(temp.getArtist().getName(), temp1.getArtist().getName())<0) {//by artist names.
									tempL.set(j,temp1);//swap
									tempL.set(i,temp);}}
								else if(durum==3) {
									if(temp.compareTo(temp.getMaterial(), temp1.getMaterial())<0) {//by materials.
										tempL.set(j,temp1);//swap
										tempL.set(i,temp);}}}}}
					else {
						if(tempL.get(0) instanceof Person) {//looks for Person class.
					Person temp3 = (Person) tempL.get(j);//copying
					Person temp4 = (Person) tempL.get(i);
					if(durum==2) {//by Nationalities
						if(temp3.compareTo(temp3.getCountry(), temp4.getCountry())<0) {
							tempL.set(j,temp4);//swap
							tempL.set(i,temp3);}}
					else if(durum==1) {//by birth date
					if(temp3.compareTo(temp3.getBirthdate(), temp4.getBirthdate())<0) {
						tempL.set(j,temp4);//swap
						tempL.set(i,temp3);}}}}}}}
		return tempL;}}//returns the sorted list that is temporary.
