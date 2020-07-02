#ifndef GRAPH_HPP
#define GRAPH_HPP

#include <vector>
#include <stack>
using std::vector;
class GraphAdjMat {
public:
        GraphAdjMat(int V);
        ~GraphAdjMat();

        int V() const { return m_V; }
        int E() const { return m_E; }

        void insert(int v, int w);
        void print() const;

        // computes the shortest path from source to destination and returns the
        // number of nodes in the path. It returns -1 if the destination is not
        // reachable from source.
        int shortest_path(int src, int dst, std::stack<int>& path);

        // computes the connected components and returns the number of connected
        // components. The ids array contains the connected component id of each
        // node.
        int connected_components(std::vector<int> &ids);
private:
        int m_V;
        int m_E;
        vector<vector<int>> m_matrix;
};

#endif
