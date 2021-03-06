package se.kth.iv1350.carinspection.service;

public class PaymentAuthorization {
    private final boolean success;
    private final String reason;
    
    /**
     * Creates an authorization for a credit card payment
     * @param success if authorization was success
     * @param reason possible reason for failure or success, if applicable
     */
    public PaymentAuthorization(boolean success, String reason) {
        this.success = success;
        this.reason = reason;
    }

    /**
     * Getter for reason
     * @return reason
     */
    public String getReason() {
        return this.reason;
    }
    
    /**
     * Getter for success
     * @return 
     */
    public boolean isSuccess() {
        return this.success;
    }
}
