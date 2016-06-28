#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define TRUE 1
#define FALSE 0

int isPrime(int);

int main(int argc,char *argv[]) {

  int i,n;
  n = atoi(argv[1]);
  
  for(i = 1;i <= n;i++){
    if(isPrime(i)) printf("%d\n",i);
  }

}

int isPrime(int n) {
  int i;
  for(i = 2;i<((int)sqrt(n)+1);i++) {
    if(n % i == 0) return FALSE;
  }
  return TRUE;
}
