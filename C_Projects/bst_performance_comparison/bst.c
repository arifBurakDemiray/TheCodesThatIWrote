/*
Arif Burak Demiray
contact burakdemiray09@gmail.com
Constructing BST via array implementation and measuring time of it.
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

//unsorted arrays
int carray1[10];
int carray2[100];
int carray3[1000];

//sorted arrays
int sorted1[10];
int sorted2[100];
int sorted3[1000];

//bst arrays
int bst_array1[50];
int bst_array2[1000000];
int bst_array3[50000000];

int indeks = 0;

char rfilename10[] = "numbers10.txt";
char rfilename100[] = "numbers100.txt";
char rfilename1000[] = "numbers1000.txt";

char wfilename10[] = "numberSorted10.txt";
char wfilename100[] = "numberSorted100.txt";
char wfilename1000[] = "numberSorted1000.txt";

//in this function it puts root to the first place of array
int root(int bstArray[], int root)
{
   if(bstArray[0]!=0){return -1;}
   else{
      bstArray[0] = root;
      return 1;
   }
}

//in this function it puts right child to its place if place is full, it returns -1. 
//it also checks in case parent empty.
int set_right(int bstArray[], int value, int parent)
{
	if(bstArray[parent]==0){return -1;}
	else{
		if(bstArray[2*parent+2]==0){ bstArray[2*parent+2]=value; return 1;}
		else{return -1;}
	}
}

//same logic as above.
int set_left(int bstArray[], int value, int parent)
{
	if(bstArray[parent]==0){return -1;}
	else{
		if(bstArray[2*parent+1]==0){ bstArray[2*parent+1]=value; return 1;}
		else{return -1;}
	}


}

//in this function it constructs the bst via recursion. 
void constructBST(int bstArray[],int unArray[],int parent,int size,int index)
{	//in this adding function, it adds elements sequentally.
	if(size!=index){//looks for if we reached end of array.
		if(unArray[index] < bstArray[parent]){//if parent greater than this value means it is going to be its left child.
			if(set_left(bstArray,unArray[index],parent)!=-1){//if it can add it adds
				set_left(bstArray,unArray[index],parent);
				constructBST(bstArray,unArray,0,size,index+1);//if it can add safely, parentery returns to root node.
			}//else it gives parentery to its left child.
			else{constructBST(bstArray,unArray,2*parent+1,size,index);}
		}
		else{//if value is greater means it is right child. same logic as above.
			if(set_right(bstArray,unArray[index],parent)!=-1){
				set_right(bstArray,unArray[index],parent);
				constructBST(bstArray,unArray,0,size,index+1);
			}
			else{constructBST(bstArray,unArray,2*parent+2,size,index);}
		}

	}
}

//this function reads numbers from file.
//numbers in the first line and seperated via space.
void file_read(int items[],int size, char rfile[]){
	FILE *file; //opens file with read mode.
    file = fopen(rfile,"r");
    char line[1000000];//one line
	fgets(line, sizeof(line), file);
	fclose(file);
	char *num;
	num = strtok(line, " ");    //below, eliminates the spaces.
	int i =0;
	while (num != NULL) {
		if(i!=size){
	        int number = atoi(num);
			items[i]=number;
	        num = strtok(NULL, " ");
	        i++;
	    }   
    }
}

//here code writes the sorted array to the file.
void file_write(int sortedAr[],int size,char wfile[]){ 
	FILE *file;// opens file in write mode
	file = fopen(wfile,"w");
	for(int i = 0; i < size;i++) {
		fprintf(file,"%d ",sortedAr[i]);
	}
	fclose(file);	
}

//this code does the inorder traversel operation.
void inorderTraversel(int parent, int bst_array[],int sortedA[]){ 
	if(bst_array[parent]!=0)
	{
		inorderTraversel((parent*2)+1,bst_array,sortedA);//left subtree
		sortedA[indeks]=bst_array[parent];//here I have global indeks.
		indeks++;//puts items to the sorted array one by one. just parents.
		inorderTraversel((parent*2)+2,bst_array,sortedA);//right subtree
    }
   else{ return;}    
}

//this is the main function and all functions used here.
int main() 
{
	//measurements
	double l1 = 0.0;
	double l2 = 0.0;
	double l3 = 0.0;

	//doing all 100 times
	for(int loop = 0; loop < 100;loop++){
		//here I am setting all bst array to 0.
		for(int i=0;i<50000000;i++){
			if(i<50)
				bst_array1[i]=0;
			if(i<1000000)
				bst_array2[i]=0;
			bst_array3[i]=0;
		}
		//reading all three cases files.
		file_read(carray1,10,rfilename10); 
		file_read(carray2,100,rfilename100); 
		file_read(carray3,1000,rfilename1000); 

		//constructing the bsts and measuring the time.
		clock_t begin1 = clock();
		root(bst_array1,carray1[0]);
		constructBST(bst_array1,carray1,0,10,1);
		clock_t end1 = clock();

		//here inorderTraverseling them and writing to the file. Just one time.
		if(loop==0){
			inorderTraversel(0,bst_array1,sorted1);
			indeks = 0;
			file_write(sorted1,10,wfilename10); 
		}

		clock_t begin2 = clock();
		root(bst_array2,carray2[0]);
		constructBST(bst_array2,carray2,0,100,1);
		clock_t end2 = clock();

		if(loop==0){
			inorderTraversel(0,bst_array2,sorted2);
			indeks = 0;
			file_write(sorted2,100,wfilename100); 
		}

		clock_t begin3 = clock();
		root(bst_array3,carray3[0]);
		constructBST(bst_array3,carray3,0,1000,1);
		clock_t end3 = clock();

		if(loop==0){
			inorderTraversel(0,bst_array3,sorted3);
			indeks = 0;
			file_write(sorted3,1000,wfilename1000); 
		}

		l1 = l1 + (double)(end1 - begin1)/CLOCKS_PER_SEC;
		l2 = l2 + (double)(end2 - begin2)/CLOCKS_PER_SEC;
		l3 = l3 + (double)(end3 - begin3)/CLOCKS_PER_SEC;

	}//taking mean of them.
	printf("Sample size 10 and range is 0-10000 %lf seconds.\n",l1/100);
	printf("Sample size 100 and range is 0-10000 %lf seconds.\n",l2/100);
	printf("Sample size 1000 and range is 0-10000 %lf seconds.\n",l3/100);
   	return 0;
}
