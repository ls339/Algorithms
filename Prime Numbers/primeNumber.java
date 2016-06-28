public class primeNumber{

    public boolean isPrime(int n) {
	for(int i=2;i*i <= n;i++) {
	    if(n%i == 0 ) return false;
	}
	return true;
    }

    private boolean isOdd(int n) {
	if(n % 2 == 0) return false;
	return true;
    }

    // Main Tester
    public static void main(String[] args) {
	primeNumber pn = new primeNumber();
	for(int i=1;i<=100;i++) {
	    if(pn.isPrime(i)) System.out.println(i);
	}
    }
}
