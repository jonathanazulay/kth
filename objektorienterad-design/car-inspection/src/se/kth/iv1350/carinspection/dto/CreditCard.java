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
    
    public CreditCard (String cardNumber, int pin) {    
        this.cardNumber = cardNumber;
        this.pin = pin;
}
    
    public String getCardNumber () {
        return this.cardNumber;
    }
    
    public int getPin () {
        return this.pin;
    }
}
