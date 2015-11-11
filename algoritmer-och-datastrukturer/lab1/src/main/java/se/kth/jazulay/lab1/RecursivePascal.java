package se.kth.jazulay.lab1;

import java.util.ArrayList;

public class RecursivePascal extends ErrorPascal {
    boolean reverse;
    ArrayList<ArrayList<Integer>> rows;

    public RecursivePascal (boolean reverse) {
        this.reverse = reverse;
        this.rows = new ArrayList();
    }
    
    @Override
    public void printPascal (int n) {
        this.validatePrintInput(n);
        
        if (!this.reverse && n > 0) { this.printPascal(n - 1); }
        System.out.println(" ");
        for (int k = 0; k <= n; k += 1) {
            System.out.print(this.binom(n, k) + " ");
        }
        if (this.reverse && n > 0) { this.printPascal(n - 1); }
    }
    
    @Override
    public int binom (int n, int k) {
        this.validateBinomInput(n, k);
        
        if (n == 0 || k == n || k == 0) {
            return 1;
        }
        
        if (k > n/2) {
            // use symmetry, prevents caching same values
            k = n - k;
        }
        
        Integer result = this.fetchCached(n, k);
        if (result == null) {
            result = binom(n - 1, k - 1) + binom(n - 1, k);
            this.storeCached(n, k, result);
        }
        return result;
    }
    
    private Integer fetchCached (int n, int k) {
        try {
            return this.rows.get(n).get(k);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return null;
        }
    }
    
    private void storeCached (int n, int k, int value) {
        while (this.rows.size() <= n) {
            this.rows.add(new ArrayList<Integer>());
        }
        
        while (this.rows.get(n).size() <= k) {
            this.rows.get(n).add(null);
        }
        
        this.rows.get(n).set(k, value);  
    }
}
