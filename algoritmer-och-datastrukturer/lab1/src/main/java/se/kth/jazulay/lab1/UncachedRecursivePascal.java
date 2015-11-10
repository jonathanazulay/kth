package se.kth.jazulay.lab1;

public class UncachedRecursivePascal implements Pascal {
    boolean reverse;

    public UncachedRecursivePascal (boolean reverse) {
        this.reverse = reverse;
    }
    
    @Override
    public void printPascal (int n) {
        if (n < 0) { return; }
        if (!this.reverse) { this.printPascal(n - 1); }
        System.out.println(" ");
        for (int k = 0; k <= n; k += 1) {
            System.out.print(this.binom(n, k) + " ");
        }
        if (this.reverse) { this.printPascal(n - 1); }
    }
    
    @Override
    public int binom (int n, int k) {
        if (n <= 0 || k >= n || k == 0) {
            return 1;
        }
        return binom(n - 1, k - 1) + binom(n - 1, k);
    }
}
