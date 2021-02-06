//Arif Burak Demiray 


#include "image_pyr.hpp"
#include <iostream>

using ceng391::Image;
using ceng391::ImagePyr;
using std::clog;
using std::endl;
using std::cerr;
using std::string;

void print_usage(const char *prog_name);
void test(string filename,int level);//tests code

int main(int argc, char** argv){ 
    
    if (argc < 3) {
            print_usage(argv[0]);
            return EXIT_FAILURE;
        }
    string filename = argv[1];
    int levels = atoi(argv[2]);
    if(levels<1){
        cerr << "[ERROR][CENG391::ImagePyr] Number of levels must be greater or equal to 1" << endl;
        return EXIT_FAILURE;
    }
    test(filename,levels);
    return EXIT_SUCCESS;
}
void print_usage(const char *prog_name)
{
        cerr << prog_name << " <input_image> <number_of_levels>" << endl;
}
void test(string filename, int level){
    Image *img = Image::from_png(filename, true);//reads given image,it can handle rgba,rgb images by converting them to grayscale images
    ImagePyr* pyr = new ImagePyr(img,level);//creates pyramid object
    pyr->create_pyramids();//creates all levels
    pyr->save_pyramids();//saves all levels
    pyr->pyramid();//you can comment this line if you do not use it, simply it created a pyramid from all levels
    delete pyr;
    delete img;
}