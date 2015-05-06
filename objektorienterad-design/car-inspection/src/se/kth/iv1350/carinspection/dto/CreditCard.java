package se.kth.iv1350.carinspection.dto;

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
