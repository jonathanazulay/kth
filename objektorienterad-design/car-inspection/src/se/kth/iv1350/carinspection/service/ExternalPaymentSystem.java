/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.carinspection.service;

import se.kth.iv1350.carinspection.dto.CreditCard;

/**
 *
 * @author jonathanazulay
 */
public class ExternalPaymentSystem {
    public static PaymentAuthorization createTransaction(CreditCard creCard, float amount) {
        return new PaymentAuthorization(true, "");
    }
}
