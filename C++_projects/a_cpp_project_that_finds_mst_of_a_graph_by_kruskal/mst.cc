// Arif Burak Demiray 
// Bekir Yoruk 
#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <sstream>
using namespace std; 
struct Edge    // This is the edges struct of our graph
{ 
    int weight;
    char st; // Start node of this edge
    char fs; // End node of this edge
  
    // Constructor
    Edge(int weight,char st,char fs) 
    { 
        this->weight = weight; 
        this->st = st; 
        this->fs = fs;
    } 
    // This method searchs thorugh a char list for a given char.
    // If it returns false it means there is a char that equal to given char.
    bool search(char a, vector<char> marked, int loop){
        for(int i=0;i<loop;i++){
            if(a==marked[i])
                return false;}
        return true;
    }
    //This is my MST. We used Kruskal Algorithm for calculating MST of a graph.
    int MST(vector<Edge> edges, int loop,int e); // e is edge number
}; 
  
int Edge::MST(vector<Edge> edges, int loop,int e) 
{   
    int mst_wt = 0; // Initialize result 
    //this line sorts the list by weights for kruskal algorithm
    sort(edges.begin(), edges.end(), [](const Edge& fs, const Edge& st) {return fs.weight < st.weight;});
    vector<char> marked; // visited chars list
    mst_wt+=edges[0].weight; // We directly added first one.
    printf("\nEdges of MST:\n");
    printf("\n%c %c\n",edges[0].fs,edges[0].st);
    marked.push_back(edges[0].fs);// We are adding directly first ones vertices to the visited list.
    marked.push_back(edges[0].st);
    for(int i=1;i<e;i++){
        if(search(edges[i].st,marked ,loop) | search(edges[i].fs,marked ,loop) ){
            printf("%c %c\n",edges[i].fs,edges[i].st);// printing MST edges
            mst_wt+=edges[i].weight; // if it okey for kruskal algorithm it increases the cost.
            if(search(edges[i].st,marked ,loop))
                marked.push_back(edges[i].st); // adding visited vertices
            else if(search(edges[i].fs,marked ,loop)) // wht two if, because one of them might have been already in the visited list.
                marked.push_back(edges[i].fs); // adding visited vertices
        }
    }
    printf("\n");
    return mst_wt; 
} 

int main() 
{ 
    vector<Edge> edges;
    int edN = 0; // edge nums
    printf("Give us the vertex number(s): ");
    int x;
    scanf("%d",&x); // vertex num
    printf("\nNow, give us the edges of the graph:\n\n");
    for(int i = 0; i<x+1; i++){
        string line;
        getline(cin,line); // We are getting line by line the input
        istringstream iss(line); // for travelling thorugh the line token by token
        char first; // the first char in a line
        iss >> first;
        char others; // first one's adjacents
        int weight; // weight of edges one by one while looping
        while(iss >> others){ // for not getting duplicate elements at the line's finish
            if(iss >> weight){ // same reason
                if((others >= 'a') & (others <= 'z') & (first >= 'a') & (first <= 'z')){ // vertices must be in range a-z
                Edge temp = Edge(weight,first,others); // creating edges
                edges.push_back(temp); // adding edges
                edN++;} // increasing edge num
                else
                    printf("\nYour edges must be in range a-z !\n\n");
                }
        }      
  }      
    int wt = edges[0].MST(edges,x,edN);
    cout << "Cost of MST: " << wt; // cost of minimum spanning tree
    printf("\n");
  
    return 0; 
} 