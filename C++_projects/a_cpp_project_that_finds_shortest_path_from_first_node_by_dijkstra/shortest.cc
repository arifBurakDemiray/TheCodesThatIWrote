/* Bekir Yoruk 
   Arif Burak Demiray */
#include <iostream>
#include <vector>
#include <queue>
#include <sstream>
using namespace std;

// Finding shortest paths to first vertex
void shortestPaths(vector< vector<pair<int, int> > >& adjList)
{
    // for holding shortest paths, creating a vector
    vector<int> distance;

    // Initializing all paths as infinite.
    int n = adjList.size();
    for (int i = 0; i < n; i++)
    {
        distance.push_back(1000000007);
    }

    // Creating a priority queue
    priority_queue<pair<int, int>, vector< pair<int, int> >, greater<pair<int, int> > > pq;

    // Add first node to pq
    pq.push(make_pair(0, 0));
    // Initializing shortest paths to itself  as 0
    distance[0] = 0;

    while (!pq.empty())
    {
        //take the top of the list, it means top of the rest of the vertices
        int first = pq.top().first;
        // we take it back because we are going to visit it, don't need to add
        pq.pop();

        // Visit all of first's friends.
        for (int i = 0; i < adjList[first].size(); i++)
        {
            int adjacent = adjList[first][i].first;
            int weight = adjList[first][i].second;

            // If the distance to adjacent is shorter, we update the distance of adjacent.
            //Then adding adjacent to priority queue
            if (distance[adjacent] > distance[first] + weight)
            {
                distance[adjacent] = distance[first] + weight;
                pq.push(make_pair(adjacent, distance[adjacent]));
            }
        }
    }

    // printing shortest paths
    for (int i = 1; i < distance.size(); i++)
    {
        cout << static_cast<char>(i + 97) << " " << distance[i] << endl;
    }
}

int main()
{
    printf("Give us the vertex number(s): ");
    int V; // vertex number
    cin >> V;
    
    // Creating our adjList 
    vector< vector<pair<int, int> > > adjList;

    // Adding its own edges to each vertex
    for (int i = 0; i < V; i++)
    {
        vector<pair<int, int> > lane;
        adjList.push_back(lane);
    }

    //Adding edges to adjList
    printf("\nNow, give us the edges of them:\n\n");
    for (int i = 0; i < V + 1; i++) {
        string line;
        getline(cin, line);
        istringstream iss(line);
        char first;
        iss >> first;
        char others;
        int weight;
        while (iss >> others) {
            if (iss >> weight) {
                if ((others >= 'a') & (others <= 'z') & (first >= 'a') & (first <= 'z')) {
                    adjList[(int)first - 97].push_back(make_pair((int)others - 97, weight));
                }
                else
                    printf("\nYour edges must be in range a-z !\n\n");
            }
        }
    }
    cout << endl;

    // finds shortest paths and prints them.
    shortestPaths(adjList);

    return 0;
}