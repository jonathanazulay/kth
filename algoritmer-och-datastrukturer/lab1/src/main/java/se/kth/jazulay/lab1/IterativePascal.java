package se.kth.jazulay.lab1;

import java.math.BigInteger;

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
        
        if (k > n/2) {
            // use symmetry, prevents caching same values
            k = n - k;
        }
        
        return this.factorial(n).divide(this.factorial(k).multiply(this.factorial(n - k))).intValue();
    }
    
    private BigInteger factorial (int fact) {
        BigInteger n = new BigInteger(fact + "");
        BigInteger factN = BigInteger.ONE;
        while (n.intValue() > 0) {
            factN = factN.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }
        return factN;
    }
}
