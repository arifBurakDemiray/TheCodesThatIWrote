# -*- coding: utf-8 -*-
"""
Created on Tue Apr 28 13:11:10 2020

@author: burak
"""
import numpy as np
from matplotlib import pyplot as plt
import math

numSamples = 100000
numBins = 100

# For the random variable Y, Fy(y) = y**2. We have to took derivation of this because we need pdf
def Fy(y,r):
    if y < 0:
        return 0
    elif y <= 1:
        return (2*y)/(r**2)
    else:
        return 0

Fy_array = []
ind = []
c = 1
for i in range(-200,1200):
    Fy_array.append(Fy((i/100),c))
    ind.append(i/100)
    c+=1
plt.figure()
plt.title('Pdf of Y')
plt.plot(ind,Fy_array)
plt.show()
#rejection method
print('----------------------------- Sample rejection method -----------------------------')
c = 2
a = -1
b = 1
Y = []
i = 0
while i < numSamples:
    u = np.random.rand()
    v = np.random.rand()
    y = (b-a)*u+a
    x = c*v
    if x <= Fy(y):
        Y.append(y)
        i = i + 1
        
plt.figure()
plt.title('Histogram of the generated Y samples (pdf)')
hY = plt.hist(Y, numBins, density=True)
plt.figure()
plt.title('Normalized cumulative sum of histogram values for the generated Y samples (cdf)')
plt.plot(hY[1][0:numBins], np.cumsum(hY[0])/hY[0].sum())
plt.show()

################################################################################################

# For the random variable X, fx(x) is as the following: we have to took integral of that because of cdf
def fx(x):
    if x < 0:
        fx = 0
    elif x <= 10:
        fx = (math.log(3*x+2)*1.08)/3
    else:
        fx = 1
    return fx

fx_array = []
ind = []
for i in range(-100,200):
    fx_array.append(fx(i/100))
    ind.append(i/100)
plt.figure()
plt.title('Cdf of X')
plt.plot(ind,fx_array)
plt.show()
#inverse transformation method
print('-------------------------- Inverse transformation method --------------------------')
def fx_inverse(u):
    return (math.e**(3*u/1.08)-2)/3
U = []
X = []
for i in range(numSamples):
    U.append(np.random.rand())
    X.append(fx_inverse(U[i]))
plt.figure()
plt.title('Histograms of the generated U and X samples (pdf)')
hU = plt.hist(U,numBins,alpha=0.5, density=True)
hX = plt.hist(X,numBins,alpha=0.5, density=True)
plt.figure()
plt.title('Normalized cumulative sum of histogram values for the generated U and X samples (cdf)')
plt.plot(np.cumsum(hU[0])/hU[0].sum())
plt.plot(np.cumsum(hX[0])/hX[0].sum())
plt.show()