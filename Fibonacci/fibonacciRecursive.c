#include <stdio.h>
#include <stdlib.h>

int fibonacci(int n);

int main(int argc, char** argv) {

  int n = atoi(argv[1]);

  for(int i=1;i<=n;i++) printf("%d ",fibonacci(i));
  //printf("%d", fibonacci());  
  return 0;
}

// Recursive function
int fibonacci(int n) {
  if(n==1 || n==2) {
    //printf("1 ");
    return 1;
  }
  //int f = fibonacci(n-1)+fibonacci(n-2);
  //printf("%d ",f);
  return fibonacci(n-1)+fibonacci(n-2);
}
