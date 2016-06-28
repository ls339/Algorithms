public class fibonacciRecursiveMemo {

    private int sum;
    private int calls = 0;
    private int[] memo = new int[100];
    
    public fibonacciRecursiveMemo(int n) {
	sum = fibMemo(n);
	System.out.print("f"+ n + " = " + sum);
	System.out.println(" ; calls = " + calls );
    }

    private int fibMemo(int n) {
	calls++;
	if(memo[n] != 0) return memo[n];
	memo[n] = fib(n);
	return memo[n];
    }
    
    private int fib(int n) {
	if(n==1 || n==2) return 1;
	return fibMemo(n-1) + fibMemo(n-2);
    }

    // Main Tester
    public static void main(String[] args) {
	for(int i=1;i<=20;i++) new fibonacciRecursiveMemo(i);
    }

}
