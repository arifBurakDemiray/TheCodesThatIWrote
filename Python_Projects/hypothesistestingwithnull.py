# -*- coding: utf-8 -*-
"""
Created on Wed Jun 24 13:24:13 2020

@author: burak
"""
from matplotlib import pyplot as plt
import numpy as np
import random
# All file like matrix
ages = np.genfromtxt("titanic_data.txt") 
first = [] # 1st class
second = [] # 2nd class
third = [] # 3rd class
crew = [] # crew
whole = [] # whole of them
rest = [] # except 1st class
for i in ages:
    if(i[0]==1):
        whole.append(i[1])
        first.append(i[1])
    elif(i[0]==2):
        whole.append(i[1])
        second.append(i[1])
        rest.append(i[1])
    elif(i[0]==3):
        whole.append(i[1])
        third.append(i[1])
        rest.append(i[1])
    elif(i[0]==0):
        whole.append(i[1])
        crew.append(i[1])
        rest.append(i[1])

print("The averages for whole data, crew data, first class, second class and third class data are")
print("%0.2f, %0.2f, %0.2f, %0.2f and %0.2f, respectively."%(np.mean(whole),np.mean(crew),np.mean(first),np.mean(second),np.mean(third)))

diff1 = [] #difference of crew and third
diff2 = [] #diffrence of first and rest
for i in range(1000):
    #mean diffrence of crew and third
    mean1 = (np.mean(random.choices(crew,k=500))-np.mean(random.choices(third,k=500)))
    #mean difference of first and rest
    mean2 = (np.mean(random.choices(first,k=500)-np.mean(random.choices(rest,k=500))))
    diff1.append(mean1)
    diff2.append(mean2)
#looking interval for crew and third    
lk1 = round(np.mean(diff1),2)
#looking interval for first and rest
lk2 = round(np.mean(diff2),2)


#all is due to null hypothesis is true
sct = [] #crew-third plotting values except rejected ones
sfr = [] #first-rest plotting values except rejected ones
sNCT = [] #c-t diffrence values except rejected ones
sNFR = [] #f-r diffrence values except rejected ones
orgCT = np.mean(crew) - np.mean(third)#actual diffrences (original)
orgFR = np.mean(first) - np.mean(rest) 
for i in range(10000):
    #random choice mean diffrences for c-t
    ran1 = (np.mean(random.choices(crew,k=500))-np.mean(random.choices(third,k=500)))
    #random choice mean diffrences for f-r
    ran2 = (np.mean(random.choices(first,k=500)-np.mean(random.choices(rest,k=500))))
    sct.append(lk1 - ran1)
    sfr.append(lk2 - ran2)
    #below part for p-values
    if(orgCT + lk1 < ran1 < orgCT + lk1 + abs(2*lk1)):#for interval
        sNCT.append(1)
    else:
        sNCT.append(0)
    if(orgFR + lk2 < ran2 < orgFR + lk2 + abs(2*lk2)):
        sNFR.append(1)
    else:
        sNFR.append(0)
    
p_value1 = sum(sNCT)/len(sNCT)
#p values
p_value2 = sum(sNFR)/len(sNFR)

#pdf
plt.figure()
CDF1 = plt.hist(sct,bins=150)
plt.show()
#cdf
plt.figure()
plt.plot(CDF1[1][0:150], np.cumsum(CDF1[0])/CDF1[0].sum())
plt.axvline(lk1, 0.06, p_value1+0.06 ,color='orange')
plt.show()


print("\nThe p-value for %.2f difference and less between means for crew and 3rd class is %.2f"%(lk1,round(p_value1,2)))

#pdf
plt.figure()
CDF2 = plt.hist(sfr,bins=150)
plt.show()
#cdf
plt.figure()
plt.plot(CDF2[1][0:150], np.cumsum(CDF2[0])/CDF2[0].sum())
plt.axvline(lk2, 0.06, p_value2+0.06 ,color='orange')
plt.show()


print("\nThe p-value for %.2f difference and more in means for 1st class and the rest is %.2f"%(lk2,round(p_value2,2)))

