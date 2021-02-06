#Arif Burak Demiray 

import cv2
import numpy as np

def read_keypoints(ith):
    """This function reads wrote keypoints
    
       Parameters
       ----------
       ith : A number or character that is represents the file name of the input txt file

       Returns
       -------
       keypoints : read keypoints that is given by detect_and_match
    """
    filename = "../data/sift_"+str(ith)+".txt"
    file = open(filename,"r") #open file in read mode
    keypoints = [] # keypoints
    lines = file.readlines() #read all lines 
    file.close()
    for line in lines:
        if(line=="Descriptors\n"):
            break
        else:
            list = line.split(',')
            kp = cv2.KeyPoint(x=float(list[3]), y=float(list[4]), _size=float(list[6]), _angle=float(list[0]),_response=float(list[5]), _octave=int(list[2]), _class_id=int(list[1]))
            keypoints.append(kp)
    return keypoints

def read_tentative_correspondences(ith,jth):
    """This function reads tentative correspondences created by detect_and_match.py

       Parameters
       ----------
       ith : A number or character that is represents the file name of the input txt file
       jth : A number or character that is represents the file name of the input txt file

       Returns
       -------
       dmatches : read desciptor matches that is given by detect_and_match
    """
    filename = "../data/tentative_correspondences_"+str(ith)+"-"+str(jth)+".txt"
    file = open(filename,"r") #open file in read mode
    lines = file.readlines() #read all lines 
    file.close()
    dmatches = [] #dmatches
    for line in lines:
        list = line.split(',')
        dm = cv2.DMatch(_queryIdx=int(list[2]),_trainIdx=int(list[3]),_imgIdx=int(list[1]),_distance=float(list[0]))
        dmatches.append(dm)
    return dmatches

def read_images(ith,jth):
    """This function reads images that is mentioned in data/goldengate folder

       Parameters
       ----------
       ith : A number or character that is represents the file name of the input png file
       jth : A number or character that is represents the file name of the input png file

       Retuns
       ------
       img1 : The first image that is read by function
       img2 : The second image that is read by function
    """
    filename1 = '../data/goldengate/goldengate-0'+str(ith)+'.png'
    img1 = cv2.imread(filename1,cv2.IMREAD_GRAYSCALE)  #read as grayscale

    filename2 = '../data/goldengate/goldengate-0'+str(jth)+'.png'
    img2 = cv2.imread(filename2,cv2.IMREAD_GRAYSCALE)

    return img1,img2

def write_homography(H,ith,jth):
    """This function writes homography matrix to a txt file

       Parameters
       ----------
       H : The Homography Matrix
       ith : A number or character that is represents the file name of the output txt file
       jth : A number or character that is represents the file name of the output txt file
    """
    filename = "../data/h_"+str(ith)+"-"+str(jth)+".txt"
    f = open(filename, "w")
    f.write(str(H)) #just writes homograpy all
    f.close()

def draw_inliers(ith,jth,img):
    """This functions saves image that has inliers on it

       Parameters
       ----------
       ith : A number or character that is represents the file name of the output png file
       jth : A number or character that is represents the file name of the output png file
       img : The image that has inliers on it
    """
    filename = "../data/inliers_"+str(ith)+"-"+str(jth)+".png"
    cv2.imwrite(filename,img) #save inlier image

def write_inliers(ith,jth,inliers_mask,tentative):
    """This function saves inliers in a txt file

       Parameters
       ----------
       ith : A number or character that is represents the file name of the output txt file
       jth : A number or character that is represents the file name of the output txt file
       inliers_mask : The inlier mask which is given by findHomography
       tentative : The DMatches of the two images
    """
    filename = "../data/inliers_"+str(ith)+"-"+str(jth)+".txt"
    f = open(filename, "w")
    number = 0
    for boolean in inliers_mask:
        if(boolean==1):
            f.write(str(tentative[number].distance)+","+str(tentative[number].imgIdx)+","+str(tentative[number].queryIdx)+","+str(tentative[number].trainIdx)+"\n")
            number+=1
    f.close()


def create_save_homography(ith,jth):
    """This function creates homography, creates inliers and saves them

       Parameters
       ----------
       ith : A number or character that is represents the file name of the input png file
       jth : A number or character that is represents the file name of the input png file

       Returns
       -------
       H : Returns homography matrix which is calculated by cv2.findHomography
    """
    img1,img2 = read_images(ith,jth)
    keypoints1 = read_keypoints(ith)
    keypoints2 = read_keypoints(jth)
    tentative = read_tentative_correspondences(ith,jth)
    MIN_MATCHES = 15  #if minimumum matches below 15 we do not calculate homography
    if len(tentative) > MIN_MATCHES:
        # Convert keypoints to an argument for findHomography
        source_points = np.float32([ keypoints1[dmatch.queryIdx].pt for dmatch in tentative]) #query means img1 means source
        destination_points = np.float32([ keypoints2[dmatch.trainIdx].pt for dmatch in tentative]) #train means img2
        # Establish a homography
        H, M = cv2.findHomography(source_points, destination_points, cv2.RANSAC,5) #5 is reprojection threshold
        inliers_mask = M.ravel().tolist()#This transforms mask to a list from matrix    #it is used in Ransac it is at half of the range for now
        write_inliers(ith,jth,inliers_mask,tentative)                    # not draws single keypoints because flag is 2 and green color. And singlepointcolor none
        img3 = cv2.drawMatches(img1,keypoints1,img2,keypoints2,tentative,None,matchColor = (0,255,0), singlePointColor = None,
                   matchesMask = inliers_mask, # draw only inliers with mask calculated above
                   flags = 2)
        draw_inliers(ith,jth,img3) #saves inliers photo
        write_homography(H,ith,jth) #writes H matrix to the txt
        return H #returns it 
    else:
        print( "Not enough tentative correspondences to match images") #if min match count greater than len of tentatives



def main():
    for i in range(5): #all images
        create_save_homography(i,i+1)

     
main()