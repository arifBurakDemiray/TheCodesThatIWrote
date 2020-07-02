#include "covid19_data.hpp"

#include <cstdlib>
#include <fstream>
#include <iostream>

using namespace std;

void split_by_commas(string &line, vector<string> &items)
{
        items.clear();

        size_t comma_pos = line.find(',');
        while (comma_pos != std::string::npos) {
                items.push_back(line.substr(0, comma_pos));
                line = line.substr(comma_pos+1, line.size());
                comma_pos = line.find(',');
        }

        items.push_back(line);
}


void load_covid19_data(std::vector<Covid19Data> &covid19_data,
                          std::vector<int> &numbers, std::string &location, std::string &date,
                           const std::string &filename)
{
        covid19_data.clear();
        numbers.clear();

        ifstream fin(filename);
        if (!fin.good()) {
                cerr << "Error opening log data file: " << filename << endl;
                exit(EXIT_FAILURE);
        }


        string line;
        getline(fin, line);

        vector<string> items;
       
        while (getline(fin, line)) {
                split_by_commas(line, items);
                Covid19Data cd;
                cd.date = items[0];
                cd.numbers.push_back(stoi(items[2]));
                cd.location = items[1];           
                covid19_data.push_back(cd);
        }

        fin.close();
}
