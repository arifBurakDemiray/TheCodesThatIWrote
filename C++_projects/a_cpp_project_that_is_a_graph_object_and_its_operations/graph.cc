#include "graph.hpp"
#include <iostream>
#include <iomanip>
#include <queue>
#include <string>
#include <stdio.h>
#include <deque>
#define INFINITY 9999
using namespace std;

GraphAdjMat::GraphAdjMat(int V)
        : m_V(V),
          m_E(0)
{
        m_matrix.resize(V);
        for (int i = 0; i < m_matrix.size(); i++){
                m_matrix[i].resize(V, 0);
    }
       
}

GraphAdjMat::~GraphAdjMat()
{
        for (int i = 0; i < V(); ++i) {
                for(int j = 0; j < V(); ++j){
                        m_matrix[i][j]=0;
                }
        } 
}

void GraphAdjMat::insert(int v, int w)
{
        // check for valid vertex ids, they must be in the range [0, V-1]
        if (v >= V() || w >= V() || v < 0 || w < 0) {
                cerr << "Error: vertex indices for insertion must be in the range [0, " << (V()-1) << "]!" << endl;
                return;
        }

        // Check for self loops, do not allow for simple graphs.
        if (v == w) {
                cerr << "Error: skipping self loop insertion!" << endl;
                return;
        }
        m_matrix[v][w]=1;
        m_matrix[w][v]=1;
        m_E++;
}


void GraphAdjMat::print() const
{
        cout << "V = " << V() << ", E = " << E() << endl;
        // For each vertex, print the vertex id and the list of adjacent vertices
        for (int i = 0; i < V(); ++i) {
                cout << setw(3) << i << ": ";
                for(int j = 0; j < V(); j++) {                               
                        int w = j;
                        if (m_matrix[i][j]==1) {
                                cout << j << " ";
                        }                       
                }
                cout << endl;
        }     
}

// Computes single source shortest paths using breadth first search from source
// using Algorithm 4.2 in the textbook. It returns the shortest path from source
// to destination in the path variable. Actually, for the same source we do not
// need to run the search again since a single run computes all shortest paths
// from the source. In case you need to compute shortest paths from the same
// source it would be better to implement a different API. See Algorithms 4.1
// and 4.2 in the text book.
int GraphAdjMat::shortest_path(int src, int dst, std::stack<int>& path)
{           
        vector<int> can_paths(V(),-1); //child nodes of startnode
        vector<bool> marked(V(),false);
        deque<int> ku; //queue for giving path
        marked[src]=true;
        ku.push_back(src);
        for(int i=0;i<V();i++){
                if(m_matrix[src][i]==1){//looking for startnode's children
                        can_paths[i]=i;
                        marked[i]=true;
                        }
        }
        int looker = 0;
        while(can_paths[dst]!=dst){
                for(int n: can_paths){
                        if(n>=0 & n!=src){
                                int number = 0;
                                int flag = 0;
                                for(int i=0;i<V();i++){
                                        if(m_matrix[n][i]==1){
                                                if(i==dst)
                                                        flag++;
                                                if(!marked[i]){
                                                        can_paths[i]=i;
                                                        marked[i]=true;}
                                                number++;}   
                                        }
                                if(number==1)//means this node does not have appropriate child
                                        can_paths[n]=-1;
                                else if(flag>0){//we found finish node
                                        ku.push_back(n);
                                        can_paths[dst]=dst;//for ending while loop
                                        break;
                                }
                                else
                                        ku.push_back(n);
                        }
                }
                looker++;
                if(looker>=V()){
                        looker=-1;// means there is no path
                        break;
                }
        }
        if(looker<0)
                return -1;
        ku.push_back(dst);
        while (!path.empty())
                path.pop();
        while(!ku.empty()){
                int popped = ku.back();
                path.push(popped);
                ku.pop_back();
        }
        return path.size();
        


}

int GraphAdjMat::connected_components(std::vector<int> &ids)
{
        ids.resize(V());
        if (V() == 0)
                return 0;
        // initially all vertices are unmarked and the initial connected
        // component id is zero.
        vector<bool> marked(V(), false);
        int cc_id = 0;

        queue<int> bfs_queue;
        for (int i = 0; i < V(); ++i) {
                // start a BFS from each unmarked vertex
                if (!marked[i]) {
                        bfs_queue.push(i);
                        while (!bfs_queue.empty()) {
                                int n = bfs_queue.front();
                                bfs_queue.pop();
                                // as we pop items from the queue, label their
                                // ids with the current connected component id.
                                ids[n] = cc_id;
                                 
                                for(int j = 0; j < V(); j++) {                               
                                        if (m_matrix[i][j]==1 & !marked[j]) {
                                                marked[j] = true;
                                                bfs_queue.push(j);
                                        
                                        for(int c = 0; c < V(); c++){
                                                if (m_matrix[j][c]==1 & !marked[c]) {
                                                marked[c] = true;
                                                bfs_queue.push(c);
                                        }
                                        }}           
                                }
                        }
                        // End of BFS. Increase connected component id.
                        ++cc_id;
                }
        }
        // number of components is the value of id at the end
        return cc_id;
}
