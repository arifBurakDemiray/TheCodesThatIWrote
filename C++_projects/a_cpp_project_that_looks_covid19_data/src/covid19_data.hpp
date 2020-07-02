#ifndef COVID19_DATA_HPP
#define COVID19_DATA_HPP

#include <string>
#include <vector>

using namespace std;

struct Covid19Data {
        std::string date;
        std::string location;
        std::vector<int> numbers;    
};
class Covid19DataCmp
{
public:
        Covid19DataCmp(int sort_id) {
                m_sort_id = sort_id;
        }

        bool operator()(const Covid19Data& cd0, const Covid19Data& cd1) const {
                if (m_sort_id == 0)
                        return cd0.numbers[0] > cd1.numbers[0];
                else if (m_sort_id==1)
                        return cd0.numbers[1] > cd1.numbers[1];           
                else if (m_sort_id==2)
                        return cd0.numbers[2] > cd1.numbers[2];
                else
                        return cd0.numbers[3] > cd1.numbers[3];
                }
        
private:
        int m_sort_id;
};


void load_covid19_data(std::vector<Covid19Data> &covid19_data,
                          std::vector<int> &numbers, std::string &location, std::string &date,
                          const std::string &filename);

#endif
