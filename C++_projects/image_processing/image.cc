// Arif Burak Demiray 

#include "image.hpp" 

#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <iostream>
#include <fstream>
#include <cmath>
#include <vector>
#include <png.h>
#include <sstream>
#include <cmath>

#ifndef png_jmpbuf
#  define png_jmpbuf(png_ptr) ((png_ptr)->jmpbuf)
#endif

#ifndef png_infopp_NULL
#  define png_infopp_NULL (png_infopp)NULL
#endif

#ifndef int_p_NULL
# define int_p_NULL (int*)NULL
#endif

using std::cerr;
using std::clog;
using std::endl;
using std::exit;
using std::ios;
using std::ofstream;
using std::string;
using std::fopen;
using std::fclose;
using std::memcpy;
using std::cos;
using std::sin;
using std::round;
using std::vector;
using std::floor;
using std::ifstream;
using std::stringstream;
using std::int8_t;
using std::memset;

namespace ceng391 {

Image::Image(int width, int height, int n_channels, int step)
        : m_width(width), m_height(height), m_n_channels(n_channels)
{
        if (step < width * n_channels)
                step = width * n_channels;
        m_step = step;
        m_data = new uchar[m_height * m_step];
}

Image::~Image()
{
        // clog << "Deleting image" << endl;
        delete [] m_data;
}

Image *Image::new_gray(int width, int height)
{
        clog << "Created grayscale image with wanted heigt and width.\n" << endl;
        return new Image(width, height, 1);
}

//rgb image constructor
Image *Image::new_rgb(int width, int height)
{
        clog << "Created RGB image with wanted heigt and width.\n" << endl;
        return new Image(width, height, 3);
}

//rgba image constructor
Image *Image::new_rgba(int width, int height)
{
        clog << "Created RGBA image with wanted heigt and width.\n" << endl;
        return new Image(width, height, 4);
} 

//modified set rectangle area for rgb and rgba images.
void Image::set_rect(int tlx, int tly, int width, int height, uchar value)
{
        if (tlx < 0) {
                width += tlx;
                tlx = 0;
        }
        if (tly < 0) {
                height += tly;
                tly = 0;
        }
        for (int y = tly; y < tly + height; ++y) {

                if (y >= m_height)
                        break;

                uchar *row = m_data + y * m_step;

                for (int x = tlx; x < tlx + width; ++x) {
                        if (x >= m_width)
                                break;
                        //for rgba support
                        if(this->n_ch()==4){
                                row[4*x]=value;
                                row[4*x+1]=value;
                                row[4*x+2]=value;
                                row[4*x+3]=value;
                        }
                        //for rgb support
                        else if(this->n_ch()==3){
                                row[3*x]=value;
                                row[3*x+1]=value;
                                row[3*x+2]=value;
                        }
                        //for grayscale images
                        else if(this->n_ch()==1){ 
                                row[x]=value;

                        }   
                        //not supporting 2 channel images
                        else{
                                cerr << "The image that using Image::set_rect function has unsupported channel number.\n" << endl;
                                exit(EXIT_FAILURE);
                        }
                }//end second for
        }//end first for
        clog << "Succesfully created rectangle area for " << this->n_ch() <<" channel image.\n"<<endl;
}//end function body

void Image::save_as_pnm(const std::string &filename)
{       
        //controlling channel supporting
        if (m_n_channels > 1) {
                if(this->n_ch()==2){
                        cerr << "The image that using Image::save_as_pnm function has unsupported channel number.\n" << endl;
                        exit(EXIT_FAILURE);
                }

                const string magic_head = "P6";
                ofstream fout;
                string extended_name = filename + ".ppm";
                fout.open(extended_name.c_str(), ios::out | ios::binary);
                fout << magic_head << "\n";
                fout << m_width << " " << m_height << " 255\n";

                for (int y = 0; y < m_height; y++) {
                        const uchar *row_data = this->data(y);
                        uchar *temp_row = new uchar[m_width*3];// for eliminating alpha channel
                        if(m_n_channels==4){
                                int index = 0;//eliminating purpose
                                for(int x=0; x < m_step; x++){
                                        if((x+1)%4!=0){//every 4th element eliminating
                                                temp_row[index] = row_data[x];
                                                index++;
                                        }
                                }
                                fout.write(reinterpret_cast<const char*>(temp_row), m_width*sizeof(uchar)*3);// width*3 = mstep for rgb 
                                delete [] temp_row;//for free the memory                                                          //we write image row by row
                        }
                        //if image is rgb and there is no need for above operations
                        else if(m_n_channels==3){
                                fout.write(reinterpret_cast<const char*>(row_data), m_width*sizeof(uchar)*3);
                                delete [] temp_row;//for free the memory  
                        }
                        else{
                                delete [] temp_row;//for free the memory  
                                cerr << "The image's that using Image::save_as_pnm function channel number is not supported.\n" << endl;
                                exit(EXIT_FAILURE);
                        }
                }//end first for
                fout.close();   
                clog << "Successfully saved "<< this->n_ch() <<" channel image.\n" << endl;  
        }
        else {
                if(this->n_ch()==1){//grayscale support
                        const string magic_head = "P5";
                        ofstream fout;
                        string extended_name = filename + ".pgm";
                        fout.open(extended_name.c_str(), ios::out | ios::binary);
                        fout << magic_head << "\n";
                        fout << m_width << " " << m_height << " 255\n";

                        for (int y = 0; y < m_height; y++) {
                                const uchar *row_data = this->data(y);
                                fout.write(reinterpret_cast<const char*>(row_data), m_width*sizeof(uchar));
                        }
                        fout.close();
                        clog << "Successfully saved "<< this->n_ch() <<" channel image.\n" << endl;
                }
                else{
                        cerr << "The image's that using Image::save_as_pnm function channel number can not be less than 1.\n" << endl;
                        exit(EXIT_FAILURE);
                }
        }
}//end function body

//to grayscale only RGBA images can be converted
void Image::to_grayscale(){
//red*0.3 + green*0.59 + blue*0.11
        int ch = this->n_ch();
        if(ch!=4){
                cerr << "The image that sent to the Image::to_grayscale function is not a RGBA image.\n"<< endl;
                exit(EXIT_FAILURE);
        }
        else{
                int h = this->h();
                int w = this->w();
                uchar *new_data = new uchar[h*w];// new data array
                for (int y = 0; y < h; y++){
                        uchar *temp_row = new_data + y*w;//getting row by row
                        const uchar *row_data = this -> data(y);
                        for(int x = 0; x < w; x++){//this code eliminates alpha channel
                                uchar value =  row_data[4*x]*0.3 + row_data[4*x + 1]*0.59 + row_data[4*x + 2]*0.11;
                                if(value > 255)//over and under flows check
                                        value = 255;
                                if(value < 0)
                                        value = 0;
                                temp_row[x] = value;
                        }   
                }
                //updating the existing image to grayscale
                this->m_n_channels = 1;
                delete [] this->m_data;//deleting old data array
                this->m_data = new_data;
                this->m_step = w;
                clog << "Successfully converted RGBA image to grayscale image.\n" << endl;
        }
}

//this code only converts grayscale to RGBA
void Image::to_rgba(){
        if(this->n_ch()!=1){
                cerr << "The image that sent to the Image::to_rgba function is not a garyscale image.\n"<< endl;
                exit(EXIT_FAILURE);
        }
        else{
                int h = this->h();
                int w = this->w();
                uchar *new_data = new uchar[h*w*4];//new data array for rgba image
                for (int y=0; y<h; y++){
                        uchar *temp_row = new_data + y*w*4;//getting row by row
                        const uchar *row_data = this->data(y);
                        for(int x=0; x<w; ++x){//setting all channels same value
                                temp_row[4*x]=row_data[x];
                                temp_row[4*x + 1]=row_data[x];
                                temp_row[4*x + 2]=row_data[x];
                                temp_row[4*x + 3]=row_data[x];
                        }
                }
                //updating existing image
                this->m_n_channels=4;
                this->m_step=w*4;
                delete [] this->m_data;//free the memory
                this->m_data = new_data;
                clog << "Successfully converted grayscale image to RGBA image.\n" << endl;
        }
}

//this function can only read pnm files.
Image* Image::read_pnm(const std::string &filename)
{
        Image *img;//returning img
        stringstream ss;//for reading pixel by pixel
        string line = "";
        ifstream ifst(filename.c_str(),ios::in | ios::binary);
        getline(ifst,line);
        string magicHead = line; 
        int h = 0;
        int w = 0;
        int value;
        ss << ifst.rdbuf();
        ss >> w >> h;
        ss >> value;
        if(magicHead.compare("P6")==0){//look head and creates the image
                img = new_rgba(w,h);}
        else if(magicHead.compare("P5")==0){
                img = new_gray(w,h);}
        else{
                cerr << "The file that is sent to the Image::read_pnm function has unsupported magic head.\n";
                cerr << "The file that is sent to the Image::read_pnm function can have unsupported extension.\n";
                cerr << "Supported extensions:PNM,PGM,PPM.\n";
                cerr << "Supported magic heads:P6,P5.\n"<<endl;
                exit(EXIT_FAILURE);
        }
        uchar* data = img->data();//gets the image data array.
        char pix;//reads pixel by pixel
        for(int y=0;y < h*w*img->n_ch(); y++){
                if(img->n_ch()==4 & (y+1)%4==0){
                        data[y]=(uchar)255;
                }
                else{
                        ss >> pix;
                        data[y]=(uchar)pix;//puts to the list
                }
        }
        ifst.close();
        clog << "Successfuly readed "<< img->n_ch() << " channel image.\n"  << endl;
        return img;
}//end function

Image *Image::from_png(const std::string &filename, bool load_as_grayscale)
{
        ...
}

void Image::reallocate(int width, int height, int n_channels)
{
        ...
}


void Image::set_rect(int tlx, int tly, int width, int height,
                     uchar red, uchar green, uchar blue, uchar alpha)
{
        ...
}

void Image::axpc(float multiplier, float offset)
{
        ...
}

static void copy_to_buffer_1d(int n, uchar* buffer, const uchar *src, int padding)
{
        ...
}

static void copy_column_to_buffer_1d(int n, uchar* buffer, const uchar *src,
                                     int step, int padding)
{
        ...
}

static void box_filter_buffer_1d(int n, uchar* buffer, int padding)
{
        ...
}

void Image::box_filter_x(int filter_size)
{
        ...
}

void Image::box_filter_y(int filter_size)
{
        ...
}

void Image::box_filter(int filter_size)
{
        ...
}

bool Image::save_as_png(const std::string &filename)
{
        ...
}


bool Image::load_png(const std::string &filename, bool load_as_grayscale)
{
        ...
}

//      [ cos(angle) -sin(angle) ]
//  R = [                        ]
//      [ sin(angle)  cos(angle) ]
//                 [ cos(angle)   sin(angle) ]
//  R^{-1} = R^T = [                         ]
//                 [ -sin(angle)  cos(angle) ]
void Image::rotated(Image *Ir, double angle, bool rotate_by_center)
{
        ...
}

//Here is the filter2d function. It can process all of matrix filters of size n*n
Image *Image::filter2d(int n,vector<vector<double>> kernel)//filter matrix
{       //only grayscale images
        if (this->m_n_channels != 1) {
                cerr << "[ERROR][CENG391::Image] Currently only grayscale images can be filtered!\n";
                exit(EXIT_FAILURE);
        }
        //n must be odd
        if((n-1)%2!=0){
                n = n - 1;
        }
        //kernel size must be n*n
        if(kernel.size()!=n)
        {
                cerr << "[ERROR][CENG391::Image] Filter matrix is not size of n x n!\n(The filter matrix must be a square matrix also it's size must be odd!)\n";
                exit(EXIT_FAILURE);
        }
        //kernel size must be n*n
        for(int counter = 0; counter < n; counter++ )
        {
                if(kernel[counter].size()!=n){
                        cerr << "[ERROR][CENG391::Image] Filter matrix is not size of n x n!\n(The filter matrix must be a square matrix also it's size must be odd!)\n";
                        exit(EXIT_FAILURE);
                }
        }
        //Going to return image with old one's size decreased because we are not controlling borders
        Image *img = new_gray(this->w()-n+1,this->h()-n+1);
        for (int y = (n/2);y < this->m_height - (n/2);y++){//why -n/2 because again we are not controlling borders 
                for (int x = (n/2);x < this->m_width-(n/2);x++){// +n/2 same reason as above 
                        double total = 0;
                        for (int k = 0; k < n;k++){//these two for loop for travelling in kernel matrix.
                                for (int l = 0; l < n;l++){//here multiplying the pixel values with kernel and add to the total
                                        total += this->m_data[(y-(n/2)+k)*this->m_width + (x-(n/2)+l)]*kernel[k][l];
                                }
                        }
                        //check for overflow and underflow
                        if(total > 255)
                                total = 255;
                        else if(total < 0)
                                total = 0;
                        //finally put total to appropriate location in returning image.
                        img->m_data[((y-(n/2))*img->m_width) + x-(n/2)] = total;
                }
        }
        //saying we created the returned image.
        clog << "The image filtered by the filter matrix you gave. New image returned. New size(w x h): "<<img->w()<<"x"<<img->h()<<endl;
        return img;
}

//this function creates a buffer for derivate x and y funciton. Adds 0 to borders.
static void make_buffer0(short* buffer,int width,int height,uchar* data)//taking necessary items
{       //why + 2 because we have a border like 0  width 0
        //  0                   00000000000000
        //height                0pixel_values0
        //  0                   00000000000000
        for(int x=0;x<(width+2)*(height+2);x++){
                buffer[x]=0;//initialized all values to 0
        }
        //puts the pixel values to inside borders
        for(int y=0;y<height;y++){
                for(int x=0;x<width;x++){//why plus one because I filled the borders with 0
                        buffer[(y+1)*width+x+1] = data[y*width+x];
                }
        }
}

//this function takes x derivative of an image. derivate x, derivate y and filter2d function have almost same code design
short *Image::deriv_x()
{       //only grayscale images 
        if (m_n_channels != 1) {
                cerr << "[ERROR][CENG391::Image] Currently only take x derivative of grayscale images!\n";
                exit(EXIT_FAILURE);
        }
        //I created the buffer array and send it to the border function
        short *buffer = new short[(this->m_width+2)*(this->m_height+2)];
        make_buffer0(buffer,this->m_width,this->m_height,this->m_data);
        //here is the x derivative kernel
        short x_matrix[3][3] = {{-1,0,1},{-2,0,2},{-1,0,1}};
        //this list is going to return.
        short *x_buffer = new short[this->m_height*this->m_width];
        for (int y = 0;y < this-> m_height;y++){
                for (int x = 0;x < this->m_width;x++){ 
                        short total = 0; //total value
                        for (int k = 0; k < 3;k++){//travelling in kernel
                                for (int l = 0; l < 3;l++){//because we have borders, we can travel safely
                                        total += buffer[(y+k)*(this->m_width) + (x+l)] * x_matrix[k][l];
                                }                            
                        }
                        //I am not doing clamping because in derivative we have lots of signed values
                        //adding total to the returning array
                        x_buffer[y*(this->m_width) + x] = total;
                }
        }
        //handling the allocations
        delete[] buffer;
        //returned the array
        return x_buffer;
} 

//this function takes y derivative of an image. Same logic as above.
short *Image::deriv_y()
{
        //only grayscale images
       if (m_n_channels != 1) {
                cerr << "[ERROR][CENG391::Image] Currently only take y derivative of grayscale images!\n";
                exit(EXIT_FAILURE);
        }
        //same logic as above derivate x function
        short *buffer = new short[(this->m_width+2)*(this->m_height+2)];
        make_buffer0(buffer,this->m_width,this->m_height,this->m_data);
        //y derivative kernel.
        short y_matrix[3][3] = {{-1,-2,-1},{0,0,0},{1,2,1}};
        short *y_buffer = new short[this->m_height*this->m_width];
        for (int y = 0; y < this->m_height ;y++){
                for (int x = 0; x < this->m_width; x++){
                        short total = 0;
                        for (int k = 0; k < 3;k++){
                                for (int l = 0; l < 3;l++){
                                        total += buffer[(y+k)*(this->m_width) + (x+l)] * y_matrix[k][l];
                                }
                        }
                        //putting new value to y buffer
                        y_buffer[y*(this->m_width) + x] = total;
                }
        }
        //handling allocations
        delete [] buffer;
        return y_buffer;
} 

//here affine function. bilinear option default false
//it tooks affine array t array which is shift matrix and input image.
void Image::warp_affine(Image *Ir, double A[4],double t[2],bool bilinear_sampling)
{
        //only grayscale images
        if (m_n_channels != 1) {
                cerr << "[ERROR][CENG391::Image] Currently only grayscale images can be affined!\n";
                exit(EXIT_FAILURE);
        }

        //also input image must be a grayscale
        if (!Ir || Ir->n_ch() != 1) {
                cerr << "[ERROR][CENG391::Image] Affine requires an allocated grayscale image as its first parameter!\n";
                exit(EXIT_FAILURE);
        }
        //creating new data for input image
        uchar *new_data = new uchar[Ir->w()*Ir->h()];
        for(int y=0;y<Ir->m_height;y++){
                //datas of Ir
                uchar *row = new_data + y*Ir->w();
                for(int x=0;x<Ir->m_width;x++){
                        int xr = x;
                        int yr = y;
                        //default making affine operations by center like rotation function
                        xr -= Ir->w()/2;
                        yr -= Ir->h()/2;
                        //affine operations  
                        double x_nd = xr*A[0] + yr*A[2] + t[0];
                        double y_nd = xr*A[1] + yr*A[3] + t[1];
                        //default making affine operations by center like rotation function                      
                        x_nd += this->w()/2;
                        y_nd += this->h()/2;
                        // do nearest neighbor
                        int xi = round(x_nd);
                        int yi = round(y_nd); 
                        uchar value = 0;
                        //checking if it is outside borders or not
                        if (xi >= 0 && yi >= 0 && xi < this->m_width && yi < this->m_height){
                                //bilinear sampling
                                if(bilinear_sampling){
                                        //for calculating alphas and betas 
                                        xi = floor(x_nd);
                                        yi = floor(y_nd);
                                        //alphas and betas
                                        double alpha = x_nd - xi;
                                        double beta = y_nd - yi;
                                        //here is the bilinear sampling by its formula given by course pdf
                                        value = (1-alpha)*(1-beta)*this->m_data[yi * this->m_step + xi]+
                                                alpha*(1-beta)*this->m_data[yi * this->m_step + xi + 1]+
                                                (1-alpha)*beta*this->m_data[(yi + 1) * this->m_step + xi]+
                                                alpha*beta*this->m_data[(yi + 1) * this->m_step + xi + 1];
                                }
                                else{//makes value by nearest neigbor sampling
                                        value = this->m_data[yi * this->m_step + xi];
                                        }
                        }
                        //adding value to input image's data
                        row[x] = value;
                }
        }
        //handle allocations
        delete [] Ir->m_data;
        Ir->m_data = new_data;
}

Image *Image::from_deriv(int width, int height, const short *deriv)
{
        ...
}

uchar Image::sample_bilinear(double x, double y, uchar default_value)
{
        ...
}


static void filter_buffer_1d(int n, float* buffer,
                             int padding, const float *kernel)
{
        ...
}


static float *gaussian_kernel(float sigma, int &padding)
{
        ...
}

// g(x | mu, sigma) = 1 / (sqrt(2*pi*sigma^2)) * exp(-0.5 * (x - mu)^2 / sigma^2)
void Image::smooth_x(float sigma)
{
        ...
}

void Image::smooth_y(float sigma)
{
        ..
}

void Image::smooth(float sigma)
{
        ..
}

// (x, y) is the location of the pixel at the top-left corner of the region to
// be convolved
static int convolve_2d(int x, int y, const uchar *data, int step,
                       int n, const float *K)
{
        ...
}
}


