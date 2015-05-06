/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.service;

/**
 *
 * @author jonathanazulay
 */
public class PaymentAuthorization {
    private final boolean success;
    private final String description;
    
    public PaymentAuthorization(boolean success, String description) {
        this.success = success;
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public boolean isSuccess() {
        return this.success;
    }
}
