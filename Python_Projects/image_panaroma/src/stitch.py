#Arif Burak Demiray 

import cv2
import numpy as np

def warp_images(img1, img2, H):
    """This function warps two given images by Homography matrix and cuts the black regions 
       add new images truely

       Parameters
       ----------
       img1 : The source image that is going to be center
       img2 : The second image that is warped by first image
       H : Homography matrix

       Returns
       -------
       out_img : Output of the warped image
    """
    firstHight = img1.shape[0] 
    firstWidth = img1.shape[1] 
    secondHight = img2.shape[0] 
    secondWidth = img2.shape[1]
    points_1 = np.float32([[0,0], [0, firstHight],[firstWidth, firstHight], [firstWidth, 0]]).reshape(-1, 1, 2) #this creates 1d array 
    perscv_points = np.float32([[0,0], [0,secondHight], [secondWidth,secondHight], [secondWidth,0]]).reshape(-1,1,2) #why differenet because I am going to make transform on it to fit images truely
    points_2 = cv2.perspectiveTransform(perscv_points, H) #Transforms the frame to fit data on it for second image
    all_points = np.concatenate((points_1,points_2)) #after making perspective, add frames
    [minx, miny] = np.int32(all_points.min(axis=0).ravel()) #returns it to 1d array, also finds maximum and minimum of the frame
    [maxx, maxy] = np.int32(all_points.max(axis=0).ravel()) #max values
    H_Trans = np.array([[1, 0, -minx], [0, 1, -miny], [0, 0, 1]]) #for translating H matrix for establish img2 place
    warped = cv2.warpPerspective(img2, H_Trans.dot(H), (maxx-minx, maxy-miny)) #warp image by translated Homograpby matrix
    warped[-miny:firstHight-miny, -minx:firstWidth-minx] = img1 #add image one to warped frame
    return warped #return it


def main():
    filename1 = '../data/goldengate/goldengate-0'+str(2)+'.png'  #center image
    img1 = cv2.imread(filename1,cv2.IMREAD_GRAYSCALE)
    panaroma = img1.copy()  #the tree structure reading image
    for i in range(5): #reads images (((((2 - 1) - 0) - 3) - 4) - 5)
        if(i>=2):
            filename2 = '../data/goldengate/goldengate-0'+str(i+1)+'.png'
        else:
            filename2 = '../data/goldengate/goldengate-0'+str(1-i)+'.png'
        img2 = cv2.imread(filename2,cv2.IMREAD_GRAYSCALE)  #below code same logic as detect_and_match.py
        sift = cv2.SIFT_create()
        kp1,des1 = sift.detectAndCompute(panaroma,None)
        kp2,des2 = sift.detectAndCompute(img2,None)
        for des in des1:
            cv2.normalize(des, des, 1.0, 1.0, cv2.NORM_L2)
        np.sqrt(des1)
        for dess in des2:
            cv2.normalize(dess, dess, 1.0, 1.0, cv2.NORM_L2)
        np.sqrt(des2)
        bf = cv2.BFMatcher()
        matches = bf.knnMatch(des1,des2,k=2)
        tentative = []
        for first,second in matches:
            if first.distance/second.distance < 0.2:
                tentative.append(first)
        MIN_MATCHES = 15
        if len(tentative) > MIN_MATCHES:
            # make points to 1 d array for find homography
            source = np.float32([ kp1[dmatch.queryIdx].pt for dmatch in tentative]).reshape(-1,1,2) #rehape for make it 1d array
            destination = np.float32([ kp2[dmatch.trainIdx].pt for dmatch in tentative]).reshape(-1,1,2)
            H, _ = cv2.findHomography(source, destination, cv2.RANSAC,0.999)
            #H is homography matrix
            warped_image = warp_images(img2, panaroma, H)  #and our warp_image matrix to warp two images
            panaroma = warped_image #make new panaroma warped image
    cv2.imwrite("../data/panaroma.png", panaroma) #write panaroma
main()