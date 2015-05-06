package se.kth.iv1350.carinspection.dto;

import org.junit.Test;
import static org.junit.Assert.*;

public class CreditCardTest {
    
    @Test
    public void testGetCardNumber() {
        CreditCard normalCreditCard = new CreditCard("2128748702378946789", 3782);
        assertEquals(normalCreditCard.getCardNumber(), "2128748702378946789");
    }

    @Test
    public void testGetPin() {
        CreditCard creditCard = new CreditCard("128748702378946789", 3782);
        assertEquals(creditCard.getPin(), 3782);
    }
    
}
