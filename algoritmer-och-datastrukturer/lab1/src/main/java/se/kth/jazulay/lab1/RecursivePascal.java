package se.kth.jazulay.lab1;

public class RecursivePascal {
    boolean reverse;
    
    public RecursivePascal (boolean reverse) {
        this.reverse = reverse;
    }
    
    public void printPascal (int n) {
        if (n < 0) { return; }
        System.out.println("");
        for (int k = 0; k <= n; k += 1) {
            System.out.print(this.binom(n, k) + " ");
        }
        this.printPascal(n - 1);
    }
    
    public int binom (int n, int k) {
        if (n <= 0 || k >= n || k == 0) {
            return 1;
        }
        return binom(n - 1, k - 1) + binom(n - 1, k);
    }
}
