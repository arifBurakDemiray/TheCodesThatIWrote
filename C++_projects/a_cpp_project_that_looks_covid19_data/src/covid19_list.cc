#include <cstdlib>
#include <cstring>
#include <iostream>
#include <iomanip>
#include <string>
#include <algorithm>
#include <locale>


#include "quicksort.hpp"
#include "covid19_data.hpp"
#include "covid19_data.cc"

using namespace std;

void print_usage(const char *prog_name)
{
        cerr << "Usage: " << prog_name << " <data-filename> [options]" << endl;
        cerr << "Options:" << endl;
        cerr << "  --by-total : sorts by total cases" << endl;
        cerr << "  --by-deaths : sorts by new deaths" << endl;
        cerr << "  --by-total-and-deaths : sorts by total deaths" << endl;
        cerr << "  -n <N> : display top N items instead of 10" << endl;
}


int main(int argc, char** argv)
{
     
        if (argc < 2) {
                print_usage(argv[0]);
                return EXIT_FAILURE;
        }

        int sort_id = 0;
        int n=10;
        for (int i = 2; i < argc; ++i) {
                if(strcmp(argv[i], "total") == 0){
                         sort_id = 1;}
                else if((strcmp(argv[i], "total") == 0) && (strcmp(argv[i+2], "deaths") == 0)) {
                        sort_id = 3;
                        break;
                }
                else if(strcmp(argv[i], "deaths") == 0){
                         sort_id = 2;
                }
                else if(strcmp(argv[i], "n") == 0){
                        n = (int)argv[n+1];
                }
             
        }



        std::vector<Covid19Data> cov_data;
        std::vector<int> numbers;
        std::string location;
        std::string date;
        load_covid19_data(cov_data, numbers, location, date,  argv[1]);


        quicksort_generic(cov_data, Covid19DataCmp(sort_id));

        string cdl[n];
        bool flag = true;
        int looker = 1;
        for (const Covid19Data& cd: cov_data) {
                if(flag){
                        cdl[0]=cd.location;
                        cout << cd.numbers[sort_id] << ", " << cd.location << ", " << cd.date << endl;
                        flag=false;}
                if(looker==n)
                        break;
                else{
                        bool control = true;
                        for(int i=0;i<looker;++i){
                                if(cdl[i]==cd.location)
                                        control = false;
                        }
                        if(control){
                                cout << cd.numbers[sort_id] << ", " << cd.location << ", " << cd.date << endl;
                                cdl[looker]=cd.location;
                                 looker++;
                        }

                }
        }

        return EXIT_SUCCESS;
}
