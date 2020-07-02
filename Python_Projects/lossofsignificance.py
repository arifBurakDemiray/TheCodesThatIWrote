# -*- coding: utf-8 -*-
"""
Created on Mon Apr 13 13:35:33 2020


"""

#for finding loss of significances
x=1e-1
flag = True
a=0
while (flag):
    print (((2*x)/(1-(x**2))),"......",(1/(1+x))-(1/(1-x)))
    x= x*(1e-1)
    a=a+1
    if(a==25):
        flag=False
