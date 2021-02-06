# -*- coding: utf-8 -*-
"""
Created on Tue Apr 21 12:55:18 2020

@author: burak
"""

import numpy as np
from matplotlib import pyplot as plt

#I divided it into 4 sub interval that are 0,0.25,0.5,0.75 and 1.
N=1000
X = []
for i in range(1000):
        u = np.random.rand()
        if(u<0.125):
            X.append(0.25)
            X.append(0.5)
            X.append(0.75)
            X.append(1)
        elif (u<0.25 or u<0.375):
            X.append(0)
            X.append(0.5)
            X.append(0.75)
            X.append(1)
        elif(u<0.5 or u<0.625):
            X.append(0)
            X.append(0.25)
            X.append(0.75)
            X.append(1)
        elif(u<0.75 or u<0.875):
            X.append(0)
            X.append(0.25)
            X.append(0.5)
            X.append(1)
        elif(0.875<u<1):
            X.append(0)
            X.append(0.25)
            X.append(0.75)
            X.append(1)
plt.figure()
plt.title("Bernoulli")
plt.hist(X,25,density=True)
plt.show()