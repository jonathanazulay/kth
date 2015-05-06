package se.kth.iv1350.carinspection.model;

import se.kth.iv1350.carinspection.dto.CreditCard;
import se.kth.iv1350.carinspection.service.ExternalPaymentSystem;
import se.kth.iv1350.carinspection.service.PaymentAuthorization;
import se.kth.iv1350.carinspection.dto.PaymentResult;

public class Sale {
    private final Inspection inspection;
    private boolean startedPay = false;
    private float currentCost;
    
    /**
     * Create a new sale for a specified inspection
     * 
     * @param inspection to pay for
     */
    public Sale (Inspection inspection) {
        this.inspection = inspection;
    }
    
    /**
     * Calculates the price of inspection
     * 
     * @return price
     */
    public float calculateCost () {
        return 20 + this.inspection.getInspectionsLeft() * 98;
    }
    
    /**
     * Decreses the total price of sale using a credit card
     * 
     * @param creditCard to use
     * @param amount to pay (can be less than total price)
     * @return object representing result of payment
     */
    public PaymentResult payWithCreditCard (CreditCard creditCard, float amount) {
        PaymentAuthorization creditCardPayment = ExternalPaymentSystem.createTransaction(creditCard, amount);
        
        if (creditCardPayment.isSuccess()) {
            return this.pay(amount);
        } else {
            return generatePaymentResult(false, creditCardPayment.getReason());
        }
    }
    
    /**
     * Decreses the total price of sale using cash payment
     * 
     * @param amount to pay (can be less than total price)
     * @return object representing result of payment
     */
    public PaymentResult payWithCash (float amount) {
        return this.pay(amount);
    }
    
    private PaymentResult pay (float amount) {
        if (amount <= 0) {
            return generatePaymentResult(false, "Amount payed has to be larger than 0");
        }
        
        if (!this.startedPay) {
            this.currentCost = this.calculateCost();
            this.startedPay = true;
        }
        
        this.currentCost -= amount;
        
        return generatePaymentResult(true, "");
    }
    
    private PaymentResult generatePaymentResult (boolean isSuccess, String descr) {
        if (this.currentCost < 0) {
            float change = this.currentCost * -1;
            this.currentCost = 0;
            return new PaymentResult(isSuccess, descr, change, 0);
        } else {
            return new PaymentResult(isSuccess, descr, 0, this.currentCost);
        }
    }
    
}
