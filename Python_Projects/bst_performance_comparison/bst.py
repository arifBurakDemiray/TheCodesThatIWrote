# -*- coding: utf-8 -*-
"""

@author: Arif Burak Demiray
contact burakdemiray09@gmial.com
"""
#In array based BST implemantation if the root is n, the left child 2*n+1 the right child 2*+2 

#for time measurement
from timeit import default_timer as timer
import sys
sys.setrecursionlimit(10**6)#increasing recursion limit

# Binary search tree functions

def root(bst_array,root): #root of the tree
    if(len(bst_array)!=0 and bst_array[0]!=None):
        return -1 #tree can be contructed early
    else:
        #it takes space for its and its children
        bst_array.extend([None]*3)
        bst_array[0]=root
        
#set right for the current parent
def set_right(bst_array,value, parent):
        #parent can not be none
    if(bst_array[parent] == None):
        return -1
    else:
        try: #for preventing array index out of error
            if(bst_array[2*parent+2]==None):#means this parent is appropriate for this child
                bst_array[2*parent+2] = value
            else:#this else means parent has already a child
                return -1
        except:#if it does not enough space for them. Makes spaces for children
                bst_array.extend([None]*(parent + 2))#why parent + 2. For ex: root at the index 6. Its right child must be at 14. It needs 8 space to reach it. index + 2
                bst_array[2*parent+2] = value #adding to parent's child
                return 1
            
#set left for the current parent
def set_left(bst_array,value, parent): #same logic is here as well
    if(bst_array[parent] == None):
        return -1
    else:
        try:
            if(bst_array[2*parent+1]==None):
                bst_array[2*parent+1] = value
            else:
                return -1
        except:
            bst_array.extend([None]*(parent + 2))
            bst_array[2*parent+1] = value
            return 1
        
def constructBST(bst_array,array, parent):
    #I am creating bst recursively
    if(len(array)>1):#Because last coming thing is not an array. It is integer.
        if(array[0]<bst_array[parent]):
            if(set_left(bst_array,array[0],parent)!=-1):#already has child
                set_left(bst_array,array[0],parent)
                return constructBST(bst_array,array[-len(array) + 1:],0) #rest of the list
            else:
                return constructBST(bst_array,array,2*parent+1)#because current has child it gives queue to its child
        else:
            if(set_right(bst_array,array[0],parent)!=-1):#same logic as above 
                set_right(bst_array,array[0],parent)
                return constructBST(bst_array,array[-len(array) + 1:],0)
            else:
                return constructBST(bst_array,array,2*parent+2)
            
def constructallBST(carray,bst_array):
    #bringing together the functions
    carray.append(None)#the last one is not a list
    root(bst_array,carray[0])#making root manually
    constructBST(bst_array,carray[-len(carray) + 1:],0)#construct bst

#for reading operations
def readFile(filename,carray):
    file = open(filename, "r")
    numbers = file.read()
    file.close()
    for i in numbers.split(" "):
        carray.append(i)
    carray[-1]=carray[-1].split("\n")[0] #the last one has escape charachter
    for i in range(len(carray)):
        carray[i] = int(carray[i])
    return 1

#file write operations
def writeFile(filename,sortedArray):
    inorderTraversel(0,bst_array,sortedArray)#makes inorder traversel in a list named sortedArray
    file = open(filename,"w+")
    for i in range(len(sortedArray)):
        file.write(str(sortedArray[i])+" ")
    file.close()
    
def inorderTraversel(root,bst_array,sortedArray):
    try:    #root can not be none. Inorder traversel 
        if(bst_array[root]!=None):
            inorderTraversel(2*root+1,bst_array,sortedArray)#left subtree
            sortedArray.append(bst_array[root])
            inorderTraversel(2*root+2,bst_array,sortedArray)#right subtree
    except:
        return 1
    
#in the main it brings together functions and makes the time measurement.
def allOf(filenamein, filenameout,carray,bst_array,sortedArray):
    readFile(filenamein,carray)  
    start = timer()
    constructallBST(carray,bst_array)
    end = timer()
    writeFile(filenameout,sortedArray)
    return end - start

#in here it does the 3 cases and makes it hundred times
flist = []#10 samples x 100
slist = []#100 samples x 100
tlist = []#1000 samples x 100

for i in range(100):
    #I declared lists tree times because they must be resetted
    carray = [] #carray coming array means unordered array of integers 
    bst_array = []
    sortedArray = []#after creating bst
    flist.append(allOf("numbers10.txt","sorted10.txt",carray,bst_array,sortedArray))#between 0 and 10000
    carray = [] 
    bst_array = []
    sortedArray = []
    slist.append(allOf("numbers100.txt","sorted100.txt",carray,bst_array,sortedArray))
    carray = [] 
    bst_array = []
    sortedArray = []#
    tlist.append(allOf("numbers1000.txt","sorted1000.txt",carray,bst_array,sortedArray))
    
#and here I am getting means of them
print("Sample size 10 and range is 0-10000: ",sum(flist)/len(flist)," seconds")
print("Sample size 100 and range is 0-10000: ",sum(slist)/len(slist), "seconds")
print("Sample size 1000 and range is 0-10000: ",sum(tlist)/len(tlist), "seconds")

        
