// Arif Burak Demiray
#include <cstdlib>
#include <iostream>
#include <vector>
#include "image.hpp"

using std::clog;
using std::endl;
using ceng391::Image;
using std::vector;
using std::cerr;

using ceng391::Image;
using ceng391::uchar;


void exercise1();
void exercise2();
void exercise3();


int main(int argc, char** argv)
{
        exercise1();
        exercise2();
        exercise3();
        return EXIT_SUCCESS;
	
}

void exercise1(){
        //#################################################################//
        clog << "***** Filtering with 3 Filter and Using 3 Image *****" << endl;
        //edge detection kernel
        //loading an image from data file path
        Image *img1 = Image::from_png("../data/small_city.png", true);
        clog << "Loaded png image of size " << img1->w()
             << "x" << img1->h() << " with " << img1->n_ch() << endl;
        //creating kernel matrix
        vector<vector<double>> kernel1 = {{-1,-1,-1},{-1,8,-1},{-1,-1,-1}};
        //filterin the image
        Image *img11 = img1->filter2d(3,kernel1);
        //deletin readed image for memory purposes
        delete img1;
        //deletin kernel for memory purposes
        kernel1.clear();
        //saving filtered image
        img11->save_as_png("/tmp/edge_detection.png");
        clog << "Saved filtered image to /tmp/edge_detection.png" << endl; 
        //deletin filtered image . below code block until exercise two has same design.
        delete img11;
        //#################################################################//
        //Gaussian blur 5x5 kernel
        Image *img2 = Image::from_png("../data/small_watch.png", true);
        clog << "Loaded png image of size " << img2->w()
             << "x" << img2->h() << " with " << img2->n_ch() << endl;
        vector<vector<double>> kernel2 = {{0.00390625,0.015625,0.0234375,0.015625,0.00390625}
                                        ,{0.015625,0.0625,0.09375,0.0625,0.015625}
                                        ,{0.0234375,0.09375,0.140625,0.09375,0.0234375}
                                        ,{0.015625,0.0625,0.09375,0.0625,0.015625}
                                        ,{0.00390625,0.015625,0.0234375,0.015625,0.00390625}};
        Image *img21 = img2->filter2d(5,kernel2);
        delete img2;
        kernel2.clear();
        img21->save_as_png("/tmp/gaussian_blur.png");
        clog << "Saved filtered image to /tmp/gaussian_blur.png" << endl; 
        delete img21;
        //#################################################################//
        //Sharpen kernel
        Image *img3 = Image::from_png("../data/dog.png", true);
        clog << "Loaded png image of size " << img3->w()
             << "x" << img3->h() << " with " << img3->n_ch() << endl;
        vector<vector<double>> kernel3 = {{0,-1,0},{-1,5,-1},{0,-1,0}};
        Image *img31 = img3->filter2d(3,kernel3);
        delete img3;
        kernel3.clear();
        img31->save_as_png("/tmp/sharpen.png");
        clog << "Saved filtered image to /tmp/sharpen.png" << endl; 
        delete img31;
        //#################################################################//
}//end exercise 1

void exercise2(){
        //#################################################################//
        clog << "***** Taking x and y Derivate of 2 Image and Saving Them as txt  *****" << endl; 
        clog << "\n" << endl;
        //loading image     
        Image *img1 = Image::from_png("../data/small_city.png", true);
        clog << "Loaded png image of size " << img1->w()
             << "x" << img1->h() << " with " << img1->n_ch() << endl;
        clog << "\n" << endl;
        //x derivative datas of readed image
        short *x_der = img1->deriv_x();
        //saving as txt of the derivative outputs
        FILE *file;// opens file in write mode
		file = fopen("/tmp/xder_one.txt","w");
        for(int index = 0;index < img1->h()*img1->w(); index++){
		fprintf(file,"%d ",x_der[index]);
                if((index+1)%img1->w()==0)
                        fprintf(file,"\n");
	}
        fclose(file);
        clog << "Saved x derivated image's datas to /tmp/xder_one.txt" << endl; 
        //memory purposes
        delete [] x_der;
        // below code segment has same algorithm until exercise three
        //#################################################################//
        short *y_der = img1->deriv_y();
		file = fopen("/tmp/yder_one.txt","w");
        for(int index = 0;index < img1->h()*img1->w(); index++){
		fprintf(file,"%d ",y_der[index]);
                if((index+1)%img1->w()==0)
                        fprintf(file,"\n");
	}
        fclose(file);
        clog << "Saved y derivated image's datas to /tmp/yder_one.txt" << endl; 
        delete [] y_der;
        delete img1;
        //#################################################################//
        //second image
        clog << "\n" << endl;
        Image *img2 = Image::from_png("../data/dog.png", true);
        clog << "Loaded png image of size " << img2->w()
             << "x" << img2->h() << " with " << img2->n_ch() << endl;
        clog << "\n" << endl;
        short *x_der2 = img2->deriv_x();
		file = fopen("/tmp/xder_two.txt","w");
        for(int index = 0;index < img2->h()*img2->w(); index++){
                fprintf(file,"%d ",x_der2[index]);
                if((index+1)%img2->w()==0)
                        fprintf(file,"\n");
        }
        fclose(file);
        clog << "Saved x derivated image's datas to /tmp/xder_two.txt" << endl; 
        delete [] x_der2;
        //#################################################################//
        short *y_der2 = img2->deriv_y();
		file = fopen("/tmp/yder_two.txt","w");
        for(int index = 0;index < img2->h()*img2->w(); index++){
                fprintf(file,"%d ",y_der2[index]);
                if((index+1)%img2->w()==0)
                        fprintf(file,"\n");

        }
        fclose(file);
        clog << "Saved y derivated image's data to /tmp/yder_two.txt" << endl; 
        delete [] y_der2;
        delete img2;
        //#################################################################//
}//end exercise 2

void exercise3(){
        //#################################################################//
        clog << "***** Calculating Affine with 1 Image and 4 Different Affine Matrices with 2 Sampling Methods  *****" << endl;      
        //reading image
        Image *img = Image::from_png("../data/dog.png", true);
        clog << "Loaded png image of size " << img->w()
             << "x" << img->h() << " with " << img->n_ch() << endl;
        //creating Ir image
        Image *Ir = img->new_gray(img->w()*2,img->h()*2);
        //affine matrix and shift vector
        double A[4] = {-1,0,0,-1};//reflection
        double t[2] = {0,0};
        //neigbour sampling
        img->warp_affine(Ir,A,t);
        Ir->save_as_png("/tmp/affine1_near.png");
        clog << "Saved affine matrix 1 via nearest neigbour sampling image to /tmp/affine1_near.png" << endl;
        //bilinear sampling
        img->warp_affine(Ir,A,t,true); 
        Ir->save_as_png("/tmp/affine1_bil.png");
        clog << "Saved affine matrix 1 via  bilinear sampling image to /tmp/affine1_bil.png" << endl;
        //same algorithm until end
        //#################################################################//
        double A1[4] = {1,1,0,1};//shear 
        img->warp_affine(Ir,A1,t);
        Ir->save_as_png("/tmp/affine2_near.png");
        clog << "Saved affine matrix 2 via nearest neigbour sampling image to /tmp/affine2_near.png" << endl;
        img->warp_affine(Ir,A1,t,true); 
        Ir->save_as_png("/tmp/affine2_bil.png");
        clog << "Saved affine matrix 2 via  bilinear sampling image to /tmp/affine2_bil.png" << endl;
        //#################################################################//
        double A2[4] = {0.5,0,0,0.5}; //2 x scaling
        img->warp_affine(Ir,A2,t);
        Ir->save_as_png("/tmp/affine3_near.png");
        clog << "Saved affine matrix 3 via nearest neigbour sampling image to /tmp/affine3_near.png" << endl;
        img->warp_affine(Ir,A2,t,true); 
        Ir->save_as_png("/tmp/affine3_bil.png");
        clog << "Saved affine matrix 3 via  bilinear sampling image to /tmp/affine3_bil.png" << endl;
        //#################################################################//
        double A3[4] = {1,0,0,1};//identity affine matrix and shifting vector
        double t1[2] = {100,-100};
        img->warp_affine(Ir,A3,t1);
        Ir->save_as_png("/tmp/affine4_near.png");
        clog << "Saved affine matrix 4 via nearest neigbour sampling image to /tmp/affine4_near.png" << endl;
        img->warp_affine(Ir,A3,t1,true); 
        Ir->save_as_png("/tmp/affine4_bil.png");
        clog << "Saved affine matrix 4 via  bilinear sampling image to /tmp/affine4_bil.png" << endl;
        delete img;
        delete Ir;
        //#################################################################//
}//end exercise 3
