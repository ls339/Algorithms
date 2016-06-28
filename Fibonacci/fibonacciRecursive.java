public class fibonacciRecursive {

    private int sum;
    private int calls = 0;

    public fibonacciRecursive(int n) {
	sum = fib(n);
	System.out.print("f"+ n + " = " + sum);
	System.out.println(" ; calls = " + calls );
    }

    private int fib(int n) {
	calls++;
	if(n==1 || n==2) return 1;
	return fib(n-1) + fib(n-2);
    }

    // Main tester
    public static void main(String[] args) {
	for(int i=1;i<=20;i++) new fibonacciRecursive(i);
    }

}
