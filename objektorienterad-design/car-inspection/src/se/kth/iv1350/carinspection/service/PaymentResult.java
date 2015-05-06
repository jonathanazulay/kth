/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.model;

/**
 *
 * @author jonathanazulay
 */
public class PaymentResult {
    private boolean success;
    private String description;
    private float change;
    private float newTotal;
    
    public PaymentResult (boolean success, String description, float change, float newTotal) {
        this.success = success;
        this.description = description;
        this.change = change;
        this.newTotal = newTotal;
    }
    
    public PaymentResult (boolean success, String description) {
        this(success, description, 0, 0);
    }
    
    public PaymentResult (boolean success) {
        this(success, "", 0, 0);
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
