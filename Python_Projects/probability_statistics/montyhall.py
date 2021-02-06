# -*- coding: utf-8 -*-
"""
Created on Thu Apr 23 13:04:49 2020

@author: burak
"""
import random
from matplotlib import pyplot as plt
# 0 means keeping decision
# 1 means changing decision
result0 = []
result1 = []
doorList = ["Goat","Goat","Car"]
probCount0 = 0
probCount1 = 0
for j in range(1000):
    trueCount0 = []
    trueCount1 = []
    for i in range(100):
        contestant = ""
        presenter = ""
        contInt = random.randint(0,2)
        contestant = doorList[contInt]
        if(contInt == 0):   
            presenter = doorList[1]
            trueCount0.append(False)
            trueCount1.append(True)
        elif(contInt == 1):     
            presenter = doorList[0]
            trueCount0.append(False)
            trueCount1.append(True)
        else:
            preInt = random.randint(0,1)
            presenter = doorList[preInt]
            trueCount0.append(True)
            trueCount1.append(False)
    result0.append(sum(trueCount0))
    result1.append(sum(trueCount1))
    probCount0 = probCount0 + sum(trueCount0) 
    probCount1 = probCount1 + sum(trueCount1) 
probOfWinning0 = probCount0/1000
probOfWinning1 = probCount1/1000

print("\n\n","The probility of winning if we decide to keeping decision is %",probOfWinning0,"\n")
plt.figure()
plt.title("Keeping Decision")
plt.hist(result0)
plt.show()
print("\n\n","The probility of winning if we decide to change decision is %",probOfWinning1,"\n")
plt.figure()
plt.title("Changing Decision")
plt.hist(result1)
plt.show()
""" 
When we look to 0, we observe from graph that true counts tend to between 30 and 35. 
When we look to 1, we observe from graph that true counts tend to between 65 and 70.
"""