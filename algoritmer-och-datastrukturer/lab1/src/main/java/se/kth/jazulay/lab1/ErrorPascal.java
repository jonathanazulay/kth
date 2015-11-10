package se.kth.jazulay.lab1;

public abstract class ErrorPascal implements Pascal {
    protected void validatePrintInput (int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                "atleast row zero has to be printed, n has to be greater than or equal to 0"
            );
        }
    }
    
    protected void validateBinomInput (int n, int k) {
        if (n < 0 || k > n) {
            throw new IllegalArgumentException(
                "invalid element position"
            );
        }
    }
}
