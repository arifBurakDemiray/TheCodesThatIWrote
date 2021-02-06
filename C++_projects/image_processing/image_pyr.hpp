//Arif Burak Demiray 


#ifndef IMAGE_PYR_HPP //include guard
#define IMAGE_PYR_HPP

#include "image.hpp"

namespace ceng391{

class ImagePyr {
    public:
            ImagePyr(Image *base_img, int n_levels, float sigma = 0.5);//I saw the default value can be 1/256 that is 0.00390625 because pixel values in range of 0-255
            ~ImagePyr();  //destructor for the pyramid                //However in other place, 1.0. I tried both of them but I think 0.5 more appropriate
            int nlvl() const { return m_n_levels; } //# of level returner
            float sig() const { return m_sigma; } //sigma
            Image *level(int level) { return &m_levels[level]; } //specified^th level image
            const Image *level(int level) const { return &m_levels[level]; }// const version
            void downsample(int level); //downsamlpe the image
            void create_pyramids(); //creates all levels
            void save_pyramids(); //saves all levels
            void pyramid();//this is for visuality

    private:
            int m_n_levels;
            float m_sigma;
            Image *m_base_img;
            Image *m_levels;
    };

#endif /* IMAGE_PYR_HPP */




















}