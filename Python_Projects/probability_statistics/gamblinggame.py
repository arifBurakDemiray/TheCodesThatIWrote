# -*- coding: utf-8 -*-
"""
Created on Mon Jun  1 13:32:27 2020

@author: burak
"""
import random
#I took cdf because I am going to use inverse transform method
def gambBet(x,r):
    return (x**2)/(r**2)
def inverse_B(x,r):
    return (x*(r**2))**(1/2)
def gambABet(x):
    return 2*x
money = [100,10000,1000,1000000]
for moneys in range(2):
    expectedA = []
    expectedB = []
    probilityA = []
    probilityB = []
    for o in range(100): #for getting more accurate solutions #THIS FOR LOOP#
        egambB = []#all expected values
        pgambB = []#all bets
        gambBRes = []#true and false values
        i = 0
        gamblerB = money[moneys]
        gambB = gamblerB
        firstroll = 0
        flag = False
        flag1 = False#for enterin special case
        while gambB < gamblerB + 500:
            dice1 = random.randint(1,7)
            dice2 = random.randint(1,7)
            result = dice1 + dice2
            if i==0:
                firstroll = result
            if(gambB > 0):
                if(flag1 == False):
                    pgambB.append(inverse_B(random.randint(0,i+1),i+1))
                    if(gambB - pgambB[-1] < 0):
                        break
                    flag1 = True
                if(flag == False and (result==7 or result == 11)):
                    gambB+=pgambB[-1]
                    gambBRes.append(1)
                    egambB.append(pgambB[-1]*(8/36))
                    flag1 = False
                elif(flag == False and(result==2 or result==3 or result==12)):
                    gambB-=pgambB[-1]
                    gambBRes.append(0)
                    egambB.append(-pgambB[-1]*(4/36))
                    flag1 = False
                else:
                    flag = True
                if(flag == True):
                    if(result == 7):
                        gambB-=pgambB[-1]
                        gambBRes.append(0)
                        egambB.append(-pgambB[-1]*(6/36))
                        flag = False
                        flag1 = False
                    elif(i!=0 and firstroll==result):
                        gambB+=pgambB[-1]
                        gambBRes.append(1)
                        egambB.append(pgambB[-1]*(134/495))
                        flag = False
                        flag1 = False
            else:
                break
            i = i + 1
        expectedB.append(sum(egambB)/len(egambB))
        probilityB.append(gambBRes.count(1)/len(gambBRes))
        ##################################################################################
        i = 0
        gamblerA = money[moneys+2]
        gambA = gamblerA
        pgambA = []
        egambA = []
        gambARes = []
        firstroll = 0
        bet = 1
        while gambA < 2*gamblerA:
            dice1 = random.randint(1,7)
            dice2 = random.randint(1,7)
            result = dice1 + dice2
            pgambA.append(bet)
            flag = False
            if i == 0:
                firstroll = result
            if(gambA - bet > 0):
                if(flag == False and(result==7 or result == 11)):
                    gambA += pgambA[i]
                    gambARes.append(1)
                    egambA.append(pgambA[i]*(8/36))#prob of 7 or 11
                elif(flag == False and(result==2 or result==3 or result==12)):
                    gambA-=pgambA[i]
                    gambARes.append(0)
                    egambA.append(-pgambA[i]*(4/36))#prob of 2 3 or 12
                    bet = gambABet(bet)
                else:
                    flag = True
                if(flag == True):
                    if(result == 7):
                        gambA-=pgambA[i]
                        gambARes.append(0)
                        egambA.append(-pgambA[i]*(6/36))#prob of sum of 7
                        bet = gambABet(bet)
                        flag = False
                    elif(i!=0 and firstroll==result):
                        gambA += pgambA[i]
                        gambARes.append(1)
                        egambA.append(pgambA[i]*(134/495))#prob of first roll
                        flag = False         
            else:
                break
            i = i + 1
        expectedA.append(sum(egambA)/len(egambA))
        probilityA.append(gambARes.count(1)/len(gambARes))
    print("-----------------------------------------------------------------------------------------------------")
    print("Probility of winning for gambler A if he/she bets", money[moneys+2],"$:",(sum(probilityA)/len(probilityA)))    
    print("Expected gain of gambler A if he/she bets",money[moneys+2],"$:",(sum(expectedA)/len(expectedA)))
    print("-----------------------------------------------------------------------------------------------------")
    print("Probility of winning for gambler B if he/she bets", money[moneys],"$:",(sum(probilityB)/len(probilityB)))
    print("Expected gain of gambler B if he/she bets",money[moneys],"$:",(sum(expectedB)/len(expectedB)))
print("-----------------------------------------------------------------------------------------------------")
#It might be executed late because of while loops.If it is problem, please decrease the number of for loop.






       
