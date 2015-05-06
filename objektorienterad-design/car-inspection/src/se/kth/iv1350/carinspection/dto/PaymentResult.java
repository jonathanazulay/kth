package se.kth.iv1350.carinspection.dto;

public class PaymentResult {
    private final boolean success;
    private final String description;
    private final float change;
    private final float newTotal;
    
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
    
    /**
     * Returns true if payment was succeeded
     * @return success
     */
    public boolean isSuccess () {
        return this.success;
    }
    
    /**
     * Returns description of payment, if applicable. Could be reason for failure
     * @return 
     */
    public String getDescription () {
        return this.description;
    }
    
    /**
     * Returns change
     * @return 
     */
    public float getChange () {
        return this.change;
    }
    
    /**
     * Returns updated total cost
     * @return 
     */
    public float getNewTotal () {
        return this.newTotal;
    }
}
