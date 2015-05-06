package se.kth.iv1350.carinspection.model;

import se.kth.iv1350.carinspection.dto.CreditCard;
import se.kth.iv1350.carinspection.service.ExternalPaymentSystem;
import se.kth.iv1350.carinspection.service.PaymentAuthorization;

public class Sale {
    private Inspection inspection;
    private boolean startedPay = false;
    private float currentCost;
    
    public Sale (Inspection insp) {
        this.inspection = insp;
    }
    
    public float calculateCost () {
        return 20 + this.inspection.getInspectionsLeft() * 98;
    }
    
    public PaymentResult payWithCreditCard (CreditCard creditCard, float amount) {
        PaymentAuthorization creditCardPayment = ExternalPaymentSystem.createTransaction(creditCard, amount);
        
        if (creditCardPayment.isSuccess()) {
            return this.pay(amount);
        } else {
            return generatePaymentResult(false, creditCardPayment.getDescription());
        }
    }
    
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
    
    private PaymentResult generatePaymentResult (boolean success, String descr) {
        if (this.currentCost < 0) {
            float change = this.currentCost * -1;
            this.currentCost = 0;
            return new PaymentResult(success, descr, change, 0);
        } else {
            return new PaymentResult(success, descr, 0, this.currentCost);
        }
    }
    
}
