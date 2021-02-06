# -*- coding: utf-8 -*-
"""
Created on Mon Apr 13 14:21:47 2020


"""

import math
def f(x):
    return(math.log(x) + x**2 - 3)

def bisection_method(a, b, tol):
    if f(a)*f(b) > 0:
        print("No root found.")
    else:
        while (b - a)/2.0 > tol:
            midpoint = (a + b)/2.0
            if f(midpoint) == 0:
                return(midpoint) 
            elif f(a)*f(midpoint) < 0:
                b = midpoint
            else:
                a = midpoint
        
        return(midpoint)

answer = bisection_method(1, math.e, 0.00001)

print("Answer:", answer)

