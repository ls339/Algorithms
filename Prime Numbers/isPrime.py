import math

def isPrime(n):
    for x in range(2,int(math.sqrt(n))+1):
        if (n % x) == 0 :
            return 0
    return 1


for x in range(1,100):
    if (isPrime(x)): print(x)
