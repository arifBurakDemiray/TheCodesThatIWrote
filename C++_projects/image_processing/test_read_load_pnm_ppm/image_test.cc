//Arif Burak Demiray 

#include <cstdlib>
#include <iostream>

#include "image.hpp"

using std::clog;
using std::endl;

using ceng391::Image;

int main(int argc, char** argv)
{
        //First Test creating rgba image and set rect area
        Image *img = Image::new_rgba(400, 400);
        img->set_zero();
        img->set_rect(150, 150, 100, 100, 255);
        img->save_as_pnm("/tmp/setrectRgba");
        delete img;

        //Second Test creating rgb image and set rect area
        Image *img1 = Image::new_rgb(400, 400);
        img1->set_zero();
        img1->set_rect(150, 150, 100, 100, 255);
        img1->save_as_pnm("/tmp/setrectRGB");
        delete img1;

        //Third Test creating grayscale image and set rect area
        Image *img2 = Image::new_gray(400, 400);
        img2->set_zero();
        img2->set_rect(150, 150, 100, 100, 255);
        img2->save_as_pnm("/tmp/setrectGray");
        delete img2;

        //Fourth Test reading rgb image and save as rgba.
		Image *img3;
		img3 = img3->read_pnm("rgb.pnm");
		img3->save_as_pnm("/tmp/readedRGB");
        delete img3;

        //Fifth Test reading grayscale image and save as gray
        Image *img4;
		img4 = img4->read_pnm("gray.pnm");
		img4->save_as_pnm("/tmp/readedGray");
        delete img4;

        //Sixth Test reading rgba and converting to grayscale and save.
        Image *img5;
		img5 = img5->read_pnm("rgb.pnm");
        img5->to_grayscale();
		img5->save_as_pnm("/tmp/convertedGray");
        delete img5;

        //Seventh Test reading grayscale and converting to rgba and save.
        Image *img6;
		img6 = img6->read_pnm("gray.pnm");
        img6->to_rgba();
		img6->save_as_pnm("/tmp/convertedRGB");
        delete img6;

        return EXIT_SUCCESS;
}
