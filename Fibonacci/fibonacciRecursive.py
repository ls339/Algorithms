
def fib(n):
    if n==1 or n==2:
        return 1
    return fib_memo(n-1) + fib_memo(n-2)

def fib_memo(n):
    if memo[n] != 0 :
        return memo[n]
    memo[n] = fib(n)
    return memo[n]

n = int(input('enter an integer '))
memo = {x:0 for x in range(1,n)}

for x in range(1,n):
    #fib(x)
    print(fib(x), end=' ')

#print(memo)
