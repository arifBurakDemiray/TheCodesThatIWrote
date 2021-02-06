#Arif Burak Demiray 

import cv2
import numpy as np

def write_descriptor(descriptors,ith):
    """A simple function to write descriptors of an image
       
       Parameters
       ----------
       descriptors : A descriptor list which is computed by one of the compute algorithms
       ith : A number or character that is represents the file name of the output txt
    """
    filename = "../data/sift_"+str(ith)+".txt"
    f = open(filename, "a")
    num = 0
    for d in descriptors:
        line = str(num)+"-->"+str(d)+"\n"  #representing the descriptors for visuality line by line
        f.write(line)
        num+=1
    f.close()

#This function draws the keypoints on image and saves it
def draw_keypoint_image(img,keypoints,ith):
    """A simple function to draw detected keypoints on an image. It draws circles for visuality 
       and draws an arrow for the angle
       
       Parameters
       ----------
       img : Image that is going to be drew keypoints by function
       keypoints : The keypoints detected by any detection algorithm
       ith : A number or character that is represents the file name of the output image

       Returns
       -------
       output_img : The output image of keypoints draw on the image
    """
    filename = "../data/sift_keypoints_"+str(ith)+".png"
    out_img = img.copy()    #for not changing the contents of the image
    out_img=cv2.drawKeypoints(img,keypoints,out_img,(0,255,0),  #here is the draw paramaters, it is green for now
    flags=cv2.DRAW_MATCHES_FLAGS_DRAW_RICH_KEYPOINTS)   #angle ways and circles
    cv2.imwrite(filename,out_img)   #save image
    return out_img  #return image

#This function draws the tentative correspondences on image and saves it
def draw_tentative_correspondence(img1,img2,keypoints1,keypoints2,matched_keypoints,ith,jth):
    """This function draws matches tested by ratio test on two images that is matched
       Also, saves it and returns it

       Parameters
       ----------
       img1 : The first image of the matched images
       img2 : The second image of the matched images
       keypoints1 : The keypoints of the first image
       keypoints2 : The keypoints of the second image
       matched_keypoints : The matched keypoints Ids of the two image
       ith : A number or character that is represents the file name of the output image
       jth : A number or character that is represents the file name of the output image

       Returns
       -------
       out_img : Image that is drew matched keypoints and lines between them
    """
    filename =  "../data/tentative_correspondences_"+str(ith)+"-"+str(jth)+".png"
    out_img = cv2.drawMatches(img1,keypoints1,img2,keypoints2,matched_keypoints,None, #none is output name of the image, bu I do not need it
    matchColor=(0,255,0),singlePointColor=None,flags=2) #not draws single keypoints because flag is 2 and green color. And singlepointcolor none
    cv2.imwrite(filename,out_img) #single point none because I do not want to see not matched keypoints
    return out_img

def write_tentative_correspondence(dmatches,ith,jth):
    """This function writes tentative correspondences of the two image to a txt file

       Parameters
       ----------
       dmatches : Matched keypoints Ids of the two image a.k.a. DMatch Object List
       ith : A number or character that is represents the file name of the output txt file
       jth : A number or character that is represents the file name of the output txt file
    """
    filename = "../data/tentative_correspondences_"+str(ith)+"-"+str(jth)+".txt"
    f = open(filename, "w")
    for dm in dmatches: #Here it writes attributes of the DMatches to read them again
        line = str(dm.distance)+","+str(dm.imgIdx)+","+str(dm.queryIdx)+","+str(dm.trainIdx)+"\n"
        f.write(line)
    f.close()

def write_keypoints(keypoints,ith):
    """Writes keypoints of an image to a txt file
    
       Parameters
       ----------
       keypoints : Keypoints of an image that is detected by any detection algorithm
       ith : A number or character that is represents the file name of the output txt file
    """
    filename = "../data/sift_"+str(ith)+".txt"
    f = open(filename, "w")
    for attribute in keypoints:#Here it writes attributes of the KeyPoint to read them again
        line = str(attribute.angle)+","+str(attribute.class_id)+","+str(attribute.octave)+","+str(attribute.pt[0])+","+str(attribute.pt[1])+","+ str(attribute.size)+","+str(attribute.response)+","+str(attribute.size)+"\n"
        f.write(line)
    f.write("Descriptors\n")
    f.close()

def main(): #main function
    sift = cv2.SIFT_create() #sift algorithm
    filename1 = '../data/goldengate/goldengate-00.png' #first image
    img1 = cv2.imread(filename1,cv2.IMREAD_GRAYSCALE) #read as grayscale
    kp1, des1 = sift.detectAndCompute(img1,None) #we need no mask for this so second arg is None
    write_keypoints(kp1,0) #writes keypoints to txt
    write_descriptor(des1,0) #writes descriptors to txt
    draw_keypoint_image(img1,kp1,0) #draws keypoints on the image
    for i in range(5): #We read one, left 5 
        filename2 = '../data/goldengate/goldengate-0'+str(i+1)+'.png' #second image
        img2 = cv2.imread(filename2,cv2.IMREAD_GRAYSCALE)
        kp2, des2 = sift.detectAndCompute(img2,None)
        write_keypoints(kp2,i+1)
        write_descriptor(des2,i+1)
        draw_keypoint_image(img2,kp2,i+1)
        bf = cv2.BFMatcher() #creates brute force matchers
        matches = bf.knnMatch(des1,des2,k=2) #finds 2 best matches for their descriptors
        tentative = []                   #2 best match enough for ratio test
        for first,second in matches: #here is the ratio test, it makes it 0.5, Why not 0.8 or 0.1, 0.8 too high, 
        #because it can show false keypoints, 0.1 is good actualy but 
        #I decided two show that inliers fix this false ones, also low ratio can remove true keypoints 
            if first.distance < 0.5*second.distance: #if first found best match's distance less than second found best match's half distance
                tentative.append(first) #then append it
        write_tentative_correspondence(tentative,i,i+1) #write matched ones
        draw_tentative_correspondence(img1,img2,kp1,kp2,tentative,i,i+1) #draw them
        img1=img2 #make img1 <- img2
        des1=des2 #same logic
        kp1=kp2 #because not read same images twice

#call main
main()