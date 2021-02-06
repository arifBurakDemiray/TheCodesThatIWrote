# -*- coding: utf-8 -*-
"""
Created on Mon Jun 15 16:20:43 2020

@author: burak

250201022 - Arif Burak Demiray
"""

import numpy as np
from scipy.stats import binom
from matplotlib import pyplot as plt
#for binomial distrubution and moment theorems
#we need expected value and variations of distrubution
#we also need second moment central and first moment theorems
#in first moment we need expected value whhich is n.p = average of samples
#in second central we need variance end n.p.(1-p).
#in here p estimation is 1-average/variance
#n estimation is average^2/average-variance
print("--------------------------------------------------------------------------------")
trials = [200,800,3200]
ns = []
ps = []
for i in range(len(trials)):
    ns.append(0)
    ps.append(0)
looper = 0
for tr in trials:
    n = []
    p = []
    for j in range(1000):
        samples = binom.rvs(size=tr,n=40,p=0.3)
        av = np.mean(samples) #average
        var = sum((((xi - av)**2) for xi in samples))/tr#variation
        p.append(1-(var/av))#estimated p
        n.append((av**2)/(av-var))#estiamted n
    print("Mean for estimated p for samples size of {} is {}".format(tr,np.mean(p)))
    print("Standart deviation for estimated p for sample size of {} is {}".format(tr,np.std(p)))
    print("Mean for estimated p for samples size of {} is {}".format(tr,np.mean(n)))
    print("Standart deviation for estimated p for sample size of {} is {}".format(tr,np.std(n)))
    print("--------------------------------------------------------------------------------")
    ps[looper] = p
    ns[looper] = n
    looper+=1
looper = 0
for tr in trials:
    plt.title("Histogram for estimated p")
    plt.hist(ps[looper],bins=100,label=str(tr),alpha=0.5,density=True)
    looper+=1
plt.legend()
plt.show()
plt.figure()
looper = 0
for tr in trials:
    plt.title("Histogram for estimated n")
    plt.hist(ns[looper],bins=100,label=str(tr),alpha=0.5,density=True)
    looper+=1
plt.legend()
plt.show()
