#include <stdio.h>
#include <stdlib.h>

int main(int argc, char**  argv) {

  int n = atoi(argv[1]);
  int f0, f1, fn;

  // Seed fibonacci initial values.
  f0 = 1;
  f1 = 1;

  /*
   * Calculate the fibonacci sequence iteratively.
   * fn = fn-1 + fn-2 
   */
    for(int i=0;i<n;i++) {
    if(i<=1) {
       fn = 1;
    } else {
      fn = f0 + f1;
      f0 = f1;
      f1 = fn;
    }
    printf("%d ",fn); // Print the sequence.
  }
  printf("\n");
}
