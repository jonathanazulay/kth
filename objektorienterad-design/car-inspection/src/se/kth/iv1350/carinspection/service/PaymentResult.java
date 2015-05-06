package se.kth.iv1350.carinspection.service;

public class PaymentResult {
    private boolean success;
    private String description;
    private float change;
    private float newTotal;
    
    /**
     * Creates a payment result including any possible change and new total
     * 
     * @param success if payment was successfull
     * @param description could be reason for failure or other
     * @param change change that should be returned to customer
     * @param newTotal for customer to pay
     */
    public PaymentResult (boolean success, String description, float change, float newTotal) {
        this.success = success;
        this.description = description;
        this.change = change;
        this.newTotal = newTotal;
    }
    
    public boolean isSuccess () {
        return this.success;
    }
    
    public String getDescription () {
        return this.description;
    }
    
    public float getChange () {
        return this.change;
    }
    
    public float getNewTotal () {
        return this.newTotal;
    }
}
