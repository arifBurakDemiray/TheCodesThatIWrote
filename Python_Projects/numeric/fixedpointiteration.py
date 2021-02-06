# -*- coding: utf-8 -*-
"""
Created on Mon Apr 13 14:29:08 2020


"""


import math
def f(x):
	return math.log(x) + x**2 -3
a = 2.09010005
temp = f(a)
for i in range(10):
    print(temp)
    temp = f(temp)
