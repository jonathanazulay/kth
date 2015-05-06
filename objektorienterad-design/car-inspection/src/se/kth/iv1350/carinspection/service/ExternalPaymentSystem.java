package se.kth.iv1350.carinspection.service;

import se.kth.iv1350.carinspection.dto.CreditCard;

public class ExternalPaymentSystem {
    /**
     * Authorizes transactions with credit cards
     * @param creditCard to use
     * @param amount to transact
     * @return object representing authorization
     */
    public static PaymentAuthorization createTransaction(CreditCard creditCard, float amount) {
        return new PaymentAuthorization(true, "");
    }
}
