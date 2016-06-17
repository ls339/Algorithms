#include <stdio.h>
#include <stdlib.h>

int main(int argc, char**  argv) {

  int n = atoi(argv[1]);
  int A[n];

  // Seed fibonacci initial values.
  A[0] = A[1] = 1;

  /*
   * Calculate the fibonacci sequence iteratively.
   * fn = fn-1 + fn-2 
   */
  for(int i=2;i<n;i++) A[i] = A[i-1] + A[i-2];

  // Print the sequence.
  for(int i=0;i<n;i++) printf("%d ",A[i]);
  printf("\n");
}
