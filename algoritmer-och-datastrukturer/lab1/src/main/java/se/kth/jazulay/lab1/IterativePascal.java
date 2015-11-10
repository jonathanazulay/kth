package se.kth.jazulay.lab1;

public class IterativePascal extends ErrorPascal {
    boolean reverse;

    public IterativePascal (boolean reverse) {
        this.reverse = reverse;
    }
    
    @Override
    public void printPascal (int n) {
       this.validatePrintInput(n);
        
        int lastRow = n;
        while (n >= 0) {
            int currentRow = n;
            if (!this.reverse) { currentRow = (n - lastRow) * -1; }
            int k = 0;
            while (k <= currentRow) {
                System.out.print(this.binom(currentRow, k) + " ");
                k += 1;
            }
            System.out.println("");
            n -= 1;
        }
    }

    @Override
    public int binom(int n, int k) {
        this.validateBinomInput(n, k);
        
        int binom = 1;
        double i = 1;
        while (i <= k) {
            binom *= (n + 1 - i) / i;
            i += 1;
        }
        return binom;
    }
}
