# -*- coding: utf-8 -*-
"""
Created on Tue Apr 21 14:57:04 2020

@author: burak
"""
import numpy as np
from matplotlib import pyplot as plt

N=20
Y = []
first = []
sec = []
third = []
fourth = []
for j in range(500):
    X=[]
    total = 0
    for i in range(N):
            u = np.random.rand()
            if(0.125<u<0.25):
                X.append(0.5)
                X.append(0.75)
                X.append(1)
                break
            elif (0.375<u<0.5):
                X.append(0.25)
                X.append(0.75)
                X.append(1)
                break
            elif(0.625<u<0.75):
                X.append(0.25)
                X.append(0.5)
                X.append(1)
                break
            elif(0.875<u<1):
                X.append(0.25)
                X.append(0.5)
                X.append(0.75)
                break
            total = total + 1
    Y.append(total)
    first.append(X.count(0.25))
    sec.append(X.count(0.5))
    third.append(X.count(0.75))
    fourth.append(X.count(1))
plt.figure()
plt.title("Total Geometric")
plt.hist(Y,bins=range(0,N+1),density=True)
plt.figure()
plt.title("First interval's (0-0.25)")
plt.hist(first,bins=range(0,N+1),density=True)
plt.figure()
plt.title("Second interval's (0.25-0.5)")
plt.hist(sec,bins=range(0,N+1),density=True)
plt.figure()
plt.title("Third interval's (0.5-0.75)")
plt.hist(third,bins=range(0,N+1),density=True)
plt.figure()
plt.title("Fourth interval's (0.75-1)")
plt.hist(fourth,bins=range(0,N+1),density=True)
plt.show()