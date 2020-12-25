#these codes simulates a rlc circuit that is given by the png named rlc.png
#Arif Burak Demiray
#contact burakdemiray09@gmail.com
import matplotlib.pyplot as plt
import math
plt.style.use('ggplot')


N=50000 # number of steps
T=1   # simulation time in seconds

C = 125*10**(-6)
L = 10*10**(-3)
R = 4000

t = np.empty(N)
Vc = np.empty(N)
I = np.empty(N)
VT= np.empty(N)
dt=T/N;

# initial values
Vc[0]=0
I[0]=0

for k in range(0,N-1):
	t[k+1]=t[k]+dt
	VT[k] = 10 #10*math.sin(20*t[k])
	I[k+1] = (VT[k]-Vc[k])*(dt/L) + I[k]
	Vc[k+1] = ((I[k+1]*dt)/C) + Vc[k] - (Vc[k]*dt)/(C*R)

    
	
plt.plot(t,VT,label='Voltage')
plt.plot(t,Vc,label='Vout Voltage')
plt.plot(t,I,label='Current')



plt.xlabel('Time (s)')
plt.title('Driven 2nd degree circuit')
plt.legend()
plt.show()

