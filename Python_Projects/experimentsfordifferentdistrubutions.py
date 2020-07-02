# -*- coding: utf-8 -*-
"""
Created on Tue Jun  9 18:44:31 2020

@author: burak
"""
import numpy as np
import random
import matplotlib.pyplot as plt
from scipy import stats
from scipy.stats import semicircular
#Experiment 1-2-3
LOV = [2,10,50]
for values in LOV:
    s = np.random.uniform(0, 1, 100000)
    total = []
    for i in range(4000):
        total.append(sum(random.choices(s,k=values)))#it randomly choose from s list
    print("Sample mean is:",np.mean(total))
    print("Sample standart deviation is:",np.std(total))
    plt.figure()
    plt.title("Histogram for generated random variables")
    plt.hist(s,50,cumulative=0,density=True)
    plt.show()
    plt.figure()
    plt.title("Histogram for sums of generated random variables")
    plt.hist(total,70,cumulative=0,density=True)
    mu = np.mean(total) #this part for normal curve. It is same in all experiments. 
    sigma = np.std(total)
    x = np.linspace(mu - 8*sigma, mu + 8*sigma, 4000)
    plt.plot(x, stats.norm.pdf(x, mu, sigma))
    plt.show()
    print("\n--------------------------\n")



#Experiment 4
total = []
bigs = [] #total samples
for j in range(10000):
    s = []
    for i in range (100):
        if(sum(s)>40):
            temp = np.random.uniform(-0.5,0.5)
            s.append(temp)
            bigs.append(temp)
        else:
            temp = np.random.uniform(0.5,1.5)
            bigs.append(temp)
            s.append(temp)
    total.append(sum(s))

print("Sample mean is:",np.mean(total))
print("Sample standart deviation is:",np.std(total))
plt.figure()
plt.title("Histogram for generated random variables")
plt.hist(bigs,70,cumulative=0,density=True)
plt.show()
plt.figure()
plt.title("Histogram for sums of generated random variables")
plt.hist(total,70,cumulative=0,density=True)
mu = np.mean(total)
sigma = np.std(total)
x = np.linspace(mu - 8*sigma, mu + 8*sigma, 4000)
plt.plot(x, stats.norm.pdf(x, mu, sigma))
plt.show()
print("\n--------------------------\n")





#Experiment 5-6-7
for values in LOV:
    s = semicircular.rvs(2, 1,10000)
    total = []
    for i in range(4000):
        total.append(sum(random.choices(s,k=values)))
    print("Sample mean is:",np.mean(total))
    print("Sample standart deviation is:",np.std(total))
    plt.figure()
    plt.title("Histogram for generated random variables")
    plt.hist(s,70,cumulative=0,density=True)
    plt.show()
    plt.figure()
    plt.title("Histogram for sums of generated random variables")
    plt.hist(total,50,cumulative=0,density=True)
    mu = np.mean(total)
    sigma = np.std(total)
    x = np.linspace(mu - 8*sigma, mu + 8*sigma, 4000)
    plt.plot(x, stats.norm.pdf(x, mu, sigma))
    plt.show()
    print("\n--------------------------\n")
    
    