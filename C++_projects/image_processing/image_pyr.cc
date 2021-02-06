//Arif Burak Demiray 

#include "image_pyr.hpp"

#include <cstdlib>
#include <iostream>
#include <cmath>

using std::cerr;
using std::endl;
using std::clog;
using std::sqrt;
using ceng391::Image;
using std::string;
using std::to_string;

namespace ceng391{

ImagePyr::ImagePyr(Image *base_img, int n_levels, float sigma)
        : m_n_levels(n_levels+1),m_base_img(base_img) ,m_sigma(sigma)
{       //why +1 because I think about the level 0
        if(base_img->n_ch()!=1){//we can only process grayscale images
                base_img->to_grayscale();//converting it to grayscale
        }
        const uchar *data = base_img->data();   //here creating the copy of the input image
        m_base_img = Image::new_gray(base_img->w(),base_img->h()); //taking new space for it
        uchar *cdata = m_base_img->data();
        for(int i=0;i < m_base_img->h()*m_base_img->w();i++){
                cdata[i] = data[i];
        }//end of copy
        if (m_n_levels < 1){//if user entered below 1 new levels are 1
                m_n_levels = 1;}
        m_levels = (Image*)malloc(sizeof(Image)*m_n_levels);//allocating memory for images
        m_levels[0] = *m_base_img; //putting its pointer to list

}

ImagePyr::~ImagePyr()
{       //deallocates all memory taken by constructor
        delete m_base_img; //deletes input image's copy
        for(int i=1;i<m_n_levels;i++){ //deallocates all images one by one
                m_levels[i].~Image();
        }
        free(m_levels);//frees the blocks of memory
} 

static Image *copy(Image *src)
{       //copies an image and returns its copy
        Image *dst = Image::new_gray(src->w(),src->h());//image is going to return 
        uchar *data_src = src->data(); //data of source
        uchar *data_dst = dst->data(); //data of destination
        for(int i=0;i < src->w()*src->h();i++)
                data_dst[i] = data_src[i];
        return dst; 
}//end of image copy

void ImagePyr::downsample(int level){//takes desired level and creates it but there should be a image for new level
        Image *before = this->level(level-1); //last level image
        float sigma = this->sig(); //sigma
        int b_width = before->w(); //before
        int b_height = before->h();
        if(b_width < 1 || b_height < 1){
                cerr << "[ERROR][CENG391::ImagePyr] There is no image at the before level of this level." << endl;
                return;
        }
        int width = b_width/2; //checking for we can downsample or not
        int height = b_height/2;

        if(height<1 || width<1){
                cerr << "[ERROR][CENG391::ImagePyr] Program can not downsample anymore. The size of image is below 1x1." << endl;
                clog << "New number of levels is " << level-1<< endl; //if not the last level is before level
                m_n_levels = level; //Why new level is this level beacuse we have source image at 0 index so it should be mnlevels + 1
                return;
        }
        // If we smooth before level directyl its datas change permanently
        Image *temp_buffer = copy(before); //so, I am copy it to not change
        temp_buffer->smooth(sigma); //smooth the copied image

        Image *temp =new (this->level(level)) Image(width,height,1); //placement new for new level to desired address

        uchar *data_temp = temp_buffer->data(); //copied data
        uchar *data_b = temp->data();   //this level's data
        for(int y=0;y < height ; y++){
                int g_y_index = 2*y; //getting data from even rows and columns +1 not changes it
                if(g_y_index < b_height){ // chechking we are above limits of source level's boundaries
                        for(int x=0; x < width; x++){
                                int g_x_index = 2*x;
                                if(g_x_index < b_width)
                                        data_b[y*width + x] = data_temp[g_y_index*b_width + g_x_index];
                        }               //at last, new level's datas are assigning from even rows and columns
                }
        }
        clog <<"Level "<< level << " is created with the size of (w x h) "<<width<<"x"<<height<<endl;
        delete temp_buffer; //deleting copied image for memory deallocation
}
//creates all levels with desired # of levels
void ImagePyr::create_pyramids(){
        this->m_sigma *= sqrt(3); //multiplying source sigma0 with sqrt(3) to doube the sigma and prevent aliasing at the lower levels 
        for(int x=1;x<m_n_levels;x++){//why 1 because at 0 we have source image
               downsample(x);
        }
}

void ImagePyr::save_pyramids(){//saves pyramids to desired location
        string filename = "/tmp/pyr_level_"; 
        for(int x=0;x<m_n_levels;x++){
                filename += to_string(x); //converts integer to string
                filename += ".png";
                Image *img = level(x); //takes that image
                img->save_as_png(filename);//and saves it
                clog << "Saved level "<<x<<" to "<<filename<<endl;
                filename = "/tmp/pyr_level_";//makes filename default again
        }
}

void ImagePyr::pyramid(){
        Image *img = this->level(0);//source
        Image *pyramid = Image::new_gray(img->w()*2,img->h());//for pyramid
        clog << "Created a pyramid with the size of (w x h) "<<img->w()*2<<"x"<<img->h()<<endl;
        pyramid->set(0);//for filling the image with zeros to not take error when saving it
        int base_level = 0;//this is for saving them side by side
        int alt_level = img->h(); //this is for saving them to the bottom
        for(int i=1;i<m_n_levels+1;i++){ //why +1 because I am updating image at last also I need alt level information at first so for last image I should turn again
                for(int y=0;y<img->h();y++){            //beacuse when i = N the image processing is (N - 1)th image
                        for(int x=0;x<img->w();x++){//from bottom and side by side
                                pyramid->data()[ (y + alt_level - img->h())*pyramid->w() + x + base_level] = img->data()[y*img->w() + x];
                        }
        }
        base_level += img->w();//side by side property
        img = this->level(i); //updating to next level
        }
        clog << "Saved pyramid to /tmp/pyramid.png"<<endl;//saving pyramid
        pyramid->save_as_png("/tmp/pyramid.png");
        delete pyramid;//and delete it
}

}