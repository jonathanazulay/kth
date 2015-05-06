/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.dto;

/**
 *
 * @author jonathanazulay
 */
public class CreditCard {
    protected String cardNumber;
    protected int pin;
    
    /**
     * Creates a credit card 
     * @param cardNumber
     * @param pin 
     */
    public CreditCard (String cardNumber, int pin) {    
        this.cardNumber = cardNumber;
        this.pin = pin;
    }
    
    /**
     * Card number getter
     * @return card number
     */
    public String getCardNumber () {
        return this.cardNumber;
    }
    
    /**
     * Pin getter
     * @return pin
     */
    public int getPin () {
        return this.pin;
    }
}
